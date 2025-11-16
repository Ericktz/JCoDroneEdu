/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public enum LightModesController implements LightModes {

    None_((byte) 0x00),

    // Body
    BodyNone((byte) 0x20),
    BodyManual((byte) 0x21),      // Manual control
    BodyHold((byte) 0x22),
    BodyFlicker((byte) 0x23),
    BodyFlickerDouble((byte) 0x24),
    BodyDimming((byte) 0x25),
    BodySunrise((byte) 0x26),
    BodySunset((byte) 0x27),
    BodyRainbow((byte) 0x28),
    BodyRainbow2((byte) 0x29),

    EndOfType((byte) 0x30);

    private final byte value;

    LightModesController(byte value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * @since 1.0.0
     */
    public byte getValue() {
        return value;
    }

    private static final Map<Byte, LightModes> valueMap = new HashMap<>();

    static {
        for (LightModesController mode : LightModesController.values()) {
            valueMap.put(mode.getValue(), mode);
        }
    }

    /**
     * fromValue method.
     * @since 1.0.0
     */
    public static LightModes fromValue(byte value) {
        return valueMap.get(value);
    }

    /**
     * Gets the mode from buffer.
     * @since 1.0.0
     */
    public LightModes getModeFromBuffer(ByteBuffer buffer) {
        return fromValue(buffer.get());
    }

}
