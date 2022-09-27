/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.ktname

abstract class KTNameExtension : KTNameContract.Extension {
    override var enableForJsTests: Boolean = true
    override var enableForInstrumentedAndroidTests: Boolean = false
}
