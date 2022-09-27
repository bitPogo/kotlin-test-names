package tech.antibytes.ktname

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertTrue

class ThingSpec {
    @Test
    @JsName("fn0")
    fun `A Thing exists`() {
        val thing: Any = Thing()
        assertTrue(thing is Thing)
    }
}
