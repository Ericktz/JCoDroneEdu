/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.monitor;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class MonitorType implements Serializable
{
    public static final byte MONITOR_TYPE_SIZE = 1;

    private MonitorHeaderType monitorHeaderType = MonitorHeaderType.Monitor8;

    public MonitorType() {
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return MONITOR_TYPE_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        monitorHeaderType = MonitorHeaderType.fromByte(buffer.get());
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(this.monitorHeaderType.getValue());
    }
}

