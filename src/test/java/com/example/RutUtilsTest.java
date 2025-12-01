package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RutUtilsTest {

    @Test
    public void testValidRutFormatted() {
        assertTrue(RutUtils.validateRut("12.345.678-5"), "12.345.678-5 should be valid");
    }

    @Test
    public void testValidRutUnformatted() {
        assertTrue(RutUtils.validateRut("123456785"), "123456785 should be valid (same as 12.345.678-5)");
    }

    @Test
    public void testInvalidRutWrongDv() {
        assertFalse(RutUtils.validateRut("12.345.678-9"), "Wrong check digit should be invalid");
    }

    @Test
    public void testInvalidRutNonNumeric() {
        assertFalse(RutUtils.validateRut("ABC.DEF.GHI-J"), "Non-numeric number part should be invalid");
    }
}