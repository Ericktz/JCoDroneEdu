/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.protocol.dronestatus;

import com.otabi.jcodroneedu.DroneSystem;
import com.otabi.jcodroneedu.protocol.InvalidDataSizeException;
import com.otabi.jcodroneedu.protocol.Serializable;

import java.nio.ByteBuffer;

public class State implements Serializable
{
    public static final byte STATE_SIZE = 8;

    private DroneSystem.ModeSystem modeSystem;
    private DroneSystem.ModeFlight modeFlight;
    private DroneSystem.ModeControlFlight modeControlFlight;
    private DroneSystem.ModeMovement modeMovement;
    private DroneSystem.Headless headless;
    private byte controlSpeed;
    private DroneSystem.SensorOrientation sensorOrientation;
    private byte battery;

    public State()
    {
    }

    public State(DroneSystem.ModeSystem modeSystem, DroneSystem.ModeFlight modeFlight, DroneSystem.ModeControlFlight modeControlFlight, DroneSystem.ModeMovement modeMovement, DroneSystem.Headless headless, byte controlSpeed, DroneSystem.SensorOrientation sensorOrientation, byte battery)
    {
        this.modeSystem = modeSystem;
        this.modeFlight = modeFlight;
        this.modeControlFlight = modeControlFlight;
        this.modeMovement = modeMovement;
        this.headless = headless;
        this.controlSpeed = controlSpeed;
        this.sensorOrientation = sensorOrientation;
        this.battery = battery;
    }

    @Override
    /**
     * Gets the size.
     * @since 1.0.0
     */
    public byte getSize()
    {
        return STATE_SIZE;
    }

    @Override
    /**
     * unpack method.
     * @since 1.0.0
     */
    public void unpack(ByteBuffer buffer) throws InvalidDataSizeException
    {
        this.modeSystem = DroneSystem.ModeSystem.fromValue(buffer.get());
        this.modeFlight = DroneSystem.ModeFlight.fromValue(buffer.get());
        this.modeControlFlight = DroneSystem.ModeControlFlight.fromValue(buffer.get());
        this.modeMovement = DroneSystem.ModeMovement.fromValue(buffer.get());
        this.headless = DroneSystem.Headless.fromValue(buffer.get());
        this.controlSpeed = buffer.get();
        this.sensorOrientation = DroneSystem.SensorOrientation.fromValue(buffer.get());
        this.battery = buffer.get();
    }

    @Override
    /**
     * pack method.
     * @since 1.0.0
     */
    public void pack(ByteBuffer buffer)
    {
        buffer.put(this.modeSystem.getValue());
        buffer.put(this.modeFlight.getValue());
        buffer.put(this.modeControlFlight.getValue());
        buffer.put(this.modeMovement.getValue());
        buffer.put(this.headless.getValue());
        buffer.put(this.controlSpeed);
        buffer.put(this.sensorOrientation.getValue());
        buffer.put(this.battery);
    }

    /**
     * Gets the battery.
     * @since 1.0.0
     */
    public byte getBattery()
    {
        return battery;
    }

    /**
     * Gets the mode flight.
     * @since 1.0.0
     */
    public DroneSystem.ModeFlight getModeFlight() {
        return modeFlight;
    }

    /**
     * Gets the mode movement.
     * @since 1.0.0
     */
    public DroneSystem.ModeMovement getModeMovement() {
        return modeMovement;
    }

    /**
     * Checks if take off.
     * @since 1.0.0
     */
    public boolean isTakeOff() {
        return this.modeFlight == DroneSystem.ModeFlight.TAKE_OFF;
    }

    /**
     * Checks if landing.
     * @since 1.0.0
     */
    public boolean isLanding() {
        return this.modeFlight == DroneSystem.ModeFlight.LANDING;
    }

    /**
     * Checks if flight.
     * @since 1.0.0
     */
    public boolean isFlight() {
        return this.modeFlight == DroneSystem.ModeFlight.FLIGHT;
    }

    /**
     * Checks if ready.
     * @since 1.0.0
     */
    public boolean isReady() {
        return this.modeFlight == DroneSystem.ModeFlight.READY;
    }
}
