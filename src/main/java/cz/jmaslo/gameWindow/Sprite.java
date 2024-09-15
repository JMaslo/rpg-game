package cz.jmaslo.gameWindow;

/**
 * Immutable sprite data. To create an instance, use {@link SpriteSheet#getSprite(int)} or
 * {@link SpriteSheet#getSprite(int, int)}. To draw the sprite, use {@link Renderer#drawSprite(Sprite, int, int)}.
 */
public final class Sprite {
    final Image image;
    final int x;
    final int y;
    final int width;
    final int height;

    Sprite(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
