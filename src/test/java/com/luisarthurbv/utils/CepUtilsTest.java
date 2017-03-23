package com.luisarthurbv.utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class CepUtilsTest {

    @Test
    public void validCepTest() {
        String cep = "12345678";
        assertEquals(String.format("Assert cep: %s is valid", cep),
                false, !CepUtils.isValid(cep));
    }

    @Test
    public void invapLessDigitsCepTest() {
        String cep = "1234567";
        assertEquals(String.format("Assert cep: %s is invalid", cep),
                false, CepUtils.isValid(cep));
    }

    @Test
    public void invapMoreDigitsCepTest() {
        String cep = "1234567890";
        assertEquals(String.format("Assert cep: %s is invalid", cep),
                false, CepUtils.isValid(cep));
    }

}
