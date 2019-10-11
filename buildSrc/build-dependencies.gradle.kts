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

object BuildVersions {
    const val GRADLE_ANDROID = "3.4.2"
    const val GRADLE_VERSIONS = "0.22.0"
    const val KOTLIN = "1.3.50"
    const val NAVIGATION = "2.1.0-beta02"
    const val JACOCO = "0.15.0"
    const val FABRIC = "1.31.0"
    const val DOKKA = "0.9.18"
    const val KTLINT = "0.34.2"
    const val SPOTLESS = "3.24.1"
    const val DETEKT = "1.0.1"
}

extra["gradleAndroid"] = "com.android.tools.build:gradle:${BuildVersions.GRADLE_ANDROID}"
extra["gradleVersions"] = "com.github.ben-manes:gradle-versions-plugin:${BuildVersions.GRADLE_VERSIONS}"
extra["kotlin"] = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildVersions.KOTLIN}"
extra["kotlinAllOpen"] = "org.jetbrains.kotlin:kotlin-allopen:${BuildVersions.KOTLIN}"
extra["navigation"] = "androidx.navigation:navigation-safe-args-gradle-plugin:${BuildVersions.NAVIGATION}"
extra["jacoco"] = "com.vanniktech:gradle-android-junit-jacoco-plugin:${BuildVersions.JACOCO}"
extra["fabric"] = "io.fabric.tools:gradle:${BuildVersions.FABRIC}"
extra["dokka"] = "org.jetbrains.dokka:dokka-gradle-plugin:${BuildVersions.DOKKA}"
extra["ktlint"] = "com.pinterest:ktlint:${BuildVersions.KTLINT}"
extra["spotless"] = "com.diffplug.spotless:spotless-plugin-gradle:${BuildVersions.SPOTLESS}"
extra["detekt"] = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${BuildVersions.DETEKT}"
