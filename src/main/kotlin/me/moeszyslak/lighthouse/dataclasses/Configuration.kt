package me.moeszyslak.lighthouse.dataclasses

import com.gitlab.kordlib.core.entity.*
import com.gitlab.kordlib.core.entity.channel.Channel
import me.jakejmattson.discordkt.api.dsl.Data

data class Configuration(
    val ownerId: String = "insert-owner-id",
    var prefix: String = "++",
    val guildConfigurations: MutableMap<Long, GuildConfiguration> = mutableMapOf()
) : Data("config/config.json") {

    operator fun get(id: Long) = guildConfigurations[id]
    fun hasGuildConfig(guildId: Long) = guildConfigurations.containsKey(guildId)

    fun setup(guild: Guild, prefix: String, adminRole: Role, alertChannel: Channel, alertRole: Role) {
        if (guildConfigurations[guild.id.longValue] != null) return

        guildConfigurations[guild.id.longValue] = GuildConfiguration(
            prefix,
            adminRole.id.longValue,
            alertChannel.id.longValue,
            alertRole.id.longValue
        )

        save()
    }
}

data class GuildConfiguration(
    var prefix: String = "++",
    var adminRoleId: Long,
    var alertChannel: Long,
    var alertRole: Long
)