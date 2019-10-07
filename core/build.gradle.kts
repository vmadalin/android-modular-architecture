plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    buildTypes.forEach {
        try {
            it.buildConfigField("String", "MARVEL_API_BASE_URL", "\"https://gateway.marvel.com\"")
            it.buildConfigField("String", "MARVEL_API_KEY_PUBLIC", "\"${getLocalProperty("marvel.key.public")}\"")
            it.buildConfigField("String", "MARVEL_API_KEY_PRIVATE", "\"${getLocalProperty("marvel.key.private")}\"")

            it.buildConfigField("boolean", "MARVEL_DATABASE_EXPORT_SCHEMA", "false")
            it.buildConfigField("String", "MARVEL_DATABASE_NAME", "\"characters-db\"")
            it.buildConfigField("int", "MARVEL_DATABASE_VERSION", "1")
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'marvel.key.public' and 'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' to obtain them.")
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

//apply(from = "$rootDir/common/android-library.gradle.kts")

//android {
//    buildTypes.each {
//        try {
//            it.buildConfigField("String", "MARVEL_API_BASE_URL", "https://gateway.marvel.com")
//            it.buildConfigField("String", "MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
//            it.buildConfigField("String", "MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))
//
//            it.buildConfigField("boolean", "MARVEL_DATABASE_EXPORT_SCHEMA", "false")
//            it.buildConfigField("String", "MARVEL_DATABASE_NAME", "characters-db")
//            it.buildConfigField("int", "MARVEL_DATABASE_VERSION", "1")
//        } catch (ignored: Exception) {
//            throw InvalidUserDataException("You should define 'marvel.key.public' and 'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' to obtain them.")
//        }
//    }
//}
