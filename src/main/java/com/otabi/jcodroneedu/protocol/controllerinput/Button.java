/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.controllerinput;

import com.otabi.jcodroneedu.DroneSystem;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class Button implements Serializable
{
    public static final byte BUTTON_SIZE = 3;

    private short button;
    private DroneSystem.ButtonEvent event;

    public Button(short button, DroneSystem.ButtonEvent event)
    {
        this.button = button;
        this.event = event;
    }

    public Button()
    {

    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return BUTTON_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.button = buffer.getShort();
        this.event = DroneSystem.ButtonEvent.fromValue(buffer.get());
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.putShort(button);
        buffer.put(event.getValue());
    }

    // Getter methods
    /**
     * Gets the button.
     * @since 1.0.0
     */
    public short getButton() {
        return button;
    }

    /**
     * Gets the event.
     * @since 1.0.0
     */
    public DroneSystem.ButtonEvent getEvent() {
        return event;
    }
}
