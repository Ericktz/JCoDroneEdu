/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

import com.otabi.jcodroneedu.protocol.dronestatus.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.otabi.jcodroneedu.protocol.DataType;
import com.otabi.jcodroneedu.protocol.cardreader.CardColor;
import com.otabi.jcodroneedu.protocol.settings.Trim;
import com.otabi.jcodroneedu.protocol.controllerinput.Joystick;
import com.otabi.jcodroneedu.protocol.controllerinput.Button;

public class DroneStatus
{
    private Attitude attitude;
    private Altitude altitude;
    private Flow flow;
    private Motion motion;
    private Position position;
    private Range range;
    private RawFlow rawFlow;
    private RawMotion rawMotion;
    private State state;
    private CardColor cardColor;
    private Trim trim;
    private Joystick joystick;
    private Button button;

    private final Map<String, CompletableFuture<Object>> futures = new ConcurrentHashMap<>();

    public DroneStatus() {
        this.attitude = new Attitude();
        this.altitude = new Altitude();
        this.flow = new Flow();
        this.motion = new Motion();
        this.position = new Position();
        this.range = new Range();
        this.rawFlow = new RawFlow();
        this.rawMotion = new RawMotion();
        this.state = new State();
        this.cardColor = new CardColor();
        this.trim = new Trim();
        this.joystick = new Joystick();
        this.button = new Button();
    }

    /**
     * waitForUpdate method.
     * @since 1.0.0
     */
    public void waitForUpdate(DataType dataType)
    {
        CompletableFuture<Object> future = new CompletableFuture<>();
        futures.put(dataType.toString(), future);

        try {
            // Block and wait for the future to be completed
            future.get(50, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.completeExceptionally(e); // Cancel or handle timeout
            System.err.println("Warning: Timeout waiting for fresh data for tag: " + dataType.toString() + ". Returning possibly stale data.");
        } finally {
            futures.remove(dataType.toString()); // Clean up the map
        }
    }

    /**
     * complete method.
     * @since 1.0.0
     */
    public void complete(DataType dataType)
    {
        CompletableFuture<Object> future = futures.remove(dataType.toString());
        if (future != null) {
            future.complete(null);
        }
    }

    /**
     * Gets the attitude.
     * @since 1.0.0
     */
    public Attitude getAttitude()
    {
        return attitude;
    }

    /**
     * Sets the attitude.
     * @since 1.0.0
     */
    public void setAttitude(Attitude attitude)
    {
        this.attitude = attitude;
    }

    /**
     * Gets the altitude.
     * @since 1.1.0
     */
    public Altitude getAltitude()
    {
        return altitude;
    }

    /**
     * Sets the altitude.
     * @since 1.0.0
     */
    public void setAltitude(Altitude altitude)
    {
        this.altitude = altitude;
    }

    /**
     * Gets the flow.
     * @since 1.0.0
     */
    public Flow getFlow()
    {
        return flow;
    }

    /**
     * Sets the flow.
     * @since 1.0.0
     */
    public void setFlow(Flow flow)
    {
        this.flow = flow;
    }

    /**
     * Gets the motion.
     * @since 1.0.0
     */
    public Motion getMotion()
    {
        return motion;
    }

    /**
     * Sets the motion.
     * @since 1.0.0
     */
    public void setMotion(Motion motion)
    {
        this.motion = motion;
    }

    /**
     * Gets the position.
     * @since 1.0.0
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Sets the position.
     * @since 1.0.0
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }

    /**
     * Gets the range.
     * @since 1.0.0
     */
    public Range getRange()
    {
        return range;
    }

    /**
     * Sets the range.
     * @since 1.0.0
     */
    public void setRange(Range range)
    {
        this.range = range;
    }

    /**
     * Gets the raw flow.
     * @since 1.0.0
     */
    public RawFlow getRawFlow()
    {
        return rawFlow;
    }

    /**
     * Sets the raw flow.
     * @since 1.0.0
     */
    public void setRawFlow(RawFlow rawFlow)
    {
        this.rawFlow = rawFlow;
    }

    /**
     * Gets the raw motion.
     * @since 1.0.0
     */
    public RawMotion getRawMotion()
    {
        return rawMotion;
    }

    /**
     * Sets the raw motion.
     * @since 1.0.0
     */
    public void setRawMotion(RawMotion rawMotion)
    {
        this.rawMotion = rawMotion;
    }

    /**
     * Gets the state.
     * @since 1.0.0
     */
    public State getState()
    {
        return state;
    }

    /**
     * Sets the state.
     * @since 1.0.0
     */
    public void setState(State state)
    {
        this.state = state;
    }

    /**
     * Gets the card color.
     * @since 1.0.0
     */
    public CardColor getCardColor()
    {
        return cardColor;
    }

    /**
     * Sets the card color.
     * @since 1.0.0
     */
    public void setCardColor(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    /**
     * Gets the trim.
     * @since 1.0.0
     */
    public Trim getTrim()
    {
        return trim;
    }

    /**
     * Sets the trim.
     * @since 1.0.0
     */
    public void setTrim(Trim trim)
    {
        this.trim = trim;
    }

    /**
     * Gets the joystick.
     * @since 1.0.0
     */
    public Joystick getJoystick()
    {
        return joystick;
    }

    /**
     * Sets the joystick.
     * @since 1.0.0
     */
    public void setJoystick(Joystick joystick)
    {
        this.joystick = joystick;
    }

    /**
     * Gets the button.
     * @since 1.0.0
     */
    public Button getButton()
    {
        return button;
    }

    /**
     * Sets the button.
     * @since 1.0.0
     */
    public void setButton(Button button)
    {
        this.button = button;
    }
}
