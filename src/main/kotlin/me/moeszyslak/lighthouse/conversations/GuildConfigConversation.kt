package me.moeszyslak.lighthouse.conversations

import dev.kord.core.entity.Guild
import me.jakejmattson.discordkt.arguments.ChannelArg
import me.jakejmattson.discordkt.arguments.EveryArg
import me.jakejmattson.discordkt.arguments.RoleArg
import me.jakejmattson.discordkt.conversations.conversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun createConfigurationConversation(configuration: Configuration, guild: Guild) = conversation {
    val prefix = prompt(EveryArg, "Bot prefix:")
    val adminRole = prompt(RoleArg, "Admin role:")
    val alertChannel = prompt(ChannelArg, "Alert Channel:")
    val alertRole = prompt(RoleArg, "Alert Role:")

    configuration.setup(guild, prefix, adminRole, alertChannel, alertRole)
}