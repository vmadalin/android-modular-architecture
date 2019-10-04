rootProject.buildFileName = "build.gradle.kts"

include(
    BuildModules.APP,
    BuildModules.CORE,
    BuildModules.Features.CHARACTERS_LIST,
    BuildModules.Features.CHARACTERS_FAVORITES
)
