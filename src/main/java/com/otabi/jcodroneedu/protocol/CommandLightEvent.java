/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

import com.otabi.jcodroneedu.protocol.lightcontroller.LightEvent;

import java.nio.ByteBuffer;

public class CommandLightEvent implements Serializable
{
    private Command command;
    private LightEvent lightEvent;

    public CommandLightEvent(Command command, LightEvent lightEvent)
    {
        this.command = command;
        this.lightEvent = lightEvent;
    }

    @Override
    public byte getSize()
    {
        return (byte) (command.getSize() + lightEvent.getSize());
    }

    @Override
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.command.unpack(buffer);
        this.lightEvent.unpack(buffer);
    }

    @Override
    public void pack(ByteBuffer buffer)
    {
        command.pack(buffer);
        lightEvent.pack(buffer);
    }
}
