/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

public class Validator {
    public static boolean isValidUnsignedByte(int value) {
        return ((value & 0xFFFFFF00) == 0);
    }

    public static boolean isValidControl(int value) {
        return ((-100 <= value) && (value <= 100));
    }
}
