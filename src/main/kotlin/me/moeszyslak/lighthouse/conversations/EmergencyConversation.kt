package me.moeszyslak.lighthouse.conversations

import com.gitlab.kordlib.core.behavior.getChannelOf
import com.gitlab.kordlib.core.entity.channel.TextChannel
import com.gitlab.kordlib.kordx.emoji.*
import me.jakejmattson.discordkt.api.dsl.conversation
import me.jakejmattson.discordkt.api.extensions.toSnowflake
import me.moeszyslak.lighthouse.dataclasses.GuildConfiguration
import java.awt.Color

fun createEmergencyConversation(guildConfiguration: GuildConfiguration) = conversation {
    val shouldExecute = promptReaction(mapOf(Emojis.whiteCheckMark.toReaction() to true, Emojis.x.toReaction() to false)) {
        color = Color.red
        title = "Confirm Emergency Ping"
        description = "This will ping all online staff members. Make sure this is what you want."

        field {
            name = "Uses"
            value = """
                * NSFW/gore content
            """.trimIndent()
        }

        field {
            value = """
                ${Emojis.whiteCheckMark.unicode} - Yes. Ping them all.
                ${Emojis.x.unicode} - No, my mistake.
            """.trimIndent()
        }
    }

    if (shouldExecute) {
        val message = channel.getMessageOrNull(previousUserMessageId)!!
        val guild = message.getGuild()
        val alertChannel = guild.getChannelOf<TextChannel>(guildConfiguration.alertChannel.toSnowflake())

        alertChannel.createMessage("@here :: ${user.mention} " +
            "just created a emergency alert in ${message.channel.mention} :: " +
            " https://discord.com/channels/${guild.id.value}/${message.channel.id.value}/${message.id.value}")
    }
}