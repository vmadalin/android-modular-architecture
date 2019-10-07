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

object TestOptions {
    const val IS_RETURN_DEFAULT_VALUES = true
}
