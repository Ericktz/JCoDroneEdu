/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.dronestatus;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Range implements Serializable
{
    public static final byte RANGE_SIZE = 12;

    private short left;
    private short front;
    private short right;
    private short rear;
    private short top;
    private short bottom;

    public Range(short left, short front, short right, short rear, short top, short bottom)
    {
        this.left = left;
        this.front = front;
        this.right = right;
        this.rear = rear;
        this.top = top;
        this.bottom = bottom;
    }

    public Range()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return RANGE_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.left = buffer.getShort();
        this.front = buffer.getShort();
        this.right = buffer.getShort();
        this.rear = buffer.getShort();
        this.top = buffer.getShort();
        this.bottom = buffer.getShort();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putShort(left);
        buffer.putShort(front);
        buffer.putShort(right);
        buffer.putShort(rear);
        buffer.putShort(top);
        buffer.putShort(bottom);
    }

    // Range sensor getters (in millimeters)
    /**
     * Gets the left.
     * @since 1.0.0
     */
    public short getLeft() { return left; }
    /** @since 1.0.0 */
    public short getFront() { return front; }
    /** @since 1.0.0 */
    public short getRight() { return right; }
    /** @since 1.0.0 */
    public short getRear() { return rear; }
    /**
     * Gets the top.
     * @since 1.0.0
     */
    public short getTop() { return top; }
    /** @since 1.0.0 */
    public short getBottom() { return bottom; }
}
