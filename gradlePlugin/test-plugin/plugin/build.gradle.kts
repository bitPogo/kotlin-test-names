/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.dependency.Dependency
import tech.antibytes.gradle.ktname.dependency.Dependency as LocalDependency
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import tech.antibytes.gradle.configuration.runtime.AntiBytesMainConfigurationTask

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(LocalDependency.kotlin.gradle)
    implementation(LocalDependency.kotlin.compiler)

    testImplementation(platform(Dependency.jvm.test.junit))
    testImplementation(Dependency.jvm.test.jupiter)
    testImplementation(Dependency.jvm.test.mockk.unit)
    testImplementation(Dependency.jvm.test.kotlin)
    testImplementation(LocalDependency.antibytes.test.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}

afterEvaluate {
    val generateConfig by tasks.creating(AntiBytesMainConfigurationTask::class.java) {
        packageName.set("tech.antibytes.ktname.config")
        stringFields.set(
            mapOf(
                "version" to "0.0.0",
                "group" to "test",
                "artifactId" to "ktname",
                "pluginId" to "test.ktname",
            )
        )
    }

    tasks.withType(KotlinCompile::class.java) {
        this.dependsOn(generateConfig)
        this.mustRunAfter(generateConfig)
    }
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("${buildDir.absolutePath.trimEnd('/')}/generated/antibytes/main/kotlin")
    }
}

gradlePlugin {
    plugins.register("ktname-test") {
        implementationClass = "tech.antibytes.ktname.KTNameCompilerPlugin"
        id = "ktname-test"
        version = "0.0.0"
    }
}
