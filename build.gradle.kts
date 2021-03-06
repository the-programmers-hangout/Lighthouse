group = "me.moeszyslak"
version = Versions.BOT
description = "A simple discord bot that allows members to alert staff"

plugins {
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("com.github.ben-manes.versions") version "0.36.0"
}

repositories {
    mavenCentral()
    jcenter()
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
    const val DISCORDKT = "0.21.3"
}