package com.hendraanggrian.circularreveallayout;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public enum RevealLocation {

    TOP_LEFT(1),
    TOP(2),
    TOP_RIGHT(3),
    LEFT(4),
    CENTER(5),
    RIGHT(6),
    BOTTOM_LEFT(7),
    BOTTOM(8),
    BOTTOM_RIGHT(9);

    private int value;

    RevealLocation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RevealLocation get(int value) {
        for (RevealLocation revealLocation : RevealLocation.values())
            if (revealLocation.getValue() == value)
                return revealLocation;
        return null;
    }

}