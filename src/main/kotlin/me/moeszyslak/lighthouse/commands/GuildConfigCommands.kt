package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.api.arguments.*
import me.jakejmattson.discordkt.api.dsl.commands
import me.moeszyslak.lighthouse.conversations.*
import me.moeszyslak.lighthouse.dataclasses.Configuration

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    guildCommand("Setup") {
        description = "Configure a guild to use this bot."
        execute {
            if (configuration.hasGuildConfig(guild.id.longValue)) {
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
            if (!configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val prefix = args.first
            configuration[guild.id.longValue]?.prefix = prefix
            configuration.save()
            respond("Prefix set to: **$prefix**")
        }
    }

    guildCommand("AdminRole") {
        description = "Set the bot admin role."
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id.longValue]?.adminRoleId = role.id.longValue
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }

    guildCommand("AlertChannel") {
        description = "Set the bot alert channel."
        execute(ChannelArg) {
            if (!configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val channel = args.first
            configuration[guild.id.longValue]?.alertChannel = channel.id.longValue
            configuration.save()
            respond("Alert channel set to: **${channel.mention}**")
        }
    }

    guildCommand("AlertRole") {
        description = "Set the bot alert role."
        execute(ChannelArg) {
            if (!configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }

            val role = args.first
            configuration[guild.id.longValue]?.alertRole = role.id.longValue
            configuration.save()
            respond("Alert role set to: **${role.mention}**")
        }
    }
}