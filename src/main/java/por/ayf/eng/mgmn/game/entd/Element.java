package por.ayf.eng.mgmn.game.entd;

import java.awt.image.BufferedImage;

/**
 * Class that define a element of the game.
 *
 * @author: Ángel Yagüe Flor.
 * @version: 1.0 - Stable.
 * @version: 1.1 - Refactor the project.
 */

public abstract class Element {
    protected int xPosition;
    protected int yPosition;
    protected int xSpeed;
    protected int ySpeed;
    protected int position;
    protected BufferedImage currentImage;

    /**
     * Method that return the X position of the element.
     *
     * @return X position of the element.
     */

    public int getPos_X() {
        return xPosition;
    }

    /**
     * Method that set a new X position to the element.
     *
     * @param xPosition: New X position of the element.
     */

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Method that return the Y position of the element.
     *
     * @return Y position of the element.
     */

    public int getYPosition() {
        return yPosition;
    }

    /**
     * Method that set a new Y position to the element.
     *
     * @param yPosition: New Y position of the element.
     */

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Method that return the speed by the X axis of the element.
     *
     * @return Speed by the X axis of the element.
     */

    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * Method that set a new speed on the Y axis of the element.
     *
     * @param xSpeed: New speed on the X axis of the element.
     */

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * Method that return the speed by the Y axis of the element.
     *
     * @return Speed by the Y axis of the element.
     */

    public int getYSpeed() {
        return ySpeed;
    }

    /**
     * Method that set a new speed on the Y axis of the element.
     *
     * @param ySpeed: New speed on the Y axis of the element.
     */

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * Method that return the current image of the element.
     *
     * @return Current image.
     */

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    /**
     * Method that set a new current image.
     *
     * @param currentImage: New current image.
     */

    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }

    /**
     * Method that return the position of the character.
     *
     * @return 0 if it looks to the left, 1 if it looks to the right.
     */

    public int getPosition() {
        return position;
    }

    /**
     * Method that set a new position to the character.
     *
     * @param position: Number that indicates if it looks to the left or right.
     */

    public void setPosition(int position) {
        this.position = position;
    }
}
