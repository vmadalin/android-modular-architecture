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

object AndroidConfig {
    const val APPLICATION_ID = "com.vmadalin.android"

    const val BUILD_TOOLS_VERSION = "29.0.0"
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true

    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
    val TEST_INSTRUMENTATION_RUNNER_ARGUMENTS = mapOf(
        "leakcanary.FailTestOnLeakRunListener" to "listener"
    )
}

interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
    val isCrashlyticsEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isCrashlyticsEnabled = false
    override val isMinifyEnabled = false
    override val isTestCoverageEnabled = true

    val applicationIdSuffix = ".debug"
    val versionNameSuffix = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isCrashlyticsEnabled = true
    override val isMinifyEnabled = true
    override val isTestCoverageEnabled = false
}
