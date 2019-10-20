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

import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.addTestsDependencies
import extensions.implementation
import extensions.kapt
import extensions.getLocalProperty
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
    }

    buildTypes.forEach {
        try {
            it.buildConfigStringField("MARVEL_API_BASE_URL", "https://gateway.marvel.com")
            it.buildConfigStringField("MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
            it.buildConfigStringField("MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))

            it.buildConfigBooleanField("MARVEL_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("MARVEL_DATABASE_NAME", "characters-db")
            it.buildConfigIntField("MARVEL_DATABASE_VERSION", 1)
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'marvel.key.public' and 'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' to obtain them.")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.libraryCreate(this)
        ProductFlavorQA.libraryCreate(this)
        ProductFlavorProduction.libraryCreate(this)
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.PAGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.CRASHLYTICS)

    kapt(AnnotationProcessorsDependencies.DAGGER)
    kapt(AnnotationProcessorsDependencies.DATABINDING)
    kapt(AnnotationProcessorsDependencies.GLIDE)
    kapt(AnnotationProcessorsDependencies.ROOM)

    addTestsDependencies()
}
