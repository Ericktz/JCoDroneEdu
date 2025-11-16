/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.buzzer;

import com.otabi.jcodroneedu.protocol.Serializable;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import java.nio.ByteBuffer;

/**
 * Buzzer protocol class for sending buzzer commands to drone or controller.
 * Matches the Python Buzzer class structure and behavior.
 */
public class Buzzer implements Serializable {
    private BuzzerMode mode;
    private int value;
    private int time;

    /**
     * Default constructor - creates a stopped buzzer
     */
    public Buzzer() {
        this.mode = BuzzerMode.STOP;
        this.value = Note.MUTE.getValue();
        this.time = 1;
    }

    /**
     * Constructor with parameters
     */
    public Buzzer(BuzzerMode mode, int value, int time) {
        this.mode = mode;
        this.value = value;
        this.time = time;
    }

    // Getters
    /**
     * Gets the mode.
     * @since 1.0.0
     */
    public BuzzerMode getMode() { return mode; }
    /** @since 1.0.0 */
    public int getValue() { return value; }
    /** @since 1.0.0 */
    public int getTime() { return time; }

    // Setters
    /**
     * Sets the mode.
     * @since 1.0.0
     */
    public void setMode(BuzzerMode mode) { this.mode = mode; }
    /** @since 1.0.0 */
    public void setValue(int value) { this.value = value; }
    /** @since 1.0.0 */
    public void setTime(int time) { this.time = time; }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return 5; // mode(1) + value(2) + time(2)
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer) {
        buffer.put((byte) mode.getValue());
        buffer.putShort((short) value);
        buffer.putShort((short) time);
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
        
        int modeValue = buffer.get() & 0xFF;
        this.mode = BuzzerMode.fromValue(modeValue);
        this.value = buffer.getShort() & 0xFFFF;
        this.time = buffer.getShort() & 0xFFFF;
    }

    // Static factory methods for common operations
    /**
     * stop method.
     * @since 1.0.0
     */
    public static Buzzer stop() {
        return new Buzzer(BuzzerMode.STOP, Note.MUTE.getValue(), 1);
    }

    /**
     * mute method.
     * @since 1.0.0
     */
    public static Buzzer mute(int duration) {
        return new Buzzer(BuzzerMode.MUTE, Note.MUTE.getValue(), duration);
    }

    /**
     * note method.
     * @since 1.0.0
     */
    public static Buzzer note(Note note, int duration) {
        return new Buzzer(BuzzerMode.SCALE, note.getValue(), duration);
    }

    /**
     * frequency method.
     * @since 1.0.0
     */
    public static Buzzer frequency(int hz, int duration) {
        return new Buzzer(BuzzerMode.HZ, hz, duration);
    }

    @Override
    /**
     * toString method.
     * @since 1.0.0
     */
    public String toString() {
        return String.format("Buzzer(mode=%s, value=%d, time=%d)", mode, value, time);
    }
}
