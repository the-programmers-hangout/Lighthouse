package me.moeszyslak.lighthouse.dataclasses

import dev.kord.core.entity.Guild
import dev.kord.core.entity.Role
import dev.kord.core.entity.channel.Channel
import me.jakejmattson.discordkt.api.dsl.Data

data class Configuration(
    val ownerId: String = "insert-owner-id",
    var prefix: String = "++",
    val guildConfigurations: MutableMap<Long, GuildConfiguration> = mutableMapOf()
) : Data("config/config.json") {

    operator fun get(id: Long) = guildConfigurations[id]
    fun hasGuildConfig(guildId: Long) = guildConfigurations.containsKey(guildId)

    fun setup(guild: Guild, prefix: String, adminRole: Role, alertChannel: Channel, alertRole: Role) {
        if (guildConfigurations[guild.id.value] != null) return

        guildConfigurations[guild.id.value] = GuildConfiguration(
            prefix,
            adminRole.id.value,
            alertChannel.id.value,
            alertRole.id.value
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