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

public class Rssi implements Serializable
{
    public static final byte RSSI_SIZE = 1;

    private byte rssi;

    public Rssi(byte rssi)
    {
        this.rssi = rssi;
    }

    public Rssi()
    {

    }

    /**
     * Returns the RSSI value in dBm as a signed byte mapped to int.
     * Typical BLE values are negative (e.g., -40 strong, -90 weak).
      * @since 1.0.0
     */
    public int getRssi() {
        return (int) rssi;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return RSSI_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.rssi = buffer.get();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(rssi);
    }
}
