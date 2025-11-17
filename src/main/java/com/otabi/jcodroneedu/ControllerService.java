/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

import com.otabi.jcodroneedu.protocol.DeviceType;
import com.otabi.jcodroneedu.protocol.lightcontroller.LightDefault;
import com.otabi.jcodroneedu.protocol.lightcontroller.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service for rendering images on the controller display.
 * 
 * <p>This component encapsulates the display rendering strategy, including
 * the 0x88 DisplayDrawImage batch protocol and the interleaved transmission
 * approach to ensure reliable, consistent rendering.</p>
 */
public class ControllerService {
    
    private static final Logger log = LogManager.getLogger(ControllerService.class);
    private final Drone drone;
    
    /**
     * Creates a new display service.
     * 
     * @param drone the drone instance for sending display commands
     */
    public ControllerService(Drone drone) {
        this.drone = drone;
        log.debug("ControllerService initialized for drone communication");
    }
    
    /**
     * Draws a square on the canvas.
     * 
     * @param x top-left X coordinate
     * @param y top-left Y coordinate
     * @param width width of the square
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawSquare(int x, int y, int width, DisplayController canvas) {
        log.trace("Drawing square at ({}, {}) with width {}", x, y, width);
        canvas.drawRectangle(x, y, width, width);
    }

    /**
     * Draws a polygon on the canvas.
     * 
     * @param points the list of points forming the polygon
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawPolygon(int[][] points, DisplayController canvas) {
        if (points == null || points.length < 2) {
            log.warn("drawPolygon called with invalid points: {}", points == null ? "null" : points.length + " points");
            return;
        }
        log.trace("Drawing polygon with {} vertices", points.length);
        for (int i = 0; i < points.length; i++) {
            int nextIdx = (i + 1) % points.length;
            canvas.drawLine(points[i][0], points[i][1], points[nextIdx][0], points[nextIdx][1]);
        }
    }

    /**
     * Draws an ellipse on the canvas.
     * 
     * @param x1 bounding box top-left X
     * @param y1 bounding box top-left Y
     * @param x2 bounding box bottom-right X
     * @param y2 bounding box bottom-right Y
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawEllipse(int x1, int y1, int x2, int y2, DisplayController canvas) {
        log.trace("Drawing ellipse with bounds ({},{}) to ({},{})", x1, y1, x2, y2);
        // Approximate ellipse using circle drawing algorithm
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        int radiusX = (x2 - x1) / 2;
        int radiusY = (y2 - y1) / 2;
        
        // Bresenham's ellipse algorithm approximation
        for (int angle = 0; angle < 360; angle += 5) {
            double rad = Math.toRadians(angle);
            double nextRad = Math.toRadians(angle + 5);
            
            int x = (int)(centerX + radiusX * Math.cos(rad));
            int y = (int)(centerY + radiusY * Math.sin(rad));
            int nextX = (int)(centerX + radiusX * Math.cos(nextRad));
            int nextY = (int)(centerY + radiusY * Math.sin(nextRad));
            
            canvas.drawLine(x, y, nextX, nextY);
        }
    }

    /**
     * Draws an arc on the canvas.
     * 
     * @param x1 bounding box top-left X
     * @param y1 bounding box top-left Y
     * @param x2 bounding box bottom-right X
     * @param y2 bounding box bottom-right Y
     * @param startAngle starting angle in degrees
     * @param endAngle ending angle in degrees
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawArc(int x1, int y1, int x2, int y2, int startAngle, int endAngle, DisplayController canvas) {
        log.trace("Drawing arc from {}° to {}° in bounds ({},{}) to ({},{})", startAngle, endAngle, x1, y1, x2, y2);
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        int radiusX = (x2 - x1) / 2;
        int radiusY = (y2 - y1) / 2;
        
        int step = Math.max(1, (endAngle - startAngle) / 20);
        for (int angle = startAngle; angle < endAngle; angle += step) {
            double rad = Math.toRadians(angle);
            double nextRad = Math.toRadians(angle + step);
            
            int x = (int)(centerX + radiusX * Math.cos(rad));
            int y = (int)(centerY + radiusY * Math.sin(rad));
            int nextX = (int)(centerX + radiusX * Math.cos(nextRad));
            int nextY = (int)(centerY + radiusY * Math.sin(nextRad));
            
            canvas.drawLine(x, y, nextX, nextY);
        }
    }

    /**
     * Draws a chord on the canvas.
     * 
     * @param x1 bounding box top-left X
     * @param y1 bounding box top-left Y
     * @param x2 bounding box bottom-right X
     * @param y2 bounding box bottom-right Y
     * @param startAngle starting angle in degrees
     * @param endAngle ending angle in degrees
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawChord(int x1, int y1, int x2, int y2, int startAngle, int endAngle, DisplayController canvas) {
        log.trace("Drawing chord from {}° to {}° in bounds ({},{}) to ({},{})", startAngle, endAngle, x1, y1, x2, y2);
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        int radiusX = (x2 - x1) / 2;
        int radiusY = (y2 - y1) / 2;
        
        // Draw the arc
        int step = Math.max(1, (endAngle - startAngle) / 20);
        int firstX = -1, firstY = -1, lastX = -1, lastY = -1;
        for (int angle = startAngle; angle < endAngle; angle += step) {
            double rad = Math.toRadians(angle);
            double nextRad = Math.toRadians(angle + step);
            
            int x = (int)(centerX + radiusX * Math.cos(rad));
            int y = (int)(centerY + radiusY * Math.sin(rad));
            int nextX = (int)(centerX + radiusX * Math.cos(nextRad));
            int nextY = (int)(centerY + radiusY * Math.sin(nextRad));
            
            if (firstX == -1) {
                firstX = x;
                firstY = y;
            }
            lastX = nextX;
            lastY = nextY;
            canvas.drawLine(x, y, nextX, nextY);
        }
        
        // Draw closing chord line
        if (firstX != -1 && lastX != -1) {
            canvas.drawLine(lastX, lastY, firstX, firstY);
        }
    }

    /**
     * Draws aligned text on the canvas.
     * 
     * @param xStart starting X coordinate
     * @param xEnd ending X coordinate for alignment
     * @param y Y coordinate
     * @param text the text to draw
     * @param alignment the alignment (left, center, right)
     * @param canvas the canvas to draw on
     * @since 1.4.0
     */
    public void drawStringAlign(int xStart, int xEnd, int y, String text, String alignment, DisplayController canvas) {
        log.trace("Drawing text '{}' at y={} with alignment={}", text, y, alignment);
        int x = xStart;
        if ("center".equalsIgnoreCase(alignment)) {
            x = xStart + (xEnd - xStart) / 2 - (text.length() * 3) / 2;
        } else if ("right".equalsIgnoreCase(alignment)) {
            x = xEnd - (text.length() * 6);
        }
        canvas.getGraphics().drawString(text, x, y);
    }

    /**
     * Shows a preview of the canvas (no-op on Java, as preview is handled by the canvas object).
     * 
     * @param canvas the canvas to preview
     * @since 1.4.0
     */
    public void previewCanvas(DisplayController canvas) {
        log.trace("Canvas preview requested (no-op on Java)");
        // In Java, the canvas object itself serves as the preview mechanism
        // No additional action needed
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
     * @since 1.0.0
     */
    public void draw(DisplayController canvas) {
        log.debug("Drawing canvas to controller display (127x63 monochrome)");
        byte[] imageData = canvas.toByteArray();
        log.trace("Canvas converted to byte array: {} bytes", imageData.length);
        
        // Clear the screen first to ensure clean state
        log.debug("Clearing controller screen");
        drone.controllerClearScreen();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            log.warn("Interrupted during clear screen delay");
            Thread.currentThread().interrupt();
        }
        
        // Extract chunks from image data
        byte[][] chunks = new byte[8][128];
        for (int rowGroup = 0; rowGroup < 8; rowGroup++) {
            int byteOffset = rowGroup * 128;
            System.arraycopy(imageData, byteOffset, chunks[rowGroup], 0, 128);
        }
        log.trace("Image split into 8 chunks of 128 bytes each");
        
        // Send all chunks 5 times, interleaved (1-2-3-4-5-6-7-8, repeat 5x)
        final int MAX_ATTEMPTS = 5;
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            log.trace("Transmission pass {}/{}", attempt, MAX_ATTEMPTS);
            for (int rowGroup = 0; rowGroup < 8; rowGroup++) {
                int yPosition = rowGroup * 8;
                try {
                    log.trace("Sending chunk {} at y={}", rowGroup, yPosition);
                    drone.controllerDrawImage(0, yPosition, 128, 8, chunks[rowGroup]);
                    // Delay between sends to allow controller to process
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    log.warn("Interrupted during transmission pass {} chunk {}", attempt, rowGroup);
                    Thread.currentThread().interrupt();
                }
            }
        }
        log.debug("Canvas transmission complete (40 total chunks sent)");
    }

    /**
     * Inverts the pixels in a specific rectangular area on the controller display.
     * 
     * @param x X coordinate of top-left corner
     * @param y Y coordinate of top-left corner
     * @param width Width of area to invert
     * @param height Height of area to invert
     * @since 1.0.0
     */
    public void invertArea(int x, int y, int width, int height) {
        log.debug("Inverting display area: x={}, y={}, width={}, height={}", x, y, width, height);
        com.otabi.jcodroneedu.protocol.display.DisplayInvert invertCommand = 
            new com.otabi.jcodroneedu.protocol.display.DisplayInvert(x, y, width, height);
        
        com.otabi.jcodroneedu.protocol.Header header = new com.otabi.jcodroneedu.protocol.Header();
        header.setDataType(com.otabi.jcodroneedu.protocol.DataType.DisplayInvert);
        header.setLength(invertCommand.getSize());
        header.setFrom(com.otabi.jcodroneedu.protocol.DeviceType.Base);
        header.setTo(com.otabi.jcodroneedu.protocol.DeviceType.Controller);

        drone.transfer(header, invertCommand);
    }

    /**
     * Loads image data from a file and converts it to controller display format.
     * 
     * <p>This method loads an image file from the filesystem and converts it to a format
     * suitable for displaying on the drone's controller screen (127x63 pixels). The image
     * is automatically scaled to fit the controller display dimensions and converted to
     * monochrome (black and white) bitmap format.</p>
     * 
     * <p><strong>Supported Formats:</strong> PNG, JPG, GIF, and other formats supported by
     * Java's ImageIO.</p>
     * 
     * <p><strong>Display Format:</strong> The returned data is a byte array representing a
     * monochrome (black and white) bitmap format used by the controller display. Each byte
     * represents 8 vertical pixels, column by column.</p>
     * 
     * @param imageFileName The path to the image file including extension
     *                       (e.g., "/tmp/robot.png" or "images/logo.jpg")
     * @return A byte array representing the monochrome image data formatted for the
     *         controller display, or an empty array if the image cannot be loaded or processed
     * @since 1.4.0
     */
    public byte[] getImageData(String imageFileName) {
        log.debug("Loading image data from: {}", imageFileName);
        try {
            // Load image file
            java.io.File imageFile = new java.io.File(imageFileName);
            if (!imageFile.exists()) {
                log.warn("Image file not found: {}", imageFileName);
                return new byte[0];
            }
            log.trace("Image file exists: {} bytes", imageFile.length());
            
            // Read image with ImageIO
            java.awt.image.BufferedImage originalImage = javax.imageio.ImageIO.read(imageFile);
            if (originalImage == null) {
                log.warn("Failed to read/decode image file: {}", imageFileName);
                return new byte[0];
            }
            log.trace("Image loaded: {}x{}px", originalImage.getWidth(), originalImage.getHeight());
            
            // Scale to controller display size (128x64)
            final int CONTROLLER_WIDTH = 128;
            final int CONTROLLER_HEIGHT = 64;
            log.trace("Scaling image from {}x{} to {}x{} pixels", originalImage.getWidth(), originalImage.getHeight(), CONTROLLER_WIDTH, CONTROLLER_HEIGHT);
            java.awt.Image scaledImage = originalImage.getScaledInstance(CONTROLLER_WIDTH, CONTROLLER_HEIGHT, 
                java.awt.Image.SCALE_SMOOTH);
            
            // Convert to binary
            log.trace("Converting to monochrome (black and white) format");
            java.awt.image.BufferedImage binaryImage = new java.awt.image.BufferedImage(
                CONTROLLER_WIDTH, CONTROLLER_HEIGHT, java.awt.image.BufferedImage.TYPE_BYTE_BINARY);
            
            // Render and pack to bytes using DisplayController algorithm
            java.awt.Graphics2D graphics = binaryImage.createGraphics();
            graphics.drawImage(scaledImage, 0, 0, null);
            graphics.dispose();
            
            byte[] imageData = new byte[(CONTROLLER_WIDTH * CONTROLLER_HEIGHT) / 8];
            int dataIndex = 0;
            for (int y = 0; y < CONTROLLER_HEIGHT; y += 8) {
                for (int x = 0; x < CONTROLLER_WIDTH; x++) {
                    byte pixelByte = 0;
                    for (int bit = 0; bit < 8 && (y + bit) < CONTROLLER_HEIGHT; bit++) {
                        int rgb = binaryImage.getRGB(x, y + bit);
                        if (rgb == 0xFF000000 || rgb == -16777216) {
                            pixelByte |= (1 << bit);
                        }
                    }
                    imageData[dataIndex++] = pixelByte;
                }
            }
            
            log.debug("Successfully loaded and converted image: {} to {} bytes for controller display", imageFileName, imageData.length);
            return imageData;
        } catch (java.io.IOException e) {
            log.error("IOException while loading image file: {}", imageFileName, e);
            return new byte[0];
        } catch (Exception e) {
            log.error("Unexpected error processing image file: {}", imageFileName, e);
            return new byte[0];
        }
    }

    /**
     * Draws a pixel image/region on the controller display in batch (low-level protocol method).
     * 
     * <p><strong>Note:</strong> This is a low-level protocol method. Most users should use
     * the canvas API instead.</p>
     * 
     * The image data should be provided as a byte array in bit-packed format, where each byte
     * represents 8 vertical pixels. This format matches the controller display's internal
     * monochrome pixel layout.
     * 
     * @param x X coordinate (starting position)
     * @param y Y coordinate (starting position)
     * @param width Width of image region
     * @param height Height of image region
     * @param imageData Byte array containing pixel data in bit-packed format
     * @since 1.0.0
     */
    public void drawImage(int x, int y, int width, int height, byte[] imageData) {
        log.trace("Drawing image region at ({},{}) size {}x{}px with {} bytes", x, y, width, height, imageData == null ? "null" : imageData.length);
        com.otabi.jcodroneedu.protocol.display.DisplayDrawImage imageCommand = 
            new com.otabi.jcodroneedu.protocol.display.DisplayDrawImage(x, y, width, height, imageData);
        
        com.otabi.jcodroneedu.protocol.Header header = new com.otabi.jcodroneedu.protocol.Header();
        header.setDataType(com.otabi.jcodroneedu.protocol.DataType.DisplayDrawImage);
        header.setLength(imageCommand.getSize());
        header.setFrom(com.otabi.jcodroneedu.protocol.DeviceType.Base);
        header.setTo(com.otabi.jcodroneedu.protocol.DeviceType.Controller);

        drone.transfer(header, imageCommand);
    }

    /**
     * Sets the controller LED to a solid color.
     * 
     * <p>Controls the LED on the controller (remote control) rather than the drone.
     * This is useful for team identification or indicating controller status.</p>
     * 
     * <h3>🎯 Educational Usage:</h3>
     * <ul>
     *   <li><strong>Team Identification:</strong> Each student has a different controller color</li>
     *   <li><strong>Status Indication:</strong> Green for ready, red for error, etc.</li>
     *   <li><strong>Debugging:</strong> Controller LED for one state, drone LED for another</li>
     * </ul>
     * 
     * @param red Red component (0-255)
     * @param green Green component (0-255)
     * @param blue Blue component (0-255)
     * @param brightness Overall brightness (0-255)
     * 
     * @throws IllegalArgumentException if any color value is outside 0-255 range
     * @apiNote Equivalent to Python's {@code drone.set_controller_LED(r, g, b, brightness)}
     * @since 1.0.0
     * @educational
     */
    public void setControllerLED(int red, int green, int blue, int brightness) {
        log.debug("Setting controller LED to RGB({}, {}, {}) brightness={}", red, green, blue, brightness);
        // Validate input parameters
        if (red < DroneSystem.ColorConstants.RGB_MIN || red > DroneSystem.ColorConstants.RGB_MAX) {
            log.error("Invalid red component: {}", red);
            throw new IllegalArgumentException("Red must be between " + DroneSystem.ColorConstants.RGB_MIN + 
                " and " + DroneSystem.ColorConstants.RGB_MAX + ", got: " + red);
        }
        if (green < DroneSystem.ColorConstants.RGB_MIN || green > DroneSystem.ColorConstants.RGB_MAX) {
            log.error("Invalid green component: {}", green);
            throw new IllegalArgumentException("Green must be between " + DroneSystem.ColorConstants.RGB_MIN + 
                " and " + DroneSystem.ColorConstants.RGB_MAX + ", got: " + green);
        }
        if (blue < DroneSystem.ColorConstants.RGB_MIN || blue > DroneSystem.ColorConstants.RGB_MAX) {
            log.error("Invalid blue component: {}", blue);
            throw new IllegalArgumentException("Blue must be between " + DroneSystem.ColorConstants.RGB_MIN + 
                " and " + DroneSystem.ColorConstants.RGB_MAX + ", got: " + blue);
        }
        if (brightness < DroneSystem.ColorConstants.RGB_MIN || brightness > DroneSystem.ColorConstants.RGB_MAX) {
            log.error("Invalid brightness: {}", brightness);
            throw new IllegalArgumentException("Brightness must be between " + DroneSystem.ColorConstants.RGB_MIN + 
                " and " + DroneSystem.ColorConstants.RGB_MAX + ", got: " + brightness);
        }

        // Create color and send to controller
        Color color = Color.fromRGB(red, green, blue);
        LightDefault lightDefault = new LightDefault(
            com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyHold, 
            color, 
            (short) brightness
        );
        log.trace("Sending LED command to controller");
        drone.sendMessage(lightDefault, DeviceType.Base, DeviceType.Controller);
        
        // Small delay for command processing
        try {
            Thread.sleep(DroneSystem.CommunicationConstants.LED_COMMAND_DELAY_MS);
        } catch (InterruptedException e) {
            log.warn("Interrupted during LED command delay");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Turns off the controller LED.
     * 
     * <p>Turns off all LED lights on the controller, returning it to its default state.</p>
     * 
     * @apiNote Equivalent to Python's {@code drone.controller_LED_off()}
     * @since 1.0.0
     * @educational
     */
    public void controllerLEDOff() {
        log.debug("Turning off controller LED");
        Color color = Color.fromRGB(0, 0, 0);
        LightDefault lightDefault = new LightDefault(
            com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyHold, 
            color, 
            (short) 0
        );
        log.trace("Sending LED off command to controller");
        drone.sendMessage(lightDefault, DeviceType.Base, DeviceType.Controller);
        
        // Small delay for command processing
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            log.warn("Interrupted during LED off delay");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sets the controller LED to a specific color with animation mode.
     * 
     * <p>This method adds animation effects to the controller LED, enabling 
     * differentiated visual feedback between drone and controller states.
     * Perfect for team identification and multi-device programs.</p>
     * 
     * <h3>🎯 Educational Usage:</h3>
     * <ul>
     *   <li><strong>Team Coordination:</strong> Different controller animations for different teams</li>
     *   <li><strong>Status Indication:</strong> Controller shows ready/busy/error states</li>
     *   <li><strong>Debugging:</strong> Controller LED for program state, drone LED for flight state</li>
     *   <li><strong>User Interface:</strong> Visual feedback for user interactions</li>
     * </ul>
     * 
     * <h3>💡 Animation Modes:</h3>
     * <ul>
     *   <li>{@code "solid"} - Steady color (same as setControllerLED)</li>
     *   <li>{@code "dimming"} - Slowly brightens and dims</li>
     *   <li>{@code "fade_in"} - Gradually brightens from off</li>
     *   <li>{@code "fade_out"} - Gradually dims to off</li>
     *   <li>{@code "blink"} - Regular on/off blinking</li>
     *   <li>{@code "double_blink"} - Two quick blinks then pause</li>
     *   <li>{@code "rainbow"} - Cycles through colors (ignores RGB values)</li>
     * </ul>
     * 
     * @param red Red component (0-255)
     * @param green Green component (0-255)
     * @param blue Blue component (0-255)
     * @param mode Animation mode (use LEDMode constants or strings above)
     * @param speed Animation speed (1-10, where 10 is fastest)
     * 
     * @throws IllegalArgumentException if any parameter is out of range
     * @apiNote Equivalent to Python's {@code drone.set_controller_LED_mode(r, g, b, mode, speed)}
     * @since 1.0.0
     * @educational
     * @pythonEquivalent set_controller_LED_mode
     * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Drone-Function-Documentation#set_controller_led_mode
     */
    public void setControllerLEDMode(int red, int green, int blue, String mode, int speed) {
        log.debug("Setting controller LED mode: RGB({}, {}, {}) mode={} speed={}", red, green, blue, mode, speed);
        // Validate input parameters
        if (red < 0 || red > 255) {
            log.error("Invalid red component: {}", red);
            throw new IllegalArgumentException("Red must be between 0 and 255, got: " + red);
        }
        if (green < 0 || green > 255) {
            log.error("Invalid green component: {}", green);
            throw new IllegalArgumentException("Green must be between 0 and 255, got: " + green);
        }
        if (blue < 0 || blue > 255) {
            log.error("Invalid blue component: {}", blue);
            throw new IllegalArgumentException("Blue must be between 0 and 255, got: " + blue);
        }
        if (speed < 1 || speed > 10) {
            log.error("Invalid speed: {}", speed);
            throw new IllegalArgumentException("Speed must be between 1 and 10, got: " + speed);
        }
        if (mode == null) {
            log.error("LED mode cannot be null");
            throw new IllegalArgumentException("Mode cannot be null");
        }

        // Convert speed to interval (Python-compatible calculation)
        short interval;
        com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController lightMode;
        
        switch (mode.toLowerCase()) {
            case "solid":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyHold;
                interval = (short) 255; // Full brightness for solid
                break;
            case "dimming":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyDimming;
                interval = (short) ((11 - speed) * 5); // interval ranges [5,50]
                break;
            case "fade_in":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodySunrise;
                interval = (short) ((11 - speed) * 12); // interval ranges [12,120]
                break;
            case "fade_out":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodySunset;
                interval = (short) ((11 - speed) * 12); // interval ranges [12,120]
                break;
            case "blink":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyFlicker;
                interval = (short) ((11 - speed) * 100); // interval ranges [100,1000]
                break;
            case "double_blink":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyFlickerDouble;
                interval = (short) ((11 - speed) * 60); // interval ranges [60,600]
                break;
            case "rainbow":
                lightMode = com.otabi.jcodroneedu.protocol.lightcontroller.LightModesController.BodyRainbow;
                interval = (short) ((11 - speed) * 7); // interval ranges [7,70]
                break;
            default:
                log.error("Invalid LED mode: {}", mode);
                throw new IllegalArgumentException("Invalid LED mode: " + mode + 
                    ". Valid modes are: solid, dimming, fade_in, fade_out, blink, double_blink, rainbow");
        }

        // Create color and send to controller
        Color color = Color.fromRGB(red, green, blue);
        LightDefault lightDefault = new LightDefault(lightMode, color, interval);
        log.trace("Sending LED mode command to controller: mode={} interval={}", mode, interval);
        drone.sendMessage(lightDefault, DeviceType.Base, DeviceType.Controller);
        
        // Small delay for command processing
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            log.warn("Interrupted during LED mode command delay");
            Thread.currentThread().interrupt();
        }
    }
}
