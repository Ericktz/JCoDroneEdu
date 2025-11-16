/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.dronestatus;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Position implements Serializable
{
    public static final byte POSITION_SIZE = 12;

    private float x;
    private float y;
    private float z;

    public Position(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return POSITION_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.x = buffer.getFloat();
        this.y = buffer.getFloat();
        this.z = buffer.getFloat();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putFloat(x);
        buffer.putFloat(y);
        buffer.putFloat(z);
    }
    
    // Position getters (in meters from takeoff point)
    /**
     * Gets the x.
     * @since 1.0.0
     */
    public float getX() { return x; }
    /** @since 1.0.0 */
    public float getY() { return y; }
    /** @since 1.0.0 */
    public float getZ() { return z; }
}
