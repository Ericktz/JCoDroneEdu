/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

import java.nio.ByteBuffer;

public class Command implements Serializable
{
    public static final byte COMMAND_SIZE = 2;

    private CommandType commandType;
    private byte option;

    public Command(CommandType commandType, byte option)
    {
        this.commandType = commandType;
        this.option = option;
    }

    public Command()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return COMMAND_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.commandType = CommandType.fromValue(buffer.get());
        this.option = buffer.get();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(commandType.getValue());
        buffer.put(option);
    }
}
