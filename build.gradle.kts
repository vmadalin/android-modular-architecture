import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()

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
    repositories.applyDefault()

//    apply(from = "detekt.gradle.kts")
//    apply(from = "dokka.gradle.kts")
//    apply(from = "git-hooks.gradle.kts")
//    apply(from = "ktlint.gradle.kts")
//    apply(from = "spotless.gradle.kts")
//    apply(from = "update-dependencies.gradle.kts")
}

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}
