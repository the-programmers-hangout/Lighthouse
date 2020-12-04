package me.moeszyslak.lighthouse.listeners

import com.gitlab.kordlib.core.behavior.getChannelOf
import com.gitlab.kordlib.core.entity.channel.TextChannel
import com.gitlab.kordlib.core.event.message.MessageCreateEvent
import me.jakejmattson.discordkt.api.dsl.listeners
import me.jakejmattson.discordkt.api.extensions.toSnowflake
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun alertListener(configuration: Configuration) = listeners {
    on<MessageCreateEvent> {
        val guild = getGuild() ?: return@on
        val guildConfiguration = configuration[guild.id.longValue] ?: return@on

        if (message.content.startsWith(guildConfiguration.alertString) ||
                message.mentionedRoleIds.contains(guildConfiguration.alertRole.toSnowflake())) {

            val alertChannel = guild.getChannelOf<TextChannel>(guildConfiguration.alertChannel.toSnowflake())
            val author = message.author ?: return@on

            alertChannel.createMessage("@here :: ${author.mention} " +
                    "just created a emergency alert in ${message.channel.mention} :: " +
                    " https://discord.com/channels/${guild.id.value}/${message.channel.id.value}/${message.id.value}")
        }

    }
}