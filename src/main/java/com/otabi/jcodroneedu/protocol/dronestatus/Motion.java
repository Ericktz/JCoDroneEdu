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

public class Motion implements Serializable
{
    public static final byte MOTION_SIZE = 18;

    private short accelX;
    private short accelY;
    private short accelZ;
    private short gyroRoll;
    private short gyroPitch;
    private short gyroYaw;
    private short angleRoll;
    private short anglePitch;
    private short angleYaw;

    public Motion(short accelX, short accelY, short accelZ, short gyroRoll, short gyroPitch, short gyroYaw, short angleRoll, short anglePitch, short angleYaw)
    {
        this.accelX = accelX;
        this.accelY = accelY;
        this.accelZ = accelZ;
        this.gyroRoll = gyroRoll;
        this.gyroPitch = gyroPitch;
        this.gyroYaw = gyroYaw;
        this.angleRoll = angleRoll;
        this.anglePitch = anglePitch;
        this.angleYaw = angleYaw;
    }

    public Motion()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return MOTION_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.accelX = buffer.getShort();
        this.accelY = buffer.getShort();
        this.accelZ = buffer.getShort();
        this.gyroRoll = buffer.getShort();
        this.gyroPitch = buffer.getShort();
        this.gyroYaw = buffer.getShort();
        this.angleRoll = buffer.getShort();
        this.anglePitch = buffer.getShort();
        this.angleYaw = buffer.getShort();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putShort(this.accelX);
        buffer.putShort(this.accelY);
        buffer.putShort(this.accelZ);
        buffer.putShort(this.gyroRoll);
        buffer.putShort(this.gyroPitch);
        buffer.putShort(this.gyroYaw);
        buffer.putShort(this.angleRoll);
        buffer.putShort(this.anglePitch);
        buffer.putShort(this.angleYaw);
    }

    // Acceleration getters (in G-force units)
    /**
     * Gets the accel x.
     * @since 1.0.0
     */
    public short getAccelX() { return accelX; }
    /** @since 1.0.0 */
    public short getAccelY() { return accelY; }
    /** @since 1.0.0 */
    public short getAccelZ() { return accelZ; }

    // Gyroscope getters (angular velocity in deg/s)
    /**
     * Gets the gyro roll.
     * @since 1.0.0
     */
    public short getGyroRoll() { return gyroRoll; }
    /** @since 1.0.0 */
    public short getGyroPitch() { return gyroPitch; }
    /** @since 1.0.0 */
    public short getGyroYaw() { return gyroYaw; }

    // Angle getters (in degrees)
    /**
     * Gets the angle roll.
     * @since 1.0.0
     */
    public short getAngleRoll() { return angleRoll; }
    /** @since 1.0.0 */
    public short getAnglePitch() { return anglePitch; }
    /** @since 1.0.0 */
    public short getAngleYaw() { return angleYaw; }
}
