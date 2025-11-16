/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.display;

import com.otabi.jcodroneedu.Drone;
import com.otabi.jcodroneedu.DisplayController;

/**
 * Service for rendering images on the controller display.
 * 
 * <p>This component encapsulates the display rendering strategy, including
 * the 0x88 DisplayDrawImage batch protocol and the interleaved transmission
 * approach to ensure reliable, consistent rendering.</p>
 */
public class DisplayService {
    
    private final Drone drone;
    
    /**
     * Creates a new display service.
     * 
     * @param drone the drone instance for sending display commands
     */
    public DisplayService(Drone drone) {
        this.drone = drone;
    }
    
    /**
     * Draws a canvas image on the controller display using efficient batch transmission.
     * 
     * <p>This method implements the 0x88 DisplayDrawImage batch protocol with an
     * interleaved transmission strategy to maximize reliability while avoiding
     * controller buffer saturation.</p>
     * 
     * <p><strong>Transmission Strategy:</strong></p>
     * <ul>
     *   <li>Canvas split into 8 chunks of 128 bytes each (8-pixel-high rows)</li>
     *   <li>Chunks sent 5 times in interleaved order: 1-2-3-4-5-6-7-8, repeat 5x</li>
     *   <li>15ms delay between sends allows controller rendering without buffer overflow</li>
     *   <li>Each chunk is echoed back by controller (acknowledgment within ~1-10ms)</li>
     * </ul>
     * 
     * <p><strong>Performance:</strong></p>
     * <ul>
     *   <li>5 passes × 8 chunks × 15ms delay = ~600ms base transmission time</li>
     *   <li>Plus rendering time at controller (typically ~400-600ms)</li>
     *   <li>Total time: ~1.0-1.2 seconds for full canvas</li>
     * </ul>
     * 
     * @param canvas the canvas to render on the display
     */
    public void draw(DisplayController canvas) {
        byte[] imageData = canvas.toByteArray();
        
        // Clear the screen first to ensure clean state
        drone.controllerClearScreen();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Extract chunks from image data
        byte[][] chunks = new byte[8][128];
        for (int rowGroup = 0; rowGroup < 8; rowGroup++) {
            int byteOffset = rowGroup * 128;
            System.arraycopy(imageData, byteOffset, chunks[rowGroup], 0, 128);
        }
        
        // Send all chunks 5 times, interleaved (1-2-3-4-5-6-7-8, repeat 5x)
        final int MAX_ATTEMPTS = 5;
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            for (int rowGroup = 0; rowGroup < 8; rowGroup++) {
                int yPosition = rowGroup * 8;
                try {
                    drone.controllerDrawImage(0, yPosition, 128, 8, chunks[rowGroup]);
                    // Delay between sends to allow controller to process
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
