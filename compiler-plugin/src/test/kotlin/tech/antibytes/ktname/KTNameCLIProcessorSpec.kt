/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

import io.mockk.mockk
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class KTNameCLIProcessorSpec {
    @Test
    fun `It fulfils CommandLineProcessor`() {
        val processor: Any = KTNameCLIProcessor()

        assertTrue { processor is CommandLineProcessor }
    }

    @Test
    fun `It has a id`() {
        assertEquals(
            actual = KTNameCLIProcessor().pluginId,
            expected = "tech.antibytes.ktname.compiler-plugin"
        )
    }

    @Test
    fun `It has no cli options`() {
        assertEquals(
            actual = KTNameCLIProcessor().pluginOptions,
            expected = emptyList()
        )
    }

    @Test
    fun `Given processOption is called it fails due to a unknown cli option`() {
        // Given
        val option: AbstractCliOption = CliOption(
            optionName = "Something",
            description = "Enables/Disables classes to opened for testing",
            valueDescription = "boolean",
            required = false,
            allowMultipleOccurrences = false
        )
        val config: CompilerConfiguration = mockk()

        // Then
        val error = assertFailsWith<IllegalArgumentException> {
            // When
            KTNameCLIProcessor().processOption(
                option,
                "something",
                config
            )
        }

        assertEquals(
            actual = error.message,
            expected = "Unknown config option ${option.optionName}"
        )
    }
}
