/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import java.util.concurrent.Callable
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption
import org.junit.jupiter.api.Test
import tech.antibytes.ktname.config.MainConfig

class KTNameCompilerPluginSpec {
    @Test
    fun `It fulfils KotlinCompilerPluginSupportPlugin`() {
        val plugin: Any = KTNameCompilerPlugin()

        assertTrue {
            plugin is KotlinCompilerPluginSupportPlugin
        }
    }

    @Test
    fun `Given apply is called it does nothing`() {
        // Given
        val project: Project = mockk()

        every { project.extensions.create(any(), KTNameExtension::class.java) } returns mockk()

        // When
        KTNameCompilerPlugin().apply(project)

        // Then
        verify(exactly = 1) {
            project.extensions.create("ktname", KTNameExtension::class.java)
        }
    }

    @Test
    fun `Given applyToCompilation is called it returns a Provider with the enabled configuration`() {
        // Given
        val project: Project = mockk()
        val extension: KTNameExtension = mockk()
        val sourceSet: KotlinCompilation<*> = mockk()
        val provider: Provider<List<SubpluginOption>> = mockk()

        val providerBuilder = slot<Callable<List<SubpluginOption>>>()

        every { sourceSet.target.project } returns project
        every {
            project.extensions.getByType(KTNameExtension::class.java)
        } returns extension
        every { extension.enableForJsTests } returns false
        every { extension.enableForInstrumentedAndroidTests } returns true
        every { project.provider(capture(providerBuilder)) } returns provider

        // When
        val provided = KTNameCompilerPlugin().applyToCompilation(sourceSet)
        val options = providerBuilder.captured.call()

        // Then
        assertSame(
            actual = provided,
            expected = provider,
        )
        assertEquals(
            actual = options[0].key,
            expected = "js",
        )
        assertEquals(
            actual = options[0].value,
            expected = "false",
        )
        assertEquals(
            actual = options[1].key,
            expected = "android",
        )
        assertEquals(
            actual = options[1].value,
            expected = "true",
        )
    }

    @Test
    fun `Given getCompilerPluginId it returns the PluginId`() {
        // When
        val actualId = KTNameCompilerPlugin().getCompilerPluginId()

        // Then
        assertEquals(
            expected = "tech.antibytes.ktname.compiler-plugin",
            actual = actualId,
        )
    }

    @Test
    fun `Given getPluginArtifact it returns its coordinates`() {
        // When
        val actual = KTNameCompilerPlugin().getPluginArtifact()

        // Then
        assertEquals(
            expected = "tech.antibytes.ktname",
            actual = actual.groupId,
        )
        assertEquals(
            expected = "compiler-plugin",
            actual = actual.artifactId,
        )
        assertEquals(
            expected = MainConfig.version,
            actual = actual.version,
        )
    }

    @Test
    fun `Given isApplicable is called it return false by default`() {
        // Given
        val sourceSet: KotlinCompilation<*> = mockk()

        // When
        val isApplicable = KTNameCompilerPlugin().isApplicable(sourceSet)

        // Then
        assertFalse(isApplicable)
    }

    @Test
    fun `Given isApplicable is called it return false if the CompilationTarget is JS and the the Extension JS field is set to false`() {
        // Given
        val sourceSet: KotlinCompilation<*> = mockk()

        // When
        val isApplicable = KTNameCompilerPlugin().isApplicable(sourceSet)

        // Then
        assertFalse(isApplicable)
    }

    @Test
    fun `Given isApplicable is called it return true if the CompilationTarget is JS and the the Extension JS field is set to true`() {
        // Given
        val sourceSet: KotlinCompilation<*> = mockk()

        // When
        val isApplicable = KTNameCompilerPlugin().isApplicable(sourceSet)

        // Then
        assertTrue(isApplicable)
    }
}
