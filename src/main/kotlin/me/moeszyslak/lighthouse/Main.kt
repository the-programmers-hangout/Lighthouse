package me.moeszyslak.lighthouse

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.gateway.Intent
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.extensions.*
import me.moeszyslak.lighthouse.dataclasses.Configuration
import java.awt.Color
import java.time.Instant

@PrivilegedIntent
@KordPreview
fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null

    bot(token) {
        val configuration = data("config/config.json") { Configuration() }

        prefix { "/" }

        configure {
            mentionAsPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
            intents = Intents(Intent.MessageContent)
            defaultPermissions = Permissions(Permission.ManageMessages)
        }
    }
}
