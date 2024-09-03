package por.ayf.eng.mgmn.game.map;

import java.awt.image.BufferedImage;

/**
 * Class that define the current map of the game.
 *
 * @author: Ángel Yagüe Flor.
 * @version: 1.0 - Stable.
 * @version: 1.1 - Refactor the project.
 */

public class Map {
    private int xPositionCamera;
    private int yPositionCamera;
    private BufferedImage map;
    private BufferedImage submap;

    public Map(int xPositionCamera, int yPositionCamera) {
        this.xPositionCamera = xPositionCamera;
        this.yPositionCamera = yPositionCamera;
    }

    /**
     * Method that return the X position of the camera.
     *
     * @return Position X of the camera.
     */

    public int getXPositionCamera() {
        return xPositionCamera;
    }

    /**
     * Method that set a new X position to the camera.
     *
     * @param xPositionCamera: New X position to the camera.
     */

    public void setXPositionCamera(int xPositionCamera) {
        this.xPositionCamera = xPositionCamera;
    }

    /**
     * Method that return the Y position of the camera.
     *
     * @return Position Y of the camera.
     */

    public int getYPositionCamera() {
        return yPositionCamera;
    }

    /**
     * Method that set a new Y position to the camera.
     *
     * @param yPositionCamera: New Y position to the camera.
     */

    public void setYPositionCamera(int yPositionCamera) {
        this.yPositionCamera = yPositionCamera;
    }

    /**
     * Method that return the current image of the map.
     *
     * @return Current image of the map.
     */

    public BufferedImage getMap() {
        return map;
    }

    /**
     * Method that set a new image to the map.
     *
     * @param map: New image of the map.
     */

    public void setMap(BufferedImage map) {
        this.map = map;
    }

    /**
     * Method that return the current image of the submap.
     *
     * @return Current image of the submap.
     */

    public BufferedImage getSubmap() {
        return submap;
    }

    /**
     * Method that set a new image to the submap.
     *
     * @param submap: New image of the submap.
     */

    public void setSubmap(BufferedImage submap) {
        this.submap = submap;
    }
}
