import tech.antibytes.gradle.ktname.dependency.Dependency
import tech.antibytes.gradle.ktname.dependency.addCustomRepositories

plugins {
    `kotlin-dsl`

    id("tech.antibytes.gradle.ktname.dependency")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    addCustomRepositories()
}

dependencies {
    implementation(Dependency.gradle.dependency)
    implementation(Dependency.gradle.coverage)
    implementation(Dependency.gradle.spotless)
    implementation(Dependency.gradle.publishing)
    implementation(Dependency.gradle.runtimeConfig)
    implementation(Dependency.gradle.projectConfig)
}
