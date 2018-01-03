package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberSystemServiceTest {
    @Test
    public void testIsValid() {
        for(int base = 2; base <= 10; base++)
            assertTrue(NumberSystemService.isValid("0", base));
        assertFalse(NumberSystemService.isValid("120", 2));
        assertTrue(NumberSystemService.isValid("120", 3));
        assertFalse(NumberSystemService.isValid("210", 2));
        assertTrue(NumberSystemService.isValid("210", 3));
        assertFalse(NumberSystemService.isValid("012", 2));
        assertTrue(NumberSystemService.isValid("012", 3));
        assertFalse(NumberSystemService.isValid("666", 6));
        assertTrue(NumberSystemService.isValid("666", 7));

    }

    @Test
    public void testRemoveLeadingZeroes() {
        assertEquals("0", NumberSystemService.removeLeadingZeroes("000000"));
        assertEquals("101", NumberSystemService.removeLeadingZeroes("00101"));
        assertEquals("101", NumberSystemService.removeLeadingZeroes("101"));
        assertEquals("1010", NumberSystemService.removeLeadingZeroes("001010"));
    }

    @Test
    public void testGetSmallestPossibleBase() {
        assertEquals(2, NumberSystemService.getSmallestPossibleBase("0"));
        assertEquals(2, NumberSystemService.getSmallestPossibleBase("101011"));
        assertEquals(3, NumberSystemService.getSmallestPossibleBase("101211"));
        assertEquals(3, NumberSystemService.getSmallestPossibleBase("201011"));
        assertEquals(3, NumberSystemService.getSmallestPossibleBase("101012"));
        assertEquals(10, NumberSystemService.getSmallestPossibleBase("101911"));
        assertEquals(10, NumberSystemService.getSmallestPossibleBase("901011"));
        assertEquals(10, NumberSystemService.getSmallestPossibleBase("101019"));
    }

    @Test
    public void testGetDecimalValue() {
        assertEquals(0, NumberSystemService.getDecimalValue("0",2));
        assertEquals(0, NumberSystemService.getDecimalValue("0",8));
        assertEquals(13, NumberSystemService.getDecimalValue("1101",2));
        assertEquals(124, NumberSystemService.getDecimalValue("444",5));
        assertEquals(6053444, NumberSystemService.getDecimalValue("12345678",9));
    }

    @Test
    public void testConvertFromDecimal() {
        assertEquals("0", NumberSystemService.convertFromDecimal(0,2));
        assertEquals("1101", NumberSystemService.convertFromDecimal(13,2));
        assertEquals("444", NumberSystemService.convertFromDecimal(124,5));
        assertEquals("12345678", NumberSystemService.convertFromDecimal(6053444,9));
    }

    @Test
    public void testParity() {
        assertEquals(0, NumberSystemService.parity("31473"));
        assertEquals(0, NumberSystemService.parity("0"));
        assertEquals(0, NumberSystemService.parity("00031473"));
        assertEquals(1, NumberSystemService.parity("31373"));
        assertEquals(1, NumberSystemService.parity("00031373000"));
    }

    @Test
    public void testCompare() {
        assertEquals(0, NumberSystemService.compare("31473", "31473"));
        assertEquals(0, NumberSystemService.compare("0", "0"));
        assertEquals(-1, NumberSystemService.compare("34", "32000"));
        assertEquals(1, NumberSystemService.compare("32000", "322"));
        assertEquals(0, NumberSystemService.compare("784684756943769346749", "784684756943769346749"));
    }

//	@Test
//	public void testGetSmallestPossibleBase() {
//		assertEquals(2, NumberSystemService.getSmallestPossibleBase("0"));
//		assertEquals(2, NumberSystemService.getSmallestPossibleBase("101011"));
//		assertEquals(3, NumberSystemService.getSmallestPossibleBase("101211"));
//		assertEquals(3, NumberSystemService.getSmallestPossibleBase("201011"));
//		assertEquals(3, NumberSystemService.getSmallestPossibleBase("101012"));
//		assertEquals(10, NumberSystemService.getSmallestPossibleBase("101911"));
//		assertEquals(10, NumberSystemService.getSmallestPossibleBase("901011"));
//		assertEquals(10, NumberSystemService.getSmallestPossibleBase("101019"));
//	}
//
//	@Test
//	public void testGetDecimalValue() {
//		assertEquals(0, NumberSystemService.getDecimalValue("0",2));
//		assertEquals(0, NumberSystemService.getDecimalValue("0",8));
//		assertEquals(13, NumberSystemService.getDecimalValue("1101",2));
//		assertEquals(124, NumberSystemService.getDecimalValue("444",5));
//		assertEquals(6053444, NumberSystemService.getDecimalValue("12345678",9));
//	}

    @Test
    public void testIncrement() {
        assertEquals("1", NumberSystemService.increment("0", 5));
        assertEquals("104", NumberSystemService.increment("103", 5));
        assertEquals("110", NumberSystemService.increment("104", 5));
        assertEquals("1000", NumberSystemService.increment("444", 5));
        assertEquals("1000", NumberSystemService.increment("888", 9));
        assertEquals("10000000000000000", NumberSystemService.increment("9999999999999999", 10));
    }

    @Test
    public void testDecrement() {
        assertEquals("0", NumberSystemService.decrement("1", 5));
        assertEquals("103", NumberSystemService.decrement("104", 5));
        assertEquals("114", NumberSystemService.decrement("120", 5));
        assertEquals("44", NumberSystemService.decrement("100", 5));
        assertEquals("9999999999999999", NumberSystemService.decrement("10000000000000000", 10));
    }

    @Test
    public void testIncrease() {
        assertEquals("3", NumberSystemService.increase("0", 3, 5));
        assertEquals("10", NumberSystemService.increase("0", 5, 5));
        assertEquals("128", NumberSystemService.increase("108", 20, 10));
        assertEquals("1000", NumberSystemService.increase("108", 892, 10));
        assertEquals("10000000000000010", NumberSystemService.increase("9999999999999999", 11, 10));
    }

    @Test
    public void testDecrease() {
        assertEquals("1", NumberSystemService.decrease("4", 3, 5));
        assertEquals("0", NumberSystemService.decrease("4", 4, 5));
        assertEquals("88", NumberSystemService.decrease("108", 20, 10));
        assertEquals("0", NumberSystemService.decrease("892", 892, 10));
        assertEquals("99", NumberSystemService.decrease("892", 793, 10));
        assertEquals("9999999999999999", NumberSystemService.decrease("10000000000000010", 11, 10));
    }

}
