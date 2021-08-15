package me.moeszyslak.lighthouse.listeners

import dev.kord.core.event.message.MessageCreateEvent
import me.jakejmattson.discordkt.api.dsl.listeners
import me.jakejmattson.discordkt.api.extensions.toSnowflake
import me.moeszyslak.lighthouse.conversations.createEmergencyConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun alertListener(configuration: Configuration) = listeners {
    on<MessageCreateEvent> {
        val guild = getGuild() ?: return@on
        val guildConfiguration = configuration[guild.id.value] ?: return@on

        if (message.mentionedRoleIds.contains(guildConfiguration.alertRole.toSnowflake())) {
            createEmergencyConversation(guildConfiguration, message).startPublicly(
                discord,
                message.author!!,
                message.channel.asChannel()
            )
        }
    }
}