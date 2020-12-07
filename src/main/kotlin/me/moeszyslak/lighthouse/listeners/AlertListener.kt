package me.moeszyslak.lighthouse.listeners

import com.gitlab.kordlib.core.event.message.MessageCreateEvent
import me.jakejmattson.discordkt.api.dsl.listeners
import me.jakejmattson.discordkt.api.extensions.toSnowflake
import me.moeszyslak.lighthouse.conversations.createEmergencyConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun alertListener(configuration: Configuration) = listeners {
    on<MessageCreateEvent> {
        val guild = getGuild() ?: return@on
        val guildConfiguration = configuration[guild.id.longValue] ?: return@on

        if (message.mentionedRoleIds.contains(guildConfiguration.alertRole.toSnowflake())) {
            createEmergencyConversation(guildConfiguration).startPublicly(discord, message.author!!, message.channel.asChannel())
        }
    }
}