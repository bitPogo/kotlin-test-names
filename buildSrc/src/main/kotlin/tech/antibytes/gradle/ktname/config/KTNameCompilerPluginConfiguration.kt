/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.gradle.ktname.config

import tech.antibytes.gradle.publishing.api.PackageConfiguration
import tech.antibytes.gradle.publishing.api.PomConfiguration

object KTNameCompilerPluginConfiguration {
    const val group = "tech.antibytes.ktname"
    const val id = "compiler-plugin"
    const val pluginId = "$group.$id"

    val publishing = Publishing

    object Publishing : KTNamePublishingConfiguration() {
        val packageConfiguration = PackageConfiguration(
            pom = PomConfiguration(
                name = "KTName Compiler Plugin",
                description = "Use Backticks for JS and Android tests!",
                year = 2022,
                url = "https://$gitHubRepositoryPath",
            ),
            developers = listOf(developer),
            license = license,
            scm = sourceControl,
        )
    }
}
