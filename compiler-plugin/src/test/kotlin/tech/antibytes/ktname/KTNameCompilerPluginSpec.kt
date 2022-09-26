/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KTNameCompilerPluginSpec {
    @Test
    fun `It fulfils KotlinCompilerPluginSupportPlugin`() {
        val plugin: Any = KTNameCompilerPlugin()

        assertTrue {
            plugin is KotlinCompilerPluginSupportPlugin
        }
    }
}
