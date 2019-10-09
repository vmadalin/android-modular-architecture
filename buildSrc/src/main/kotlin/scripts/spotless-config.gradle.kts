import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

configure<SpotlessExtension> {
    format("misc") {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.md", "**/.gitignore", "**/*.yaml", "**/*.yml"),
                    "exclude" to listOf(".gradle/**", ".gradle-cache/**", "**/tools/**", "**/build/**")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target("**/res/**/*.xml")
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt")
        licenseHeaderFile(rootProject.file("COPYRIGHT"))
    }

    kotlinGradle {
        target("**/*.gradle.kts", "*.gradle.kts")
        ktlint("0.33.0").userData(mapOf("indent_size" to "4", "continuation_indent_size" to "4"))
        licenseHeaderFile(rootProject.file("COPYRIGHT"), "import|tasks|apply|plugins|include")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
