/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import java.util.HashMap;
import java.util.Map;

public enum LightFlagsController {

    None_((byte) 0x00),

    BodyRed((byte) 0x01),
    BodyGreen((byte) 0x02),
    BodyBlue((byte) 0x04);

    private final byte value;

    LightFlagsController(byte value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * @since 1.0.0
     */
    public byte getValue() {
        return value;
    }

    private static final Map<Byte, LightFlagsController> valueMap = new HashMap<>();

    static {
        for (LightFlagsController mode : LightFlagsController.values()) {
            valueMap.put(mode.getValue(), mode);
        }
    }

    /**
     * fromValue method.
     * @since 1.0.0
     */
    public static LightFlagsController fromValue(byte value) {
        return valueMap.get(value);
    }
}
