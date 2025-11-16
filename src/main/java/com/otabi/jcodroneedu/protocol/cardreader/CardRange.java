/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.cardreader;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class CardRange implements Serializable
{
    public static final byte CARD_RANGE_SIZE = 24;

    private byte[][][] range;

    public CardRange(byte[][][] range)
    {
        this.range = range;
    }

    public CardRange()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return CARD_RANGE_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.range = new byte[2][3][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                buffer.get(this.range[i][j]);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                buffer.put(this.range[i][j]);

    }
}
