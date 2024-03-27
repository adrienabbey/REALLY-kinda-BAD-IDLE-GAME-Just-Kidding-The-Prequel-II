/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.awt.*;

/**
 * This class provides methods to calculate the position of the iris within an eye.
 * TODO: Add bounce/wiggle physics to googley eyes
 * TODO: make class easier to use
 */
public class EyePhysics {
    private Point eyePosition;
    private int eyeRadius;
    private Point irisPosition;
    private int irisRadius;

    /**
     * Calculates the position of the iris based on the cursor position and eye parameters.
     *
     * @param cursorPosition The position of the cursor.
     * @param eyePosition    The position of the center of the eye.
     * @param eyeRadius      The radius of the eye.
     * @param irisRadius     The radius of the iris.
     * @return The calculated position of the iris.
     */
    public Point calculateIrisPosition(Point cursorPosition, Point eyePosition, int eyeRadius, int irisRadius) {
        // Calculate the angle between the eye center and the cursor position
        double angle = Math.atan2(cursorPosition.y - eyePosition.y, cursorPosition.x - eyePosition.x);
        
        // Calculate the coordinates of the iris based on the angle and radii
        int x = (int) (eyePosition.x + (eyeRadius - irisRadius) * Math.cos(angle));
        int y = (int) (eyePosition.y + (eyeRadius - irisRadius) * Math.sin(angle));
        
        return new Point(x, y);
    }

    // Method calculates the iris position based on the mouse position 
    public void updateIrisPosition(Point mousePosition, EyePhysics eye) {
        if (mousePosition != null) {
            // update eye's iris position based on eye's properties.
            eye.setIrisPosition(eye.calculateIrisPosition(mousePosition, eye.getEyePosition(), eye.getEyeRadius(), eye.getIrisRadius()));
            }
        }

    // Method draws eye 
    public void drawEye(Graphics g, EyePhysics eye) {
        // Draw the eye
        g.setColor(Color.WHITE);
        g.fillOval(eye.getEyePosition().x - eye.getEyeRadius(), eye.getEyePosition().y - eye.getEyeRadius(), 2 * eye.getEyeRadius(), 2 * eye.getEyeRadius());

        // Draw the iris
        g.setColor(Color.BLACK);
        g.fillOval(irisPosition.x - irisRadius, irisPosition.y - irisRadius, 2 * irisRadius, 2 * irisRadius);
    }

    // setters for individual eye properties
    public void setEyePosition(Point newEyePosition) {
        eyePosition = newEyePosition; 
    }

    public void setEyeRadius(int newEyeRadius) {
        eyeRadius = newEyeRadius; 
    }

    public void setIrisPosition(Point newIrisPosition) {
        irisPosition = newIrisPosition; 
    }

    public void setIrisRadius(int newIrisRadius) {
        irisRadius = newIrisRadius; 
    }


    // getters for individual eye properties
    public Point getEyePosition() {
        return eyePosition; 
    }

    public int getEyeRadius() {
        return eyeRadius; 
    }

    public Point getIrisPosition() {
        return irisPosition; 
    }

    public int getIrisRadius() {
        return irisRadius; 
    }
}
