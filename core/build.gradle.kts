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
import extensions.implementation
import extensions.kapt
import extensions.getLocalProperty
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.vmadalin.core.annotations.OpenClass")
}

android {
    buildTypes.forEach {
        try {
            it.buildConfigStringField("MARVEL_API_BASE_URL", "https://gateway.marvel.com/")
            it.buildConfigStringField("MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
            it.buildConfigStringField("MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))

            it.buildConfigBooleanField("MARVEL_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("MARVEL_DATABASE_NAME", "characters-db")
            it.buildConfigIntField("MARVEL_DATABASE_VERSION", 1)
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'marvel.key.public' and " +
                "'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' " +
                "to obtain them.")
        }
    }
}

dependencies {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)
    implementation(Dependencies.CRASHLYTICS)

    kapt(AnnotationProcessorsDependencies.DATABINDING)
    kapt(AnnotationProcessorsDependencies.ROOM)
}
