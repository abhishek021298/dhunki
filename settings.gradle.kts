pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven (url = uri("https://jitpack.io") )
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
    }
}
rootProject.name = "Techno Music"
include(":app")
