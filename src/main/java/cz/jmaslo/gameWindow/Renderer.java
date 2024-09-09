package cz.jmaslo.gameWindow;

import java.awt.*;

/**
 * Rendering context for drawing shapes.
 */
public final class Renderer {
    private final Graphics g;

    Renderer(Graphics g) {
        this.g = g;
    }

    /**
     * Draws a filled rectangle.
     *
     * @param x x-coordinate of the top-left corner
     * @param y y-coordinate of the top-left corner
     * @param width rectangle width
     * @param height rectangle height
     * @param color RGB color as an integer (0xRRGGBB), similar to CSS colors
     */
    public void fillRect(int x, int y, int width, int height, int color) {
        g.setColor(new Color(color));
        g.fillRect(x, y, width, height);
    }

    /**
     * Draws an image.
     *
     * @param image image to draw
     * @param dstX x-coordinate of the top-left corner of the destination rectangle on the canvas
     * @param dstY y-coordinate of the top-left corner of the destination rectangle on the canvas
     */
    public void drawImage(Image image, int dstX, int dstY) {
        g.drawImage(image.image, dstX, dstY, null);
    }

    /**
     * Draws an image or a part of it.
     *
     * @param image image to draw
     * @param dstX x-coordinate of the top-left corner of the destination rectangle on the canvas
     * @param dstY y-coordinate of the top-left corner of the destination rectangle on the canvas
     * @param srcX x-coordinate of the top-left corner of the source rectangle in the image
     * @param srcY y-coordinate of the top-left corner of the source rectangle in the image
     * @param width width of the rectangle to draw
     * @param height height of the rectangle to draw
     */
    public void drawImage(Image image, int dstX, int dstY, int srcX, int srcY, int width, int height) {
        g.drawImage(image.image, dstX, dstY, dstX + width, dstY + height, srcX, srcY, srcX + width, srcY + height, null);
    }
}
