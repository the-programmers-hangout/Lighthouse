package me.moeszyslak.lighthouse.conversations

import dev.kord.core.entity.Guild
import me.jakejmattson.discordkt.api.arguments.ChannelArg
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.conversations.conversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun createConfigurationConversation(configuration: Configuration, guild: Guild) = conversation {
    val prefix = prompt(EveryArg, "Bot prefix:")
    val adminRole = prompt(RoleArg, "Admin role:")
    val alertChannel = prompt(ChannelArg, "Alert Channel:")
    val alertRole = prompt(RoleArg, "Alert Role:")

    configuration.setup(guild, prefix, adminRole, alertChannel, alertRole)
}