/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.settings;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Trim implements Serializable
{
    public static final byte TRIM_SIZE = 8;

    private short roll;
    private short pitch;
    private short yaw;
    private short throttle;

    public Trim(short roll, short pitch, short yaw, short throttle)
    {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        this.throttle = throttle;
    }

    public Trim() {
        this((short) 0, (short) 0, (short) 0, (short) 0);
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return TRIM_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.roll = buffer.getShort();
        this.pitch = buffer.getShort();
        this.yaw = buffer.getShort();
        this.throttle = buffer.getShort();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putShort(this.roll);
        buffer.putShort(this.pitch);
        buffer.putShort(this.yaw);
        buffer.putShort(this.throttle);
    }

    // Getter methods
    /**
     * Gets the roll.
     * @since 1.0.0
     */
    public short getRoll() {
        return roll;
    }

    /**
     * Gets the pitch.
     * @since 1.0.0
     */
    public short getPitch() {
        return pitch;
    }

    /**
     * Gets the yaw.
     * @since 1.0.0
     */
    public short getYaw() {
        return yaw;
    }

    /**
     * Gets the throttle.
     * @since 1.0.0
     */
    public short getThrottle() {
        return throttle;
    }

    // Setter methods
    /**
     * Sets the roll.
     * @since 1.0.0
     */
    public void setRoll(short roll) {
        this.roll = roll;
    }

    /**
     * Sets the pitch.
     * @since 1.0.0
     */
    public void setPitch(short pitch) {
        this.pitch = pitch;
    }

    /**
     * Sets the yaw.
     * @since 1.0.0
     */
    public void setYaw(short yaw) {
        this.yaw = yaw;
    }

    /**
     * Sets the throttle.
     * @since 1.0.0
     */
    public void setThrottle(short throttle) {
        this.throttle = throttle;
    }
}
