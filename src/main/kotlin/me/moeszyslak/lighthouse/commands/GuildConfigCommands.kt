package me.moeszyslak.lighthouse.commands

import me.jakejmattson.discordkt.api.arguments.ChannelArg
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.dsl.commands
import me.moeszyslak.lighthouse.conversations.ConfigurationConversation
import me.moeszyslak.lighthouse.dataclasses.Configuration
import me.moeszyslak.lighthouse.services.PermissionLevel
import me.moeszyslak.lighthouse.services.requiredPermissionLevel

fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    guildCommand("Setup") {
        description = "Configure a guild to use this bot."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute {
            if (configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Guild configuration exists. To modify it use the commands to set values.")
                return@execute
            }
            ConfigurationConversation(configuration)
                    .createConfigurationConversation(guild)
                    .startPublicly(discord, author, channel)
            respond("${guild.name} setup")
        }
    }

    guildCommand("Prefix") {
        description = "Set the bot prefix."
        requiredPermissionLevel = PermissionLevel.Administrator
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

    guildCommand("StaffRole") {
        description = "Set the bot staff role."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id.longValue)) {
                respond("Please run the **setup** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id.longValue]?.staffRoleId = role.id.longValue
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }

    guildCommand("AdminRole") {
        description = "Set the bot admin role."
        requiredPermissionLevel = PermissionLevel.Administrator
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
        requiredPermissionLevel = PermissionLevel.Administrator
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

    guildCommand("AlertString") {
        description = "Set the bot alert string."
        requiredPermissionLevel = PermissionLevel.Administrator
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
        requiredPermissionLevel = PermissionLevel.Administrator
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