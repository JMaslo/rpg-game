package cz.jmaslo.gameWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Immutable image data. To create an instance, use {@link Image#load(String, int)}. To draw the image or a part of it,
 * use {@link Renderer#drawImage(Image, int, int, int, int, int, int)}.
 */
public final class Image {
    final java.awt.Image image;

    Image(BufferedImage image, int scale) {
        this.image = image.getScaledInstance(image.getWidth() * scale, image.getHeight() * scale, BufferedImage.SCALE_DEFAULT);
    }

    /**
     * Loads an image from the specified file, scaling it by the specified factor. Any errors are rethrown as
     * {@link RuntimeException}.
     *
     * @param path path to the image file
     * @param scale scale factor for the image
     * @return loaded image
     * @see Renderer#drawImage(Image, int, int, int, int, int, int)
     */
    public static Image load(String path, int scale) {
        assert scale > 0 : "Scale must be positive";
        try {
            return new Image(ImageIO.read(new File(path)), scale);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load texture: " + path, e);
        }
    }

    /**
     * Loads an image from the specified file. Any errors are rethrown as {@link RuntimeException}.
     *
     * @param path path to the image file
     * @return loaded image
     * @see Renderer#drawImage(Image, int, int, int, int, int, int)
     */
    public static Image load(String path) {
        return load(path, 1);
    }

    /**
     * Returns the width of the image.
     *
     * @return image width in pixels
     */
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * Returns the height of the image.
     *
     * @return image height in pixels
     */
    public int getHeight() {
        return image.getHeight(null);
    }

    /**
     * Returns a sprite sheet for this image.
     *
     * @param tileWidth width of a single tile in pixels
     * @param tileHeight height of a single tile in pixels
     * @return sprite sheet
     */
    public SpriteSheet getSpriteSheet(int tileWidth, int tileHeight) {
        return new SpriteSheet(this, tileWidth, tileHeight);
    }

    /**
     * Returns a sprite sheet for this image with square tiles.
     *
     * @param tileSize width and height of a single tile in pixels
     * @return sprite sheet
     */
    public SpriteSheet getSpriteSheet(int tileSize) {
        return getSpriteSheet(tileSize, tileSize);
    }
}
