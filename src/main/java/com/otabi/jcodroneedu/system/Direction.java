/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.system;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NONE((byte) 0x00),
    LEFT((byte) 0x01),
    FRONT((byte) 0x02),
    RIGHT((byte) 0x03),
    REAR((byte) 0x04),
    TOP((byte) 0x05),
    BOTTOM((byte) 0x06),
    END_OF_TYPE((byte) 0x07);

    private static final Map<Byte, Direction> BYTE_DIRECTION_MAP =
            new HashMap<>();

    static {
        for (Direction direction : Direction.values()) {
            BYTE_DIRECTION_MAP.put(direction.value(), direction);
        }
    }

    private final byte direction;

    Direction(byte direction) {
        this.direction = direction;
    }

    /**
     * value method.
     * @since 1.0.0
     */
    public byte value() {
        return direction;
    }

    /**
     * fromByte method.
     * @since 1.0.0
     */
    public static Direction fromByte(byte b) {
        return BYTE_DIRECTION_MAP.get(b);
    }
}
