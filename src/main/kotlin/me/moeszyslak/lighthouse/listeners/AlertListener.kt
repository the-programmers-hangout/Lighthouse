package me.moeszyslak.lighthouse.listeners

import dev.kord.core.event.message.MessageCreateEvent
import me.jakejmattson.discordkt.dsl.listeners
import me.jakejmattson.discordkt.extensions.toSnowflake
import me.moeszyslak.lighthouse.conversations.createEmergencyConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun alertListener(configuration: Configuration) = listeners {
    on<MessageCreateEvent> {
        val guild = getGuild() ?: return@on
        val guildConfiguration = configuration[guild.id] ?: return@on

        if (message.mentionedRoleIds.contains(guildConfiguration.alertRole)) {
            createEmergencyConversation(guildConfiguration, message).startPublicly(
                discord,
                message.author!!,
                message.channel.asChannel()
            )
        }
    }
}