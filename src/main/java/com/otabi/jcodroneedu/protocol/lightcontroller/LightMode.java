/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public abstract class LightMode implements Serializable
{
    public static final byte LIGHT_MODE_SIZE = 3;

    private LightModes mode;
    private short interval;

    public LightMode(LightModes mode, short interval)
    {
        this.mode = mode;
        this.interval = interval;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.mode = mode.getModeFromBuffer(buffer);
        this.interval = buffer.getShort();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(mode.getValue());
        buffer.putShort(interval);
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return LIGHT_MODE_SIZE;
    }

    /**
     * Gets the mode.
     * @since 1.0.0
     */
    public LightModes getMode()
    {
        return mode;
    }

    protected void setMode(LightModes mode)
    {
        this.mode = mode;
    }
}
