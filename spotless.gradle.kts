import com.diffplug.gradle.spotless.SpotlessExtension

apply(plugin = "com.diffplug.gradle.spotless")

tasks.withType<SpotlessExtension> {
    kotlin {
        target("**/*.kt", "**/*.kts", "*.xml")
        //licenseHeaderFile(rootProject.file("COPYRIGHT"))
    }
}
