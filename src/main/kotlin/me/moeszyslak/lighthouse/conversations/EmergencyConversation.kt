package me.moeszyslak.lighthouse.conversations

import dev.kord.common.kColor
import dev.kord.core.behavior.getChannelOf
import dev.kord.core.entity.Message
import dev.kord.core.entity.channel.GuildMessageChannel
import dev.kord.x.emoji.Emojis
import me.jakejmattson.discordkt.api.conversations.conversation
import me.jakejmattson.discordkt.api.extensions.toSnowflake
import me.moeszyslak.lighthouse.dataclasses.GuildConfiguration
import java.awt.Color

fun createEmergencyConversation(guildConfiguration: GuildConfiguration, invoke: Message) = conversation {
    val shouldExecute = promptButton<Boolean> {
        embed {
            color = Color.red.kColor
            title = "Confirm Emergency Ping"
            description =
                "This will ping all online staff members. Make sure this is what you want as invalid pings may result in infractions."

            field {
                name = "Uses"
                value = """
                * NSFW/gore content
                * Unnoticed raids
            """.trimIndent()
            }

            field {
                value = """
                ${Emojis.whiteCheckMark.unicode} - Yes. Ping them all.
                ${Emojis.x.unicode} - No, my mistake.
            """.trimIndent()
            }
        }

        buttons {
            button("Yes", Emojis.whiteCheckMark, true)
            button("No", Emojis.x, false)
        }

    }

    channel.getMessage(previousBotMessageId).delete()

    if (shouldExecute) {
        val guild = invoke.getGuild()
        val alertChannel = guild.getChannelOf<GuildMessageChannel>(guildConfiguration.alertChannel.toSnowflake())
        alertChannel.createMessage(
            "@here :: ${user.mention} " +
                    "just created a emergency alert in ${invoke.channel.mention} :: " +
                    " https://discord.com/channels/${guild.id.value}/${invoke.channel.id.value}/${invoke.id.value}"
        )
    }
}