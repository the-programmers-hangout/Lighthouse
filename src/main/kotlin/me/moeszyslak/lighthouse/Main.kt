package me.moeszyslak.lighthouse

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.extensions.*
import me.moeszyslak.lighthouse.dataclasses.Configuration
import java.awt.Color
import java.time.Instant

private val startup = Instant.now()

@PrivilegedIntent
@KordPreview
suspend fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null
    val prefix = System.getenv("DEFAULT_PREFIX") ?: "<none>"

    require(token != null) { "Expected the bot token as an environment variable" }

    bot(token) {
        val configuration = data("config/config.json") { Configuration() }

        prefix {
            guild?.let { configuration[it.id]?.prefix } ?: prefix
        }

        configure {
            mentionAsPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
            documentCommands = true
            entitySupplyStrategy = EntitySupplyStrategy.cacheWithRestFallback
            intents = Intents.all
            defaultPermissions = Permissions(Permission.ManageMessages)
        }

        mentionEmbed {
            val guildConfiguration = configuration[it.guild!!.id]

            title = "Lighthouse 3.0.0"
            description = "A multi-guild discord bot to log everything and everything you could ever want"
            color = it.discord.configuration.theme
            thumbnail(it.discord.kord.getSelf().pfpUrl)
            addInlineField("Source", "[GitHub](https://github.com/the-programmers-hangout/Lighthouse)")
            addInlineField("Ping", it.discord.kord.gateway.averagePing?.toString() ?: "Unknown")
            addInlineField("Startup", TimeStamp.at(startup, TimeStyle.RELATIVE))
            addInlineField("Channel", "<#${guildConfiguration?.alertChannel}>")
            addInlineField("Role", "<@${guildConfiguration?.alertRole}>")
            footer(it.discord.versions.toString())
        }
    }
}