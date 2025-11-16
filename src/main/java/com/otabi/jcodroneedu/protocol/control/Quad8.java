/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.control;


import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Quad8 implements Serializable
{

    public static final int SIZE = 4;

    private byte roll = 0;
    private byte pitch = 0;
    private byte yaw = 0;
    private byte throttle = 0;

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(this.roll);
        buffer.put(this.pitch);
        buffer.put(this.yaw);
        buffer.put(this.throttle);
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return (byte) SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.roll = buffer.get();
        this.pitch = buffer.get();
        this.yaw = buffer.get();
        this.throttle = buffer.get();
    }

    /**
     * Sets the roll.
     * @since 1.0.0
     */
    public void setRoll(byte roll)
    {
        this.roll = roll;
    }

    /**
     * Sets the pitch.
     * @since 1.0.0
     */
    public void setPitch(byte pitch)
    {
        this.pitch = pitch;
    }

    /**
     * Sets the yaw.
     * @since 1.0.0
     */
    public void setYaw(byte yaw)
    {
        this.yaw = yaw;
    }

    /**
     * Sets the throttle.
     * @since 1.0.0
     */
    public void setThrottle(byte throttle)
    {
        this.throttle = throttle;
    }

    /**
     * Gets the roll.
     * @since 1.0.0
     */
    public byte getRoll()
    {
        return roll;
    }

    /**
     * Gets the pitch.
     * @since 1.0.0
     */
    public byte getPitch()
    {
        return pitch;
    }

    /**
     * Gets the yaw.
     * @since 1.0.0
     */
    public byte getYaw()
    {
        return yaw;
    }

    /**
     * Gets the throttle.
     * @since 1.0.0
     */
    public byte getThrottle()
    {
        return throttle;
    }
}