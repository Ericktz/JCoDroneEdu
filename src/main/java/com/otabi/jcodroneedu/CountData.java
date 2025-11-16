/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

/**
 * Represents flight count statistics from the drone.
 * 
 * <p>This class provides a type-safe, Java-idiomatic way to access count data,
 * as an alternative to the Python-compatible {@code Object[]} array returned by
 * {@link Drone#getCountData()}.</p>
 * 
 * <p>Count data includes cumulative statistics about the drone's usage:
 * <ul>
 *   <li>System operation time (total time powered on)</li>
 *   <li>Flight time (total time motors running)</li>
 *   <li>Number of takeoffs performed</li>
 *   <li>Number of landings performed</li>
 *   <li>Number of accidents/crashes detected</li>
 * </ul>
 * 
 * <p><strong>Usage Example:</strong></p>
 * <pre>
 * Drone drone = new Drone();
 * drone.pair();
 * 
 * // Java-style composite object
 * CountData data = drone.getCountDataObject();
 * System.out.println("Flight time: " + data.getFlightTime() + " seconds");
 * System.out.println("Takeoffs: " + data.getTakeoffCount());
 * System.out.println("Landings: " + data.getLandingCount());
 * 
 * // Or access the timestamp
 * System.out.println("Data retrieved at: " + data.getTimestamp());
 * </pre>
 * 
 * @see Drone#getCountDataObject()
 * @see Drone#getCountData()
 * @since 1.0.0
 */
public class CountData {
    private final double timestamp;
    private final int flightTime;
    private final int takeoffCount;
    private final int landingCount;
    private final int accidentCount;
    
    /**
     * Constructs a CountData object with the specified values.
     * 
     * @param timestamp Timestamp when the data was retrieved (seconds since program start)
     * @param flightTime Total flight time in seconds (motors running)
     * @param takeoffCount Total number of takeoffs performed
     * @param landingCount Total number of landings performed
     * @param accidentCount Total number of accidents/crashes detected
     */
    public CountData(double timestamp, int flightTime, int takeoffCount, int landingCount, int accidentCount) {
        this.timestamp = timestamp;
        this.flightTime = flightTime;
        this.takeoffCount = takeoffCount;
        this.landingCount = landingCount;
        this.accidentCount = accidentCount;
    }
    
    /**
     * Gets the timestamp when this data was retrieved.
     * 
     * @return Timestamp in seconds since program start
      * @since 1.0.0
     */
    public double getTimestamp() {
        return timestamp;
    }
    
    /**
     * Gets the total flight time.
     * This is the cumulative time the motors have been running.
     * 
     * @return Flight time in seconds
      * @since 1.0.0
     */
    public int getFlightTime() {
        return flightTime;
    }
    
    /**
     * Gets the total number of takeoffs.
     * 
     * @return Number of takeoffs performed
      * @since 1.0.0
     */
    public int getTakeoffCount() {
        return takeoffCount;
    }
    
    /**
     * Gets the total number of landings.
     * 
     * @return Number of landings performed
      * @since 1.0.0
     */
    public int getLandingCount() {
        return landingCount;
    }
    
    /**
     * Gets the total number of accidents/crashes.
     * The drone detects accidents using accelerometer threshold detection.
     * 
     * @return Number of accidents detected
      * @since 1.0.0
     */
    public int getAccidentCount() {
        return accidentCount;
    }
    
    /**
     * Returns a string representation of the count data.
     * 
     * @return Human-readable string with all count statistics
      * @since 1.0.0
     */
    @Override
    /** @since 1.0.0 */
    public String toString() {
        return String.format("CountData{timestamp=%.2f, flightTime=%d, takeoffs=%d, landings=%d, accidents=%d}",
                timestamp, flightTime, takeoffCount, landingCount, accidentCount);
    }
}
