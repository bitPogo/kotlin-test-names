/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.dependency.Dependency
import tech.antibytes.gradle.ktname.dependency.Dependency as LocalDependency
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import tech.antibytes.gradle.configuration.runtime.AntiBytesMainConfigurationTask
import tech.antibytes.gradle.ktname.config.KTNameCompilerPluginConfiguration

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    id("tech.antibytes.gradle.publishing")
    id("tech.antibytes.gradle.coverage")
}

group = KTNameCompilerPluginConfiguration.group

antiBytesPublishing {
    packageConfiguration = KTNameCompilerPluginConfiguration.publishing.packageConfiguration
    repositoryConfiguration = KTNameCompilerPluginConfiguration.publishing.repositories
    versioning = KTNameCompilerPluginConfiguration.publishing.versioning
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
        /*stringFields.set(
            mapOf(
                "version" to project.version as String,
                "group" to KTNameCompilerPluginConfiguration.group,
                "artifactId" to KTNameCompilerPluginConfiguration.id,
                "pluginId" to KTNameCompilerPluginConfiguration.pluginId,
            )
        )*/
    }

    tasks.withType(KotlinCompile::class.java) {
        this.dependsOn(generateConfig)
        this.mustRunAfter(generateConfig)
    }
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("${buildDir.absolutePath.trimEnd('/')}/generated/antibytes/main")
    }
}

gradlePlugin {
    plugins.register(KTNameCompilerPluginConfiguration.pluginId) {
        group = KTNameCompilerPluginConfiguration.group
        id = KTNameCompilerPluginConfiguration.pluginId
        implementationClass = "tech.antibytes.ktname.KTNameCompilerPlugin"
        displayName = "${id}.gradle.plugin"
        description = "KTName Compiler Plugin"
        version = project.version
    }
}
