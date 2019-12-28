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

/**
 * Configuration of all gradle build plugins
 */
object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"

    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_ALLOPEN = "kotlin-allopen"

    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val JACOCO = "com.vanniktech.android.junit.jacoco"
    const val GRAPH_GENERATOR = "com.vanniktech.dependency.graph.generator"
    const val FABRIC = "io.fabric"

    const val DETEKT = "plugins.detekt"
    const val DOKKA = "plugins.dokka"
    const val GIT_HOOKS = "plugins.git-hooks"
    const val KTLINT = "plugins.ktlint"
    const val SPOTLESS = "plugins.spotless"
    const val UPDATE_DEPENDENCIES = "plugins.update-dependencies"
}
