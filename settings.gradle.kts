pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TMDBApp"
include(":app")
include(":data:movie")
include(":core:network")
include(":core:extension")
include(":core:common")
include(":core:navigation")
include(":core:ui")
include(":domain:movie")
include(":feature:movieDetail")
include(":feature:home")
