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

val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"

    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_ALLOPEN = "kotlin-allopen"

    const val COMMON_ANDROID_LIBRARY = "common-android-library"
    const val COMMON_ANDROID_DYNAMIC_FEATURE = "common-android-dynamic-feature"

    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val SPOTLESS = "com.diffplug.gradle.spotless"
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val JACOCO = "com.vanniktech.android.junit.jacoco"
    const val FABRIC = "io.fabric"
}

extra["pluginAndroidApplication"] = BuildPlugins.ANDROID_APPLICATION
extra["pluginAndroidDynamicFeature"] = BuildPlugins.ANDROID_DYNAMIC_FEATURE
extra["pluginAndroidLibrary"] = BuildPlugins.ANDROID_LIBRARY
extra["pluginKotlinAndroid"] = BuildPlugins.KOTLIN_ANDROID
extra["pluginKotlinAndroidExtensions"] = BuildPlugins.KOTLIN_ANDROID_EXTENSIONS
extra["pluginKapt"] = BuildPlugins.KOTLIN_KAPT
extra["pluginCommonAndroidLibrary"] = BuildPlugins.COMMON_ANDROID_LIBRARY
extra["pluginCommonAndroidDynamicFeature"] = BuildPlugins.COMMON_ANDROID_DYNAMIC_FEATURE
extra["pluginNavigationSafeArgs"] = BuildPlugins.NAVIGATION_SAFE_ARGS
extra["pluginSpotless"] = BuildPlugins.SPOTLESS
extra["pluginDetekt"] = BuildPlugins.DETEKT
extra["pluginJacoco"] = BuildPlugins.JACOCO
extra["pluginFabric"] = BuildPlugins.FABRIC
