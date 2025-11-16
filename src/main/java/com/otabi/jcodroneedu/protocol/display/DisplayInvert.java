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
 * Protocol class for inverting a specific area of the controller display screen.
 * Python equivalent: DisplayInvert
 */
public class DisplayInvert implements Serializable {
    
    private short x;
    private short y;
    private short width;
    private short height;

    /**
     * Default constructor.
     */
    public DisplayInvert() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Constructor with specified parameters.
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of area to invert
     * @param height Height of area to invert
     */
    public DisplayInvert(int x, int y, int width, int height) {
        this.x = (short) x;
        this.y = (short) y;
        this.width = (short) width;
        this.height = (short) height;
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
    public int getWidth() { return width; }
    /** @since 1.0.0 */
    public void setWidth(int width) { this.width = (short) width; }
    
    /**
     * Gets the height.
     * @since 1.0.0
     */
    public int getHeight() { return height; }
    /** @since 1.0.0 */
    public void setHeight(int height) { this.height = (short) height; }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return 8;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer) {
        buffer.putShort(x);
        buffer.putShort(y);
        buffer.putShort(width);
        buffer.putShort(height);
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
        this.width = buffer.getShort();
        this.height = buffer.getShort();
    }
}
