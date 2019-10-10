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

import utils.createFabricProperties
import dependencies.Dependencies
import dependencies.DebugDependencies
import dependencies.AnnotationProcessorsDependencies

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.FABRIC)
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.vmadalin.core.annotations.OpenClass")
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = AndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments = AndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled

            buildConfigField("boolean", "ENABLE_CRASHLYTICS", BuildTypeRelease.isCrashlyticsEnabled.toString())
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix

            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled

            buildConfigField("boolean", "ENABLE_CRASHLYTICS", BuildTypeDebug.isCrashlyticsEnabled.toString())
        }
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.CHARACTERS_LIST,
        BuildModules.Features.CHARACTERS_FAVORITES
    )

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        disable("GoogleAppIndexingWarning")
        isCheckAllWarnings = true
        //isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }
}

junitJacoco {
    jacocoVersion = BuildDependencies.JACOCO
    includeNoLocationClasses = true
}

afterEvaluate {
    createFabricProperties(this)
}

dependencies {
    implementation(project(BuildModules.CORE))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.CRASHLYTICS)
    implementation(Dependencies.PLAY_CORE)

    debugImplementation(DebugDependencies.LEAKCANARY)

    kapt(AnnotationProcessorsDependencies.DAGGER)
    kapt(AnnotationProcessorsDependencies.DATABINDING)

    addTestsDependencies()
}
