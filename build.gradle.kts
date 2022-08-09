group = "me.moeszyslak"
version = Versions.BOT
description = "A simple discord bot that allows members to alert staff"

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("me.jakejmattson:DiscordKt:${Versions.DISCORDKT}")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    shadowJar {
        archiveFileName.set("Lighthouse.jar")
        manifest {
            attributes(
                "Main-Class" to "me.moeszyslak.lighthouse.MainKt"
            )
        }
    }
}

object Versions {
    const val BOT = "3.0.0"
    const val DISCORDKT = "0.23.2"
}