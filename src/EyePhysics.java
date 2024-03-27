import java.awt.*;

/**
 * This class provides methods to calculate the position of the iris within an eye.
 * TODO: Add bounce/wiggle physics to googley eyes
 * TODO: make class easier to use
 */
public class EyePhysics {
    // private EyePhysics eyePhysics;
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

    // // sets all of eye's properties
    // public void setEyeProperties(Point ePos, int erad, Point iPos, int iRad) {
    //     Point eyePosition = ePos;
    //     int eyeRadius = erad;
    //     Point irisPosition = iPos;
    //     int irisRadius = iRad;
    // }

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
