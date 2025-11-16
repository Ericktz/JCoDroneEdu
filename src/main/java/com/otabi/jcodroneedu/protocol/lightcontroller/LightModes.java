/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import java.nio.ByteBuffer;

public interface LightModes
{
    /**
     * Gets the value.
     * @since 1.0.0
     */
    public byte getValue();

    /**
     * Gets the mode from buffer.
     * @since 1.0.0
     */
    public LightModes getModeFromBuffer(ByteBuffer buffer);
}
