import tech.antibytes.gradle.dependency.Version
import tech.antibytes.gradle.ktname.dependency.addCustomRepositories
import tech.antibytes.gradle.ktname.dependency.ensureKotlinVersion

plugins {
    id("tech.antibytes.gradle.ktname.dependency")

    id("tech.antibytes.gradle.dependency")

    id("tech.antibytes.gradle.ktname.script.quality-spotless")

    kotlin("jvm")
}

allprojects {
    repositories {
        addCustomRepositories()
        mavenCentral()
        google()
        jcenter()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }

    ensureKotlinVersion(Version.kotlin.language)
}
