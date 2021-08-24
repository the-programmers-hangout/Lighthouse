package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.api.arguments.ChannelArg
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.commands.commands
import me.moeszyslak.lighthouse.conversations.createConfigurationConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    guildCommand("Setup") {
        description = "Configure a guild to use this bot."
        execute {
            if (configuration.hasGuildConfig(guild.id.value)) {
                respond("Guild configuration exists. To modify it use the commands to set values.")
                return@execute
            }

            createConfigurationConversation(configuration, guild).startPublicly(discord, author, channel)
            respond("${guild.name} setup")
        }
    }

    guildCommand("Prefix") {
        description = "Set the bot prefix."
        execute(EveryArg) {
            if (!configuration.hasGuildConfig(guild.id.value)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val prefix = args.first
            configuration[guild.id.value]?.prefix = prefix
            configuration.save()
            respond("Prefix set to: **$prefix**")
        }
    }

    guildCommand("AdminRole") {
        description = "Set the bot admin role."
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id.value)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id.value]?.adminRoleId = role.id.value
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }

    guildCommand("AlertChannel") {
        description = "Set the bot alert channel."
        execute(ChannelArg) {
            if (!configuration.hasGuildConfig(guild.id.value)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val channel = args.first
            configuration[guild.id.value]?.alertChannel = channel.id.value
            configuration.save()
            respond("Alert channel set to: **${channel.mention}**")
        }
    }

    guildCommand("AlertRole") {
        description = "Set the bot alert role."
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id.value)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val role = args.first
            configuration[guild.id.value]?.alertRole = role.id.value
            configuration.save()
            respond("Alert role set to: **${role.mention}**")
        }
    }
}