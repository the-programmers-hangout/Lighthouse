package me.moeszyslak.lighthouse

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Snowflake
import dev.kord.common.kColor
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.addInlineField
import me.moeszyslak.lighthouse.dataclasses.Configuration
import me.moeszyslak.lighthouse.dataclasses.Permissions
import me.moeszyslak.lighthouse.services.BotStatsService
import java.awt.Color

@PrivilegedIntent
@KordPreview
suspend fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null
    val prefix = System.getenv("DEFAULT_PREFIX") ?: "<none>"

    require(token != null) { "Expected the bot token as an environment variable" }

    bot(token) {
        prefix {
            val configuration = discord.getInjectionObjects(Configuration::class)
            guild?.let { configuration[it.id.value]?.prefix } ?: prefix
        }

        configure {
            allowMentionPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
            generateCommandDocs = true
            entitySupplyStrategy = EntitySupplyStrategy.cacheWithRestFallback
            intents = Intents.all
            permissions(Permissions.STAFF)
        }

        mentionEmbed {
            val configuration = it.discord.getInjectionObjects(Configuration::class)
            val statsService = it.discord.getInjectionObjects(BotStatsService::class)
            val guildConfiguration = configuration[it.guild!!.id.value]

            title = "Lighthouse"
            description = "A simple discord bot that allows members to alert staff"
            color = it.discord.configuration.theme!!.kColor

            thumbnail {
                url = it.discord.kord.getSelf().avatar.url
            }

            addInlineField("Prefix", it.prefix())
            addInlineField("Ping", statsService.ping)

            if (guildConfiguration != null) {
                val adminRole = it.guild!!.getRole(Snowflake(guildConfiguration.adminRoleId))
                val alertRole = it.guild!!.getRole(Snowflake(guildConfiguration.alertRole))
                val alertChannel = it.guild!!.getChannel(Snowflake(guildConfiguration.alertChannel))

                field {
                    name = "Configuration"
                    value = "```" +
                            "Admin Role: ${adminRole.mention}\n" +
                            "Alert Channel: ${alertChannel.mention}\n" +
                            "Alert Role: ${alertRole.mention}" +
                            "```"
                }
            }


            field {
                val versions = it.discord.versions

                name = "Bot Info"
                value = "```" +
                        "Version: 2.0.2\n" +
                        "DiscordKt: ${versions.library}\n" +
                        "Kord: ${versions.kord}\n" +
                        "Kotlin: ${versions.kotlin}" +
                        "```"
            }

            addInlineField("Uptime", statsService.uptime)

            field {
                name = "Source"
                value = "[GitHub](https://github.com/the-programmers-hangout/LogBot)"
                inline = true
            }
        }

    }
}