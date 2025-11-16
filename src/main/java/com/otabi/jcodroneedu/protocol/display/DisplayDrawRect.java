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
 * Protocol class for drawing a rectangle on the controller display screen.
 * Python equivalent: DisplayDrawRect
 */
public class DisplayDrawRect implements Serializable {
    
    private short x;
    private short y;
    private short width;
    private short height;
    private DisplayPixel pixel;
    private boolean flagFill;
    private DisplayLine line;

    /**
     * Default constructor with white pixel, filled, and solid line defaults.
     */
    public DisplayDrawRect() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.pixel = DisplayPixel.WHITE;
        this.flagFill = true;
        this.line = DisplayLine.SOLID;
    }

    /**
     * Constructor with specified parameters.
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of rectangle
     * @param height Height of rectangle
     * @param pixel The pixel type to draw
     * @param flagFill Whether to fill the rectangle
     * @param line The line style for outline
     */
    public DisplayDrawRect(int x, int y, int width, int height, DisplayPixel pixel, boolean flagFill, DisplayLine line) {
        this.x = (short) x;
        this.y = (short) y;
        this.width = (short) width;
        this.height = (short) height;
        this.pixel = pixel;
        this.flagFill = flagFill;
        this.line = line;
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
    
    /** @since 1.0.0 */
    public DisplayPixel getPixel() { return pixel; }
    /**
     * Sets the pixel.
     * @since 1.0.0
     */
    public void setPixel(DisplayPixel pixel) { this.pixel = pixel; }
    
    /** @since 1.0.0 */
    public boolean isFlagFill() { return flagFill; }
    /** @since 1.0.0 */
    public void setFlagFill(boolean flagFill) { this.flagFill = flagFill; }
    
    /**
     * Gets the line.
     * @since 1.0.0
     */
    public DisplayLine getLine() { return line; }
    /** @since 1.0.0 */
    public void setLine(DisplayLine line) { this.line = line; }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return 11;
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
        buffer.put(pixel.value());
        buffer.put((byte) (flagFill ? 1 : 0));
        buffer.put(line.value());
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
        this.pixel = DisplayPixel.fromByte(buffer.get());
        this.flagFill = buffer.get() != 0;
        this.line = DisplayLine.fromByte(buffer.get());
    }
}
