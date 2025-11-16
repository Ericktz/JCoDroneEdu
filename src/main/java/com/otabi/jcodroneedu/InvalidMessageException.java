/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

import java.security.InvalidParameterException;

public class InvalidMessageException extends InvalidParameterException {
    public InvalidMessageException(String message) {
        super(message);
    }
}
