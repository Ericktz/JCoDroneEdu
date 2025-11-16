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

/**
 * Protocol class for drawing a point on the controller display screen.
 * Python equivalent: DisplayDrawPoint
 */
public class DisplayDrawPoint implements Serializable {
    
    private short x;
    private short y;
    private DisplayPixel pixel;

    /**
     * Default constructor with white pixel default.
     */
    public DisplayDrawPoint() {
        this.x = 0;
        this.y = 0;
        this.pixel = DisplayPixel.WHITE;
    }

    /**
     * Constructor with specified parameters.
     * @param x X coordinate
     * @param y Y coordinate
     * @param pixel The pixel type to draw
     */
    public DisplayDrawPoint(int x, int y, DisplayPixel pixel) {
        this.x = (short) x;
        this.y = (short) y;
        this.pixel = pixel;
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
    public DisplayPixel getPixel() { return pixel; }
    /** @since 1.0.0 */
    public void setPixel(DisplayPixel pixel) { this.pixel = pixel; }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return 5;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer) {
        buffer.putShort(x);
        buffer.putShort(y);
        buffer.put(pixel.value());
    }

    // Note: toArray() inherited from Serializable interface (handles LITTLE_ENDIAN)

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException {
        if (buffer.remaining() < getSize()) {
            throw new InvalidDataSizeException(getSize(), buffer.remaining());
        }
        
        this.x = buffer.getShort();
        this.y = buffer.getShort();
        this.pixel = DisplayPixel.fromByte(buffer.get());
    }
}
