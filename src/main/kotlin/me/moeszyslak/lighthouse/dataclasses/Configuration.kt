package me.moeszyslak.lighthouse.dataclasses

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.Serializable
import me.jakejmattson.discordkt.dsl.Data
import me.jakejmattson.discordkt.dsl.edit

@Serializable
data class Configuration(val guildConfigurations: MutableMap<Snowflake, GuildConfiguration> = mutableMapOf()) : Data() {
    operator fun get(id: Snowflake) = guildConfigurations[id]
    operator fun set(id: Snowflake, value: GuildConfiguration) = edit { guildConfigurations[id] = value }
}

@Serializable
data class GuildConfiguration(val alertChannel: Snowflake, val alertRole: Snowflake)