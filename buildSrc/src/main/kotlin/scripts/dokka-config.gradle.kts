import org.jetbrains.dokka.gradle.DokkaTask

apply(plugin = "org.jetbrains.dokka")

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"

    reportUndocumented = true
    skipEmptyPackages = true
}
