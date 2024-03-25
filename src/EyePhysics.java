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

import java.awt.geom.Point2D;

/**
 * Simulates the physics of motion for an iris which moves within an eye. The iris moves
 * independently of the motion of the eye/face, and moves according to the following forces:
 * <p>
 * <ol>
 * <li>Gravity - downward acceleration.</li>
 *
 * <li>Friction - deceleration; opposing motion</li>
 *
 * <li>Bounce - acceleration in the opposite direction of motion when the iris hits the side of the
 * eye (e.g., due to a jerking motion which suddenly moves the face in frame). Note that this is
 * the only way to get the iris to move horizontally, since gravity only accelerates downward.</li>
 * </ol>
 * <p>
 * The simulation is configured to run at a universal real time rate, regardless of the performance
 * of the device in which it is run and how frequently updates are received.
 */
public class EyePhysics {
    // The time period in milliseconds for the simulation.
    private final long TIME_PERIOD_MS = 1000;

    // The friction and gravity values below are set relative to a specific time period. This
    // allows the simulation to run at the same rate, regardless of whether it is running on a slow
    // or fast device or if there are temporary performance variations on the device.
    private final float FRICTION = 2.2f;
    private final float GRAVITY = 40.0f;

    // The multiplier for the bounce force.
    private final float BOUNCE_MULTIPLIER = 20.0f;

    // A tolerance value to consider a value as zero.
    private final float ZERO_TOLERANCE = 0.001f;

    // The last update time in milliseconds.
    private long lastUpdateTimeMs = System.currentTimeMillis();

    // The position and radius of the eye.
    private Point2D.Float eyePosition;
    private float eyeRadius;

    // The position and radius of the iris.
    private Point2D.Float irisPosition;
    private float irisRadius;

    // The velocity components of the iris.
    private float vx = 0.0f;
    private float vy = 0.0f;

    // The count of consecutive bounces.
    private int consecutiveBounces = 0;

    //========================================================================================
    // Methods
    //========================================================================================

    /**
     * Generate the next position of the iris based on simulated velocity, eye boundaries,   * gravity, friction, and bounce momentum.
     *
     * @param eyePosition The position of the eye.
     * @param eyeRadius   The radius of the eye.
     * @param irisRadius  The radius of the iris.
     * @return The next position of the iris.
     */
    public Point2D.Float nextIrisPosition(Point2D.Float eyePosition, float eyeRadius, float irisRadius) {
        this.eyePosition = eyePosition;
        this.eyeRadius = eyeRadius;
        if (this.irisPosition == null) {
            this.irisPosition = eyePosition;
        }
        this.irisRadius = irisRadius;

        long nowMs = System.currentTimeMillis();
        long elapsedTimeMs = nowMs - lastUpdateTimeMs;
        float simulationRate = (float) elapsedTimeMs / TIME_PERIOD_MS;
        lastUpdateTimeMs = nowMs;

        if (!isStopped()) {
            vy += GRAVITY * simulationRate;
        }

        vx = applyFriction(vx, simulationRate);
        vy = applyFriction(vy, simulationRate);

        float x = (float) (irisPosition.x + (vx * irisRadius * simulationRate));
        float y = (float) (irisPosition.y + (vy * irisRadius * simulationRate));
        irisPosition = new Point2D.Float(x, y);

        makeIrisInBounds(simulationRate);

        return irisPosition;
    }

    /**
     * Apply friction to the velocity component.
     *
     * @param velocity       The velocity component.
     * @param simulationRate The simulation rate.
     * @return The updated velocity component after applying friction.
     */
    private float applyFriction(float velocity, float simulationRate) {
        if (isZero(velocity)) {
            velocity = 0.0f;
        } else if (velocity > 0) {
            velocity = Math.max(0.0f, velocity - (FRICTION * simulationRate));
        } else {
            velocity = Math.min(0.0f, velocity + (FRICTION * simulationRate));
        }
        return velocity;
    }

    /**
     * Correct the iris position to be in-bounds within the eye, if it is now out of bounds. Being
     * out of bounds could have been due to a sudden movement of the head and/or camera, or the
     * result of just bouncing/rolling around.
     *
     * @param simulationRate The simulation rate.
     */
    private void makeIrisInBounds(float simulationRate) {
        float irisOffsetX = (float) (irisPosition.x - eyePosition.x);
        float irisOffsetY = (float) (irisPosition.y - eyePosition.y);

        float maxDistance = eyeRadius - irisRadius;
        float distance = (float) Math.sqrt(Math.pow(irisOffsetX, 2) + Math.pow(irisOffsetY, 2));
        if (distance <= maxDistance) {
            consecutiveBounces = 0;
            return;
        }

        consecutiveBounces++;

        float ratio = maxDistance / distance;
        float x = (float) (eyePosition.x + (ratio * irisOffsetX));
        float y = (float) (eyePosition.y + (ratio * irisOffsetY));

        float dx = x - irisPosition.x;
        vx = applyBounce(vx, dx, simulationRate) / consecutiveBounces;

        float dy = y - irisPosition.y;
        vy = applyBounce(vy, dy, simulationRate) / consecutiveBounces;

        irisPosition = new Point2D.Float(x, y);
    }

    /**
     * Update velocity in response to bouncing off the sides of the eye (i.e., when iris hits the
     * bottom or the eye moves quickly). This is the only way to gain horizontal velocity, since
     * there is no other horizontal force.
     *
     * @param velocity        The velocity component.
     * @param distOutOfBounds The distance out of bounds.
     * @param simulationRate  The simulation rate.
     * @return The updated velocity after applying bounce.
     */
    private float applyBounce(float velocity, float distOutOfBounds, float simulationRate) {
        if (isZero(distOutOfBounds)) {
            return velocity;
        }

        velocity *= -1;

        float bounce = BOUNCE_MULTIPLIER * Math.abs(distOutOfBounds / irisRadius);
        if (velocity > 0) {
            velocity += bounce * simulationRate;
        } else {
            velocity -= bounce * simulationRate;
        }

        return velocity;
    }

    /**
    * The iris is stopped if it is at the bottom of the eye and its velocity is zero.
    */
    private boolean isStopped() {
        if (eyePosition.y >= irisPosition.y) {
            return false;
        }

        float irisOffsetY = (float) (irisPosition.y - eyePosition.y);
        float maxDistance = eyeRadius - irisRadius;
        if (irisOffsetY < maxDistance) {
            return false;
        }

        return (isZero(vx) && isZero(vy));
    }

    /**
    * Allow for a small tolerance in floating point values in considering whether a value is zero.
    */
    private boolean isZero(float num) {
        return ((num < ZERO_TOLERANCE) && (num > -1 * ZERO_TOLERANCE));
    }
}
