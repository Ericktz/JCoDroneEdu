/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.linkmanager;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Error implements Serializable
{
    public static final byte ERROR_SIZE = 16;

    private long systemTime;
    private int errorFlagsForSensor;
    private int errorFlagsForState;

    public Error()
    {
    }

    public Error(long systemTime, int errorFlagsForSensor, int errorFlagsForState)
    {
        this.systemTime = systemTime;
        this.errorFlagsForSensor = errorFlagsForSensor;
        this.errorFlagsForState = errorFlagsForState;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return ERROR_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        systemTime = buffer.getLong();
        errorFlagsForSensor = buffer.getInt();
        errorFlagsForState = buffer.getInt();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putLong(systemTime);
        buffer.putInt(errorFlagsForSensor);
        buffer.putInt(errorFlagsForState);
    }

    // --- Getters ---

    /**
     * Gets the system time.
     * @since 1.0.0
     */
    public long getSystemTime() {
        return systemTime;
    }

    /**
     * Gets the error flags for sensor.
     * @since 1.0.0
     */
    public int getErrorFlagsForSensor() {
        return errorFlagsForSensor;
    }

    /**
     * Gets the error flags for state.
     * @since 1.0.0
     */
    public int getErrorFlagsForState() {
        return errorFlagsForState;
    }
}
