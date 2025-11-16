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

class Monitor0 implements Serializable
{
    public static final byte MONITOR_0_SIZE = 2;

    private MonitorDataType monitorDataType = MonitorDataType.F32;
    private byte index;

    public Monitor0()
    {
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return MONITOR_0_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        buffer.put(this.monitorDataType.getValue());
        buffer.put(this.index);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        monitorDataType = MonitorDataType.fromByte(buffer.get());
        index = buffer.get();
    }
}
