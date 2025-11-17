/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

import static com.otabi.jcodroneedu.protocol.Validator.isValidUnsignedByte;

public class Color implements Serializable
{
    public static final byte COLOR_SIZE = 3;

    private byte r;
    private byte g;
    private byte b;

    public Color(byte r, byte g, byte b)
    {
        // Interpret the incoming signed Java bytes as unsigned values in 0..255
        if (!(isValidUnsignedByte(r & 0xFF) && isValidUnsignedByte(g & 0xFF) && isValidUnsignedByte(b & 0xFF)))
        {
            throw new IllegalArgumentException("Colors must be in the range of 0 to 255.");
        }
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.r = buffer.get();
        this.g = buffer.get();
        this.b = buffer.get();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(r);
        buffer.put(g);
        buffer.put(b);
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return COLOR_SIZE;
    }

    /**
     * Creates a Color from standard RGB values (0-255 range).
     * 
     * <p>Converts from the standard RGB color space (0-255) to the controller's
     * internal format (0-127 range). This factory method is the recommended way
     * to create Color objects from standard RGB values.</p>
     * 
     * @param red Red component (0-255)
     * @param green Green component (0-255)
     * @param blue Blue component (0-255)
     * @return A Color object with scaled values for the controller
     * @throws IllegalArgumentException if any component is outside 0-255 range
     * @since 1.4.0
     */
    public static Color fromRGB(int red, int green, int blue) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException("Red must be between 0 and 255, got: " + red);
        }
        if (green < 0 || green > 255) {
            throw new IllegalArgumentException("Green must be between 0 and 255, got: " + green);
        }
        if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Blue must be between 0 and 255, got: " + blue);
        }
        
        // Scale from 0-255 range to 0-127 range for controller
        byte r = (byte) Math.min(127, red * 127 / 255);
        byte g = (byte) Math.min(127, green * 127 / 255);
        byte b = (byte) Math.min(127, blue * 127 / 255);
        
        return new Color(r, g, b);
    }
}
