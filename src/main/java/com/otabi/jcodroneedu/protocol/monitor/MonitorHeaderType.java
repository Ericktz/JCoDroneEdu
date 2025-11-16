/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.monitor;

public enum MonitorHeaderType {
    Monitor0((byte) 0x00),
    Monitor4((byte) 0x01),
    Monitor8((byte) 0x02),
    EndOfTypeOf((byte) 0x03);

    private final byte value;

    MonitorHeaderType(byte value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * @since 1.0.0
     */
    public byte getValue() {
        return value;
    }

    /**
     * fromByte method.
     * @since 1.0.0
     */
    public static MonitorHeaderType fromByte(byte value) {
        for (MonitorHeaderType type : MonitorHeaderType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MonitorHeaderType value: " + value);
    }
}
