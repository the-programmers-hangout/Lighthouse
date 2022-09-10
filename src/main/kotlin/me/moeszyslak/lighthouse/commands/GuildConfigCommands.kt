package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.arguments.ChannelArg
import me.jakejmattson.discordkt.arguments.RoleArg
import me.jakejmattson.discordkt.commands.commands
import me.jakejmattson.discordkt.dsl.edit
import me.moeszyslak.lighthouse.dataclasses.Configuration
import me.moeszyslak.lighthouse.dataclasses.GuildConfiguration

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    slash("Configure", "Configure a guild.") {
        execute(ChannelArg("Channel", "Alert Channel"), RoleArg("Role", "Alert Role")) {
            configuration.edit { this[guild.id] = GuildConfiguration(args.first.id, args.second.id) }
            respond("Configuration updated")
        }
    }
}