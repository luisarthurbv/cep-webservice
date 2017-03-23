package com.luisarthurbv.utils;

import java.util.regex.Pattern;

public class CepUtils {

    private static final Pattern CEP_PATTERN = Pattern.compile("[0-9]{8}");

    public static boolean isValid(String cep) {
        return CEP_PATTERN.matcher(cep).matches();
    }

}
