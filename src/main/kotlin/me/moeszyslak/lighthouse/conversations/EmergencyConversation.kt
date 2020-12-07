package me.moeszyslak.lighthouse.conversations

import com.gitlab.kordlib.kordx.emoji.*
import me.jakejmattson.discordkt.api.dsl.conversation
import java.awt.Color

fun emergencyConversation() = conversation {
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

    if (shouldExecute)
        respond("AHHHHHHHHHHHH ${Emojis.loudspeaker.unicode}")
}