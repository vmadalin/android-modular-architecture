import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(BuildDependencies.GRADLE_ANDROID)
        classpath(BuildDependencies.GRADLE_VERSIONS)
        classpath(BuildDependencies.KOTLIN)
        classpath(BuildDependencies.KOTLIN_ALLOPEN)
        classpath(BuildDependencies.NAVIGATION)
        classpath(BuildDependencies.JACOCO)
        classpath(BuildDependencies.FABRIC)
        classpath(BuildDependencies.DOKKA)
    }
}

plugins {
    id(BuildPlugins.SPOTLESS) version BuildPluginsVersions.SPOTLESS
    id(BuildPlugins.DETEKT) version BuildPluginsVersions.DETEKT
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}
