/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.linkmanager;

import java.nio.ByteBuffer;
// Collectors not used; removed
import com.otabi.jcodroneedu.protocol.Serializable;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;

public class Address implements Serializable {
    public static final int ADDRESS_SIZE = 16;

    private byte[] address;

    public Address()
    {
    }

    public Address(byte[] address) {
        this.address = address;
    }

    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return ADDRESS_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        address = new byte[getSize()];
        buffer.get(address, 0, getSize());
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(this.address);
    }

    /**
     * Gets the address.
     * @since 1.0.0
     */
    public byte[] getAddress() {
        return address;
    }

}
