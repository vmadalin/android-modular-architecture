package plugins.graph

import BuildTasksGroups
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ProjectDependencyGraphTask : DefaultTask() {

    @get:OutputFile
    var destinationDir: String? = null

    @TaskAction
    fun resolveLatestVersion() {
        group = BuildTasksGroups.DOCUMENTATION
        description = "Generate project dependency graph as png"
    }
}
