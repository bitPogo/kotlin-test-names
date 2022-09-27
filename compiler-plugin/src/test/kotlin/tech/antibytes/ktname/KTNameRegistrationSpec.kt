/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

import io.mockk.mockk
import kotlin.test.assertTrue
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.junit.jupiter.api.Test

class KTNameRegistrationSpec {
    @Test
    fun `It fulfils ComponentRegistrar`() {
        val registration: Any = KTNameRegistration()
        assertTrue { registration is ComponentRegistrar }
    }

    @Test
    fun `Given registerProjectComponents is called, it does nothing`() {
        // Given
        val project: MockProject = mockk()
        val config: CompilerConfiguration = mockk()

        // When
        KTNameRegistration().registerProjectComponents(project, config)

        // Then
    }
}
