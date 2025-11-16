/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import java.nio.ByteBuffer;

public interface LightModes
{
    public byte getValue();

    public LightModes getModeFromBuffer(ByteBuffer buffer);
}
