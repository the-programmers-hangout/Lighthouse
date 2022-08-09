package me.moeszyslak.lighthouse.dataclasses

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Role
import dev.kord.core.entity.channel.Channel
import me.jakejmattson.discordkt.dsl.Data

data class Configuration(
    val ownerId: String = "insert-owner-id",
    var prefix: String = "++",
    val guildConfigurations: MutableMap<Snowflake, GuildConfiguration> = mutableMapOf()
) : Data() {
    operator fun get(id: Snowflake) = guildConfigurations[id]
    fun hasGuildConfig(guildId: Snowflake) = guildConfigurations.containsKey(guildId)

    fun setup(guild: Guild, prefix: String, adminRole: Role, alertChannel: Channel, alertRole: Role) {
        if (guildConfigurations[guild.id] != null) return

        guildConfigurations[guild.id] = GuildConfiguration(
            prefix,
            adminRole.id,
            alertChannel.id,
            alertRole.id
        )

        save()
    }
}

data class GuildConfiguration(
    var prefix: String = "++",
    var adminRoleId: Snowflake,
    var alertChannel: Snowflake,
    var alertRole: Snowflake
)