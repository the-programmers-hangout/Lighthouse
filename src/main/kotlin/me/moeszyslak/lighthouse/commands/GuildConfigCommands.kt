package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.arguments.ChannelArg
import me.jakejmattson.discordkt.arguments.EveryArg
import me.jakejmattson.discordkt.arguments.RoleArg
import me.jakejmattson.discordkt.commands.commands
import me.moeszyslak.lighthouse.conversations.createConfigurationConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    text("Setup") {
        description = "Configure a guild to use this bot."
        execute {
            if (configuration.hasGuildConfig(guild.id)) {
                respond("Guild configuration exists. To modify it use the commands to set values.")
                return@execute
            }

            createConfigurationConversation(configuration, guild).startPublicly(discord, author, channel)
            respond("${guild.name} setup")
        }
    }

    text("Prefix") {
        description = "Set the bot prefix."
        execute(EveryArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val prefix = args.first
            configuration[guild.id]?.prefix = prefix
            configuration.save()
            respond("Prefix set to: **$prefix**")
        }
    }

    text("AdminRole") {
        description = "Set the bot admin role."
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id]?.adminRoleId = role.id
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }

    text("AlertChannel") {
        description = "Set the bot alert channel."
        execute(ChannelArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val channel = args.first
            configuration[guild.id]?.alertChannel = channel.id
            configuration.save()
            respond("Alert channel set to: **${channel.mention}**")
        }
    }

    text("AlertRole") {
        description = "Set the bot alert role."
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val role = args.first
            configuration[guild.id]?.alertRole = role.id
            configuration.save()
            respond("Alert role set to: **${role.mention}**")
        }
    }
}