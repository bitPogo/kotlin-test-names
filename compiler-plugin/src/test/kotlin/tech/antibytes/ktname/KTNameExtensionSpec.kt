/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import tech.antibytes.gradle.test.createExtension

class KTNameExtensionSpec {
    @Test
    fun `It fulfils Extension`() {
        val extension: Any = createExtension<KTNameExtension>()

        assertTrue {
            extension is KTNameContract.Extension
        }
    }

    @Test
    fun `Js Tests are enabled by default`() {
        // Given
        val extension: KTNameExtension = createExtension()

        // When
        val enabled = extension.enableForJsTests

        // Then
        assertTrue(enabled)
    }

    @Test
    fun `Instrumented Android Tests are disabled by default`() {
        // Given
        val extension: KTNameExtension = createExtension()

        // When
        val enabled = extension.enableForInstrumentedAndroidTests

        // Then
        assertFalse(enabled)
    }
}
