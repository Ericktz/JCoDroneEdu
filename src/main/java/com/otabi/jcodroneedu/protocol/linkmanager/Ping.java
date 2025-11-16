/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.linkmanager;

import com.otabi.jcodroneedu.protocol.Serializable;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;

import java.nio.ByteBuffer;

public class Ping implements Serializable
{
    public static final int PING_SIZE = 4;
    private int systemTime;

    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return PING_SIZE;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putInt(systemTime);
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.systemTime = buffer.getInt();
    }
}
