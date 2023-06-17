package com.sevilla.usermanager.util;

import java.util.regex.Pattern;

public final class CommonValidate {

    public static boolean patternMatches(String email, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
}
