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

public class CardClassify implements Serializable
{
    public static final byte CARD_CLASSIFY_SIZE = 39;

    private byte index;
    private byte[][][] cc;
    private byte[] l;

    public CardClassify(byte index, byte[][][] cc, byte[] l)
    {
        this.index = index;
        this.cc = cc;
        this.l = l;
    }

    public CardClassify()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return CARD_CLASSIFY_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.index = buffer.get();
        this.cc = new byte[6][3][2];
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 3; j++)
                buffer.get(cc[i][j]);
        this.l = new byte[2];
        buffer.get(this.l);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(this.index);
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 3; j++)
                buffer.put(cc[i][j]);
        buffer.put(this.l);
    }
}
