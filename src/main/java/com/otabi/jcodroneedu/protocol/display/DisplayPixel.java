/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.display;

/**
 * Enum for display pixel types used in CoDrone EDU controller display operations.
 */
public enum DisplayPixel {
    BLACK((byte) 0x00),
    WHITE((byte) 0x01),
    INVERSE((byte) 0x02),
    OUTLINE((byte) 0x03);

    private final byte value;

    DisplayPixel(byte value) {
        this.value = value;
    }

    /**
     * value method.
     * @since 1.0.0
     */
    public byte value() {
        return value;
    }

    /**
     * fromByte method.
     * @since 1.0.0
     */
    public static DisplayPixel fromByte(byte b) {
        for (DisplayPixel pixel : values()) {
            if (pixel.value == b) {
                return pixel;
            }
        }
        throw new IllegalArgumentException("Unknown DisplayPixel value: " + b);
    }
}
