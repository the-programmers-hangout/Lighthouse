group = "me.moeszyslak"
version = Versions.BOT
description = "A simple discord bot that allows members to alert staff"

plugins {
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
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
    const val BOT = "1.0.0"
    const val DISCORDKT = "0.23.0-SNAPSHOT"
}