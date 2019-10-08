val ktlint by configurations.creating

dependencies {
    ktlint(BuildDependencies.KTLINT)
}

tasks.named("check") {
    dependsOn(ktlint)
}

tasks {
    register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.vmadalin.android.SampleApp"
        args("--android", "src/**/*.kt")
    }

    register<JavaExec>("ktlintFormat") {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.vmadalin.android.SampleApp"
        args("--android", "-F", "src/**/*.kt")
    }
}
