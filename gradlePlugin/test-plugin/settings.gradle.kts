pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

includeBuild("ktname-test-dependency")
include("plugin")

buildCache {
    local {
        isEnabled = false
        directory = File(rootDir, "build.gralde.kts-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "test-plugin"
