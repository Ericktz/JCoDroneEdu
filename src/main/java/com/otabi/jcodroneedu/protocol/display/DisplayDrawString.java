/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.display;

import com.otabi.jcodroneedu.protocol.Serializable;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Protocol class for drawing a string on the controller display screen.
 * Python equivalent: DisplayDrawString
 */
public class DisplayDrawString implements Serializable {
    
    private short x;
    private short y;
    private DisplayFont font;
    private DisplayPixel pixel;
    private String message;

    /**
     * Default constructor with default font and white pixel.
     */
    public DisplayDrawString() {
        this.x = 0;
        this.y = 0;
        this.font = DisplayFont.LIBERATION_MONO_5X8;
        this.pixel = DisplayPixel.WHITE;
        this.message = "";
    }

    /**
     * Constructor with specified parameters.
     * @param x X coordinate
     * @param y Y coordinate
     * @param font The font to use
     * @param pixel The pixel type to draw
     * @param message The message to display
     */
    public DisplayDrawString(int x, int y, DisplayFont font, DisplayPixel pixel, String message) {
        this.x = (short) x;
        this.y = (short) y;
        this.font = font;
        this.pixel = pixel;
        this.message = message != null ? message : "";
    }

    // Getters and setters
    /**
     * Gets the x.
     * @since 1.0.0
     */
    public int getX() { return x; }
    /** @since 1.0.0 */
    public void setX(int x) { this.x = (short) x; }
    
    /** @since 1.0.0 */
    public int getY() { return y; }
    /**
     * Sets the y.
     * @since 1.0.0
     */
    public void setY(int y) { this.y = (short) y; }
    
    /** @since 1.0.0 */
    public DisplayFont getFont() { return font; }
    /** @since 1.0.0 */
    public void setFont(DisplayFont font) { this.font = font; }
    
    /**
     * Gets the pixel.
     * @since 1.0.0
     */
    public DisplayPixel getPixel() { return pixel; }
    /** @since 1.0.0 */
    public void setPixel(DisplayPixel pixel) { this.pixel = pixel; }
    
    /** @since 1.0.0 */
    public String getMessage() { return message; }
    /**
     * Sets the message.
     * @since 1.0.0
     */
    public void setMessage(String message) { this.message = message != null ? message : ""; }

    @Override
    /** @since 1.0.0 */
    public byte getSize() {
        return (byte) (6 + message.getBytes(StandardCharsets.UTF_8).length);
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer) {
        buffer.putShort(x);
        buffer.putShort(y);
        buffer.put(font.value());
        buffer.put(pixel.value());
        buffer.put(message.getBytes(StandardCharsets.UTF_8));
    }

    // Note: toArray() inherited from Serializable interface (handles LITTLE_ENDIAN)

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException {
        if (buffer.remaining() < 6) {
            throw new InvalidDataSizeException(6, buffer.remaining());
        }
        
        this.x = buffer.getShort();
        this.y = buffer.getShort();
        this.font = DisplayFont.fromByte(buffer.get());
        this.pixel = DisplayPixel.fromByte(buffer.get());
        
        // Read remaining bytes as message
        byte[] messageBytes = new byte[buffer.remaining()];
        buffer.get(messageBytes);
        this.message = new String(messageBytes, StandardCharsets.UTF_8);
    }
}
