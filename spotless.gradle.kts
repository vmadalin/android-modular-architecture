import com.diffplug.gradle.spotless.SpotlessTask

apply(plugin = "com.diffplug.gradle.spotless")

tasks.withType<SpotlessTask> {
    kotlin {
        target("**/*.kt", "**/*.kts", "*.xml")
        //licenseHeaderFile rootProject.file('COPYRIGHT')
    }
}
