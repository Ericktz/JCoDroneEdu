/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.examples;

import com.otabi.jcodroneedu.Drone;

/**
 * Test: Display the quadcopter drone image on the controller screen.
 * 
 * <p>Demonstrates the simplified {@link Drone#getImageData(String)} method
 * for loading and displaying images on the controller display without manual
 * image processing or canvas manipulation.</p>
 * 
 * <p>The image is automatically:</p>
 * <ul>
 *   <li>Loaded from the test resources directory</li>
 *   <li>Scaled to 128x64 pixels (controller display size)</li>
 *   <li>Converted to monochrome (black and white) format</li>
 *   <li>Packed into the byte array format required by the controller</li>
 * </ul>
 */
public class DroneImageDisplayTest {
    public static void main(String[] args) throws Exception {
        try (Drone drone = new Drone()) {
            drone.pair();
            System.out.println("Connected to drone");
            
            String imagePath = "src/test/resources/images/quadcopter_drone.png";
            
            // Draw 10 times
            for (int iteration = 1; iteration <= 10; iteration++) {
                System.out.println("Iteration " + iteration + "/10 - Drawing quadcopter drone...");
                
                // Load and convert image using the simplified getImageData() API
                byte[] imageData = drone.getImageData(imagePath);
                
                if (imageData.length == 0) {
                    System.out.println("ERROR: Failed to load image from " + imagePath);
                    return;
                }
                
                // Create a canvas and render the full-screen image
                var canvas = drone.controllerCreateCanvas();
                var graphics = canvas.getGraphics();
                
                // Draw image data directly using BufferedImage approach
                // Create BufferedImage from the byte data
                java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                    128, 64, java.awt.image.BufferedImage.TYPE_BYTE_BINARY);
                
                // Unpack byte array back into BufferedImage
                int dataIndex = 0;
                for (int y = 0; y < 64; y += 8) {
                    for (int x = 0; x < 128; x++) {
                        byte pixelByte = imageData[dataIndex++];
                        for (int bit = 0; bit < 8 && (y + bit) < 64; bit++) {
                            boolean isBlack = (pixelByte & (1 << bit)) != 0;
                            if (isBlack) {
                                img.setRGB(x, y + bit, 0xFF000000); // Black
                            } else {
                                img.setRGB(x, y + bit, 0xFFFFFFFF); // White
                            }
                        }
                    }
                }
                
                // Draw the image on the canvas
                graphics.drawImage(img, 0, 0, null);
                
                // Display the canvas on controller
                drone.controllerDrawCanvas(canvas);
                
                if (iteration < 10) {
                    Thread.sleep(3000);
                }
            }
            
            System.out.println("Test complete!");
        }
    }
}
