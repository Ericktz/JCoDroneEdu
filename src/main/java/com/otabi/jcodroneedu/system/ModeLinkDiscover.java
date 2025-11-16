/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.system;

public enum ModeLinkDiscover {
    NONE(0x00),

    NAME(0x01),
    SERVICE(0x02),
    ALL(0x03),

    END_OF_TYPE(0x04);

    private final int mode;

    ModeLinkDiscover(int mode) {
        this.mode = mode;
    }

    /**
     * Gets the mode.
     * @since 1.0.0
     */
    public int getMode() {
        return mode;
    }
}
