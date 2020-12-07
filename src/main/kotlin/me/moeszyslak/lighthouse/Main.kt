package me.moeszyslak.lighthouse

import com.gitlab.kordlib.common.entity.Snowflake
import com.gitlab.kordlib.core.any
import com.gitlab.kordlib.core.entity.Member
import com.gitlab.kordlib.gateway.Intents
import com.gitlab.kordlib.gateway.PrivilegedIntent
import me.jakejmattson.discordkt.api.dsl.bot
import me.moeszyslak.lighthouse.dataclasses.Configuration
import me.moeszyslak.lighthouse.services.BotStatsService
import java.awt.Color
import java.io.ObjectInputFilter

@PrivilegedIntent
suspend fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null
    val prefix = System.getenv("DEFAULT_PREFIX") ?: "<none>"

    require(token != null) { "Expected the bot token as an environment variable" }

    bot(token) {
        prefix {
            val configuration = discord.getInjectionObjects(Configuration::class)

            guild?.let { configuration[it.id.longValue]?.prefix } ?: prefix
        }

        configure {
            allowMentionPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
        }

        mentionEmbed {
            val configuration = it.discord.getInjectionObjects(Configuration::class)
            val statsService = it.discord.getInjectionObjects(BotStatsService::class)
            val guildConfiguration = configuration[it.guild!!.id.longValue]

            title = "Lighthouse"
            description = "A simple discord bot that allows members to alert staff"
            color = it.discord.configuration.theme

            thumbnail {
                url = it.discord.api.getSelf().avatar.url
            }

            field {
                name = "Prefix"
                value = it.prefix()
                inline = true
            }

            field {
                name = "Ping"
                value = statsService.ping
                inline = true
            }

            if (guildConfiguration != null) {
                val adminRole = it.guild!!.getRole(Snowflake(guildConfiguration.adminRoleId))
                val alertRole = it.guild!!.getRole(Snowflake(guildConfiguration.alertRole))
                val alertChannel = it.guild!!.getChannel(Snowflake(guildConfiguration.alertChannel))

                field {
                    name = "Configuration"
                    value = "```" +
                            "Admin Role: ${adminRole.mention}\n" +
                            "Alert Channel: ${alertChannel.mention}\n" +
                            "Alert String: ${guildConfiguration.alertString}\n" +
                            "Alert Role: ${alertRole.mention}" +
                            "```"
                }
            }


            field {
                val versions = it.discord.versions

                name = "Bot Info"
                value = "```" +
                        "Version: 1.0.0\n" +
                        "DiscordKt: ${versions.library}\n" +
                        "Kord: ${versions.kord}\n" +
                        "Kotlin: ${versions.kotlin}" +
                        "```"
            }

            field {
                name = "Uptime"
                value = statsService.uptime
                inline = true
            }

            field {
                name = "Source"
                value = "[GitHub](https://github.com/the-programmers-hangout/LogBot)"
                inline = true
            }
        }

        permissions {
            val configuration = discord.getInjectionObjects(Configuration::class)
            suspend fun Member.hasPermission() = roles.any { it.id.longValue == configuration[guild.id.longValue]?.adminRoleId } || isOwner()

            if (guild != null) {
                val member = guild!!.getMember(user.id)
                member.hasPermission()
            }
            else
                false
        }

        intents {
            Intents.all.intents.forEach {
                +it
            }
        }
    }
}