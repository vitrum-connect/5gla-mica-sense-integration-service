package de.app.fivegla.api;

import lombok.Getter;

/**
 * Error codes.
 */
@Getter
public enum Error {

    INVALID_REQUEST(errorOf(1));
    private static String errorOf(int i) {
        return ERR_ + String.format("%05d", i);
    }

    private static final String ERR_ = "ERR_";

    private final String code;

    Error(String code) {
        this.code = code;
    }

    public String asTitle() {
        return String.format("%s", code);
    }
}
