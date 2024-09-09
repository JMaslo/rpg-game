package cz.jmaslo.gameWindow;

import java.awt.image.BufferedImage;

/**
 * Immutable image data. To create an instance, use {@link GameWindow#loadTexture(String)}. To draw the image or a part
 * of it, use {@link Renderer#drawImage(Image, int, int, int, int, int, int)}.
 */
public final class Image {
    final BufferedImage image;

    Image(BufferedImage image) {
        this.image = image;
    }

    /**
     * Returns the width of the image.
     *
     * @return image width in pixels
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Returns the height of the image.
     *
     * @return image height in pixels
     */
    public int getHeight() {
        return image.getHeight();
    }
}
