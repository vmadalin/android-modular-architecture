import io.gitlab.arturbosch.detekt.Detekt

apply(plugin = "io.gitlab.arturbosch.detekt")

tasks.withType<Detekt> {
    config = files("$rootDir/.detekt/config.yml")
    setExcludes(listOf(".*build.*",".*/resources/.*",".*/tmp/.*"))
}
