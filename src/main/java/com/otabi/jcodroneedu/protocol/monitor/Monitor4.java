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

class Monitor4 implements Serializable
{
    public static final int MONITOR_4_SIZE = 6;

    private int systemTime;
    private MonitorDataType monitorDataType = MonitorDataType.F32;
    private byte index;

    public Monitor4()
    {
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return MONITOR_4_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        systemTime = buffer.getInt();
        monitorDataType = MonitorDataType.fromByte(buffer.get());
        index = buffer.get();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putInt(this.systemTime);
        buffer.put(this.monitorDataType.getValue());
        buffer.put(this.index);
    }
}
