/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.controllerinput;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Joystick implements Serializable
{
    private JoystickBlock left;
    private JoystickBlock right;

    public Joystick(JoystickBlock left, JoystickBlock right)
    {
        this.left = left;
        this.right = right;
    }

    public Joystick()
    {
        this.left = new JoystickBlock();
        this.right = new JoystickBlock();
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return (byte) (left.getSize() + right.getSize());
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        left.unpack(buffer);
        right.unpack(buffer);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        left.pack(buffer);
        right.pack(buffer);
    }

    // Getter methods
    /**
     * Gets the left.
     * @since 1.0.0
     */
    public JoystickBlock getLeft() {
        return left;
    }

    /**
     * Gets the right.
     * @since 1.0.0
     */
    public JoystickBlock getRight() {
        return right;
    }
}
