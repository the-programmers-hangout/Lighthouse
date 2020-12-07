package me.moeszyslak.lighthouse.conversations

import com.gitlab.kordlib.core.entity.Guild
import me.jakejmattson.discordkt.api.arguments.*
import me.jakejmattson.discordkt.api.dsl.conversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

class ConfigurationConversation(private val configuration: Configuration) {
    fun createConfigurationConversation(guild: Guild) = conversation {
        val prefix = promptMessage(EveryArg, "Bot prefix:")
        val adminRole = promptMessage(RoleArg, "Admin role:")
        val alertChannel = promptMessage(ChannelArg, "Alert Channel:")
        val alertRole = promptMessage(RoleArg, "Alert Role:")

        configuration.setup(guild, prefix, adminRole, alertChannel, alertRole)
    }
}