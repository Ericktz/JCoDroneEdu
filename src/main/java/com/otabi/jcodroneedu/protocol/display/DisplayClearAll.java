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
 * Protocol class for clearing the entire controller display screen.
 * Python equivalent: DisplayClearAll
 */
public class DisplayClearAll implements Serializable {
    
    private DisplayPixel pixel;

    /**
     * Default constructor with white pixel default.
     */
    public DisplayClearAll() {
        this.pixel = DisplayPixel.WHITE;
    }

    /**
     * Constructor with specified pixel type.
     * @param pixel The pixel type to use for clearing
     */
    public DisplayClearAll(DisplayPixel pixel) {
        this.pixel = pixel;
    }

    /**
     * Gets the pixel.
     * @since 1.0.0
     */
    public DisplayPixel getPixel() {
        return pixel;
    }

    /**
     * Sets the pixel.
     * @since 1.0.0
     */
    public void setPixel(DisplayPixel pixel) {
        this.pixel = pixel;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return 1;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer) {
        buffer.put(pixel.value());
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException {
        if (buffer.remaining() < getSize()) {
            throw new InvalidDataSizeException(getSize(), buffer.remaining());
        }
        
        this.pixel = DisplayPixel.fromByte(buffer.get());
    }
}
