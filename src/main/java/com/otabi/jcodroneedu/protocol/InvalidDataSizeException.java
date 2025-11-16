/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol;

public class InvalidDataSizeException extends RuntimeException {
    public InvalidDataSizeException(int expected, int received) {
        super(
                String.format(
                        "Invalid message data size, expected: %d, received: %d",
                        expected,
                        received)
        );
    }
}
