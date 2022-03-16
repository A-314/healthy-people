package com.emelyan.models;

public enum Gender {
    MALE('M'),
    FEMALE('F');

    private final char code;

    Gender(char code) {
        this.code = code;
    }

    public static Gender fromCode(char code) {
        if (code == 'F' || code == 'f') {
            return FEMALE;
        }
        if (code == 'M' || code == 'm') {
            return MALE;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

    public char getCode() {
        return code;
    }
}
