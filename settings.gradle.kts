pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

includeBuild("gradlePlugin/ktname-dependency")

include(
    ":compiler-plugin"
)

buildCache {
    local {
        isEnabled = false
        directory = File(rootDir, "build.gralde.kts-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "ktname"
