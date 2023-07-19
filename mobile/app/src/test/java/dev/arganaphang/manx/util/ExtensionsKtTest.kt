package dev.arganaphang.manx.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Calendar

class ExtensionsKtTest {    @Test
    fun `Long to Money`() {
        assertEquals("Rp120.000", 120000L.toMoney())
    }
    @Test
    fun `Date to Human`() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1);
        assertEquals("Yesterday", cal.time.toHuman())
    }
}