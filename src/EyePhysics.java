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
 */
public class EyePhysics {

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
}
