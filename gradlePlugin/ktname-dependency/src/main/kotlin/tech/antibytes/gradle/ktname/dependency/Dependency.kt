/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.gradle.ktname.dependency

object Dependency {
    val gradle = GradlePlugin

    val kotlin = Kotlin

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val compiler = "org.jetbrains.kotlin:kotlin-compiler-embeddable"
    }
}
