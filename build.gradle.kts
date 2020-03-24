@file:Suppress("PropertyName", "UNUSED_VARIABLE")

val ktor_version: String by project

plugins {
    kotlin("multiplatform") version "1.3.71"
    kotlin("plugin.serialization") version "1.3.71"
    idea
}

group = "org.jellyfin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    /*
     * To find out how to configure the targets, please follow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    jvm("android")

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("common/src/main")

            dependencies {
                implementation(kotlin("stdlib-common"))
                api("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }
        val commonTest by getting {
            kotlin.srcDir("common/src/test")

            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            kotlin.srcDir("jvm/src/main")

            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
            }
        }
        val androidTest by getting {
            kotlin.srcDir("jvm/src/test")

            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
    }
}
