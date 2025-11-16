/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

import com.otabi.jcodroneedu.Drone;

public class TestEducationalException {
    @SuppressWarnings("deprecation") // Testing legacy method behavior
    public static void main(String[] args) {
        try (Drone drone = new Drone()) {
            drone.square(60, 2, 1);  // This should throw the educational exception
        } catch (Exception e) {
            System.out.println("Exception caught:");
            System.out.println(e.getMessage());
        }
    }
}
