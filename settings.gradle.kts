dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
         // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "KotlinTest"
rootProject.buildFileName = "build.gradle.kts"
include(":app")
include(":lib_base")
include(":lib_network")
include(":lib_voice")
include(":module_joke")
include(":module_map")
include(":module_setting")
include(":module_voice_setting")
include(":module_app_manager")
include(":module_weather")
include(":module_constellation")
include(":module_developr")
