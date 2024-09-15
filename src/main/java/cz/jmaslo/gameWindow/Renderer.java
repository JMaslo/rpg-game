package cz.jmaslo.gameWindow;

import java.awt.*;

/**
 * Rendering context for drawing shapes.
 */
public final class Renderer {
    private final Graphics g;
    private final int x0;
    private final int y0;

    Renderer(Graphics g) {
        this(g, 0, 0);
    }

    Renderer(Graphics g, int x0, int y0) {
        this.g = g;
        this.x0 = x0;
        this.y0 = y0;
    }

    /**
     * Translates the rendering context by the specified amount.
     *
     * @param x x-coordinate translation
     * @param y y-coordinate translation
     * @return new rendering context
     */
    public Renderer translate(int x, int y) {
        return new Renderer(g, x0 + x, y0 + y);
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
        g.setColor(new java.awt.Color(color));
        g.fillRect(x + x0, y + y0, width, height);
    }

    /**
     * Draws an image.
     *
     * @param image image to draw
     * @param dstX x-coordinate of the top-left corner of the destination rectangle on the canvas
     * @param dstY y-coordinate of the top-left corner of the destination rectangle on the canvas
     */
    public void drawImage(Image image, int dstX, int dstY) {
        g.drawImage(image.image, dstX + x0, dstY + y0, null);
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
        g.drawImage(image.image, dstX + x0, dstY + y0, dstX + width + x0, dstY + height + y0, srcX, srcY, srcX + width, srcY + height, null);
    }

    /**
     * Draws a sprite.
     *
     * @param sprite sprite to draw
     * @param x x-coordinate of the top-left corner of the destination rectangle on the canvas
     * @param y y-coordinate of the top-left corner of the destination rectangle on the canvas
     */
    public void drawSprite(Sprite sprite, int x, int y) {
        drawImage(sprite.image, x, y, sprite.x, sprite.y, sprite.width, sprite.height);
    }
}
