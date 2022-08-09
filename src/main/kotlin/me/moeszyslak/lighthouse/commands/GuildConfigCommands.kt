package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.arguments.ChannelArg
import me.jakejmattson.discordkt.arguments.RoleArg
import me.jakejmattson.discordkt.commands.commands
import me.moeszyslak.lighthouse.dataclasses.Configuration
import me.moeszyslak.lighthouse.dataclasses.GuildConfiguration

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    slash("Configure") {
        description = "Configure a guild."
        execute(ChannelArg("Channel", "Alert Channel"), RoleArg("Role", "Alert Role")) {
            configuration[guild.id] = GuildConfiguration(args.first.id, args.second.id)
            respond("Configuration updated")
        }
    }
}