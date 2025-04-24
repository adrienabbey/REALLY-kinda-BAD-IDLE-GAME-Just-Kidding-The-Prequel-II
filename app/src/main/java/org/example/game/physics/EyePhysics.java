/*
 * Eye Physics Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

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
 * 
 * Link to original library: https://github.com/googlesamples/android-vision/blob/master/visionSamples/googly-eyes/app/src/main/java/com/google/android/gms/samples/vision/face/googlyeyes/EyePhysics.java
 * EyePhysics.java was created with using teh above library class as a template.
 */
import java.awt.*;

/**
 * This class provides methods to set eye properties, draw eyes on panels and  calculate and update the position of the iris within an eye based on cursor direction. Can update iris position both in the direction of and the direction opposite to the cursor position.  
 * 
 * 
 * TODO: Add the following capabilities: 
 * Simulate the physics of motion for an iris which moves within a googly eye.  The iris moves independently of the motion of the face/eye, and moves according to the following forces:<p>
 *
 * <ol>
 * <li>Gravity - downward acceleration.</li>
 *
 * <li>Friction - deceleration; opposing motion</li>
 *
 * <li>Bounce - acceleration in the opposite direction of motion when the iris hits the side of the
 * eye (e.g., due to a jerking motion which suddenly moves the face in frame).  Note that this is
 * the only way to get the iris to move horizontally, since gravity only accelerates downward.</li>
 * </ol>
 */
public class EyePhysics {
    private Point eyePosition;
    private int eyeRadius;
    private Point irisPosition;
    private int irisRadius;

 //======================================================================
 // Methods
 //======================================================================
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

    // Same as calculateIrisPosition method, except it calculates the inverse posiition of the Iris relative to the direction of the cursor.
    public Point calculateOppositeIrisPosition(Point cursorPosition, Point eyePosition, int eyeRadius, int irisRadius) {
        // Calculate the angle between the eye center and the opposite cursor position by subtracting the eyeposition by the cursorPosition, rather than the cursorPosition by the eyeposition. 
        double angle = Math.atan2(eyePosition.y - cursorPosition.y, eyePosition.x - cursorPosition.x);
        
        // Calculate the coordinates of the iris based on the angle and radii
        int x = (int) (eyePosition.x + (eyeRadius - irisRadius) * Math.cos(angle));
        int y = (int) (eyePosition.y + (eyeRadius - irisRadius) * Math.sin(angle));
        
        return new Point(x, y);
    }

    /**
     * Updates the iris position based on the mouse position 
     *
     * @param mousePosition The position of the cursor.
     * @param eye           The eye object that houses the eye's instance variables and methods.
     */
    public void updateIrisPosition(Point mousePosition, EyePhysics eye) {
        if (mousePosition != null) {
            // update eye's iris position based on eye's properties.
            eye.setIrisPosition(eye.calculateIrisPosition(mousePosition, eye.getEyePosition(), eye.getEyeRadius(), eye.getIrisRadius()));
            }
        }

    // Same as updateIrisPosition method, except it updates the iris position based on the inverse direction of the mouse position 
    public void updateOppositeIrisPosition(Point mousePosition, EyePhysics eye) {
        if (mousePosition != null) {
            // update eye's iris position based on eye's properties.
            eye.setIrisPosition(eye.calculateOppositeIrisPosition(mousePosition, eye.getEyePosition(), eye.getEyeRadius(), eye.getIrisRadius()));
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
