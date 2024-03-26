import java.awt.*;

/**
 * This class provides methods to calculate the position of the iris within an eye.
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
