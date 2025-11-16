/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol._unknown;

import com.otabi.jcodroneedu.protocol.DataType;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Request implements Serializable
{
    public static final byte REQUEST_SIZE = 1;
    private DataType dataType;

    public Request(DataType dataType)
    {
        this.dataType = dataType;
    }

    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return REQUEST_SIZE;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(dataType.value());
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.dataType = DataType.fromByte(buffer.get());
    }
}
