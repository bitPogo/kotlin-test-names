/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

object KTNameContract {
    interface Extension {
        /**
         * Enables the compiler plugin for JavaScript tests
         */
        var enableForJsTests: Boolean

        /**
         * Enables the compiler plugin for instrumented Android tests
         */
        var enableForInstrumentedAndroidTests: Boolean
    }

    internal const val USE_ANDROID = "android"
    internal const val USE_JS = "js"
}
