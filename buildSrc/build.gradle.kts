/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.fabric.io/public")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "3.5.3"
    const val GRADLE_VERSIONS = "0.27.0"
    const val KOTLIN = "1.3.61"
    const val NAVIGATION = "2.1.0"
    const val JACOCO = "0.16.0-SNAPSHOT"
    const val FABRIC = "1.31.2"
    const val DOKKA = "0.10.0"
    const val KTLINT = "0.36.0"
    const val SPOTLESS = "3.26.1"
    const val DETEKT = "1.2.2"
    const val GRAPH_GENERATOR = "0.6.0-SNAPSHOT"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}")
    implementation("com.vanniktech:gradle-dependency-graph-generator-plugin:${PluginsVersions.GRAPH_GENERATOR}")
    implementation("io.fabric.tools:gradle:${PluginsVersions.FABRIC}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginsVersions.SPOTLESS}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
}
