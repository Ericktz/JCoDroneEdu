/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

import java.nio.ByteBuffer;

public class Header implements Serializable
{
    private static final byte HEADER_SIZE = 4; // A clear constant name

    private DataType dataType;
    private byte length;
    private DeviceType from;
    private DeviceType to;

    public Header() {
    }

    public Header(DataType dataType, byte length, DeviceType from, DeviceType to) {
        this.dataType = dataType;
        this.length = length;
        this.from = from;
        this.to = to;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return HEADER_SIZE;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(dataType.value())
            .put(length)
            .put(from.getValue())
            .put(to.getValue());
    }



    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.dataType = DataType.fromByte(buffer.get());
        this.length = buffer.get();
        this.from = DeviceType.fromByte(buffer.get());
        this.to = DeviceType.fromByte(buffer.get());
    }

    /**
     * Gets the data type.
     * @since 1.0.0
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Gets the length.
     * @since 1.0.0
     */
    public byte getLength() {
        return length;
    }

    /**
     * Gets the from.
     * @since 1.0.0
     */
    public DeviceType getFrom() {
        return from;
    }

    /**
     * Gets the to.
     * @since 1.0.0
     */
    public DeviceType getTo() {
        return to;
    }

    /**
     * Sets the data type.
     * @since 1.0.0
     */
    public void setDataType(DataType dataType)
    {
        this.dataType = dataType;
    }

    /**
     * Sets the length.
     * @since 1.0.0
     */
    public void setLength(byte length)
    {
        this.length = length;
    }

    /**
     * Sets the from.
     * @since 1.0.0
     */
    public void setFrom(DeviceType from)
    {
        this.from = from;
    }

    /**
     * Sets the to.
     * @since 1.0.0
     */
    public void setTo(DeviceType to)
    {
        this.to = to;
    }

}
