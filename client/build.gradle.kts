/*
 * Copyright 2020
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

@file:Suppress("PropertyName", "UNUSED_VARIABLE")

import nebula.plugin.contacts.Contact
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

val ktor_version: String by project

plugins {
    kotlin("multiplatform") version "1.3.71"
    kotlin("plugin.serialization") version "1.3.71"
    id("nebula.contacts") version "5.1.0"
    id("nebula.javadoc-jar") version "17.2.1"
    id("nebula.source-jar") version "17.2.1"
    `maven-publish`
    idea
}

group = "org.jellyfin.api"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    /*
     * To find out how to configure the targets, please follow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                // Setup the Kotlin compiler options for the 'main' compilation:
                jvmTarget = "1.8"
            }
        }
    }

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
        val jvmMain by getting {
            kotlin.srcDir("jvm/src/main")

            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
            }
        }
        val jvmTest by getting {
            kotlin.srcDir("jvm/src/test")

            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.6.2")
            }
        }
    }
}

contacts {
    addPerson("cromefire_@outlook.com", closureOf<Contact> {
        moniker = "Cromefire_"
        github = "cromefire"
    })
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
        }
    }
    repositories {
        maven("https://gitlab.com/api/v4/projects/17693247/packages/maven") {
            name = "gitlab"

            credentials(HttpHeaderCredentials::class.java) {
                val jobToken = System.getenv("CI_JOB_TOKEN")
                if (jobToken != null) {
                    // GitLab CI
                    name = "Job-Token"
                    value = System.getenv("CI_JOB_TOKEN")
                } else {
                    name = "Private-Token"
                    value = System.getenv("GITLAB_TOKEN")
                }
            }
            authentication {
                register("header", HttpHeaderAuthentication::class.java)
            }
        }
    }
}

tasks {
    getByName<KotlinJvmTest>("jvmTest") {
        systemProperties["project.version"] = version

        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
