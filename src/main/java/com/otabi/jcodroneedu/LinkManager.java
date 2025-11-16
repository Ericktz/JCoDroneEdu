/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

import com.otabi.jcodroneedu.protocol.linkmanager.Error;
import com.otabi.jcodroneedu.protocol.linkmanager.*;

/**
 * Manages and stores the state related to the communication link and device identity.
 * This class acts as a cache for non-real-time, static, or semi-static data
 * such as device information, addresses, and connection status. It is populated
 * by the Receiver as messages are parsed from the drone.
 */
public class LinkManager {

    private Address address;
    private Error error;
    private Information information;
    private Message lastMessage;
    private Pairing pairing;
    private Ping lastPing;
    private Registration registration;
    private Rssi rssi;
    private SystemInformation systemInformation;

    // --- Getters and Setters ---

    /**
     * Gets the address.
     * @since 1.0.0
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * @since 1.0.0
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the error.
     * @since 1.0.0
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the error.
     * @since 1.0.0
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Gets the information.
     * @since 1.0.0
     */
    public Information getInformation() {
        return information;
    }

    /**
     * Sets the information.
     * @since 1.0.0
     */
    public void setInformation(Information information) {
        this.information = information;
    }

    /**
     * Gets the last message.
     * @since 1.0.0
     */
    public Message getLastMessage() {
        return lastMessage;
    }

    /**
     * Sets the last message.
     * @since 1.0.0
     */
    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    /**
     * Gets the pairing.
     * @since 1.0.0
     */
    public Pairing getPairing() {
        return pairing;
    }

    /**
     * Sets the pairing.
     * @since 1.0.0
     */
    public void setPairing(Pairing pairing) {
        this.pairing = pairing;
    }

    /**
     * Gets the last ping.
     * @since 1.0.0
     */
    public Ping getLastPing() {
        return lastPing;
    }

    /**
     * Sets the last ping.
     * @since 1.0.0
     */
    public void setLastPing(Ping lastPing) {
        this.lastPing = lastPing;
    }

    /**
     * Gets the registration.
     * @since 1.0.0
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * Sets the registration.
     * @since 1.0.0
     */
    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    /**
     * Gets the rssi.
     * @since 1.0.0
     */
    public Rssi getRssi() {
        return rssi;
    }

    /**
     * Sets the rssi.
     * @since 1.0.0
     */
    public void setRssi(Rssi rssi) {
        this.rssi = rssi;
    }

    /**
     * Gets the system information.
     * @since 1.0.0
     */
    public SystemInformation getSystemInformation() {
        return systemInformation;
    }

    /**
     * Sets the system information.
     * @since 1.0.0
     */
    public void setSystemInformation(SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }
}
