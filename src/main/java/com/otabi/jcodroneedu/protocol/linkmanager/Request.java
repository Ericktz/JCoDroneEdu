/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.linkmanager;

import com.otabi.jcodroneedu.protocol.DataType;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

/**
 * Represents a request message sent to the drone to ask for a specific
 * type of data. The payload is a single byte representing the DataType
 * being requested.
 */
public class Request implements Serializable {
    public static final byte REQUEST_SIZE = 1;

    private DataType requestedDataType;

    /**
     * Default constructor for the factory pattern.
     */
    public Request() {
    }

    /**
     * Creates a new Request for a specific DataType.
     * @param requestedDataType The type of data being requested.
     */
    public Request(DataType requestedDataType) {
        this.requestedDataType = requestedDataType;
    }

    @Override
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
    public void pack(ByteBuffer buffer) {
        buffer.put(requestedDataType.value());
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException {
        if (buffer.remaining() < REQUEST_SIZE) {
            // Corrected to pass both the expected size and the actual size.
            throw new InvalidDataSizeException(REQUEST_SIZE, buffer.remaining());
        }
        this.requestedDataType = DataType.fromByte(buffer.get());
    }

    /**
     * Gets the requested data type.
     * @since 1.0.0
     */
    public DataType getRequestedDataType() {
        return requestedDataType;
    }
}
