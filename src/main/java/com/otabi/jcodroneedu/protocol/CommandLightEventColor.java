/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

import com.otabi.jcodroneedu.protocol.lightcontroller.Color;
import com.otabi.jcodroneedu.protocol.lightcontroller.LightEvent;

import java.nio.ByteBuffer;

public class CommandLightEventColor implements Serializable
{
    private Command command;
    private LightEvent lightEvent;
    private Color color;

    public CommandLightEventColor(Command command, LightEvent lightEvent, Color color)
    {
        this.command = command;
        this.lightEvent = lightEvent;
        this.color = color;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return (byte) (command.getSize() + lightEvent.getSize() + color.getSize());
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.command.unpack(buffer);
        this.lightEvent.unpack(buffer);
        this.color.unpack(buffer);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        command.pack(buffer);
        lightEvent.pack(buffer);
        color.pack(buffer);
    }
}
