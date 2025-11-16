/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.linkmanager;

import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class Message implements Serializable {
    public char[] message;

    public Message()
    {
    }

    public Message(char[] message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = message.toCharArray();
    }

    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize() {
        return (byte) message.length;
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        CharBuffer cb = buffer.asCharBuffer();
        cb.put(message);
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
        this.message = new char[charBuffer.remaining()];
        charBuffer.get(this.message);
    }

}