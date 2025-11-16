/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.display;

/**
 * Enum for display text alignment used in CoDrone EDU controller display string operations.
 */
public enum DisplayAlign {
    LEFT((byte) 0x00),
    CENTER((byte) 0x01),
    RIGHT((byte) 0x02);

    private final byte value;

    DisplayAlign(byte value) {
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
    public static DisplayAlign fromByte(byte b) {
        for (DisplayAlign align : values()) {
            if (align.value == b) {
                return align;
            }
        }
        throw new IllegalArgumentException("Unknown DisplayAlign value: " + b);
    }
}
