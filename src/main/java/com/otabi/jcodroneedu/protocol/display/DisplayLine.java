/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.display;

/**
 * Enum for display line types used in CoDrone EDU controller display drawing operations.
 */
public enum DisplayLine {
    SOLID((byte) 0x00),
    DOTTED((byte) 0x01),
    DASHED((byte) 0x02);

    private final byte value;

    DisplayLine(byte value) {
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
    public static DisplayLine fromByte(byte b) {
        for (DisplayLine line : values()) {
            if (line.value == b) {
                return line;
            }
        }
        throw new IllegalArgumentException("Unknown DisplayLine value: " + b);
    }
}
