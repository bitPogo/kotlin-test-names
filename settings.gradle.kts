pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    includeBuild("gradlePlugin/test-plugin")
}

includeBuild("gradlePlugin/ktname-dependency")

include(
    ":compiler-plugin",
    ":example"
)

buildCache {
    local {
        isEnabled = false
        directory = File(rootDir, "build.gralde.kts-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "ktname"
