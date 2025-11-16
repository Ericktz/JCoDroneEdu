/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.cardreader;

public class CardFunctionList extends CardList
{
    public CardFunctionList(byte index, byte size, byte cardIndex, byte[] card)
    {
        super(index, size, cardIndex, card);
    }

    public CardFunctionList()
    {

    }
}
