/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;

import java.nio.ByteBuffer;

public class LightModeColor extends LightMode {

    private Color color;

    public LightModeColor(LightModes mode, Color color, short interval) {
        super(mode, interval);
        this.color = color;
    }

    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return (byte) (super.getSize() + color.getSize());
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        super.pack(buffer);
        color.pack(buffer);
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        super.unpack(buffer);
        color.unpack(buffer);
    }
}
