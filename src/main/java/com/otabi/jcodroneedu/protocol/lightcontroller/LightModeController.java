/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.lightcontroller;

import java.nio.ByteBuffer;

public class LightModeController extends LightMode
{
    public LightModeController(LightModes mode, short interval)
    {
        super(mode, interval);
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer)
    {
        setMode(LightModesController.fromValue(buffer.get()));
    }
}
