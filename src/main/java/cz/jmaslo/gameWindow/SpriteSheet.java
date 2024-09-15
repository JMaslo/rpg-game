package cz.jmaslo.gameWindow;

/**
 * Immutable sprite sheet data. To create an instance, use {@link Image#load(String, int)}. To get a sprite from the
 * sheet, use {@link #getSprite(int)} or {@link #getSprite(int, int)}.
 */
public final class SpriteSheet {

    private final Image image;
    private final int spriteWidth;
    private final int spriteHeight;

    SpriteSheet(Image image, int spriteWidth, int spriteHeight) {
        this.image = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public int getCols() {
        return image.getWidth() / spriteWidth;
    }

    public int getRows() {
        return image.getHeight() / spriteHeight;
    }

    public Sprite getSprite(int col, int row) {
        return new Sprite(image, col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight);
    }

    public Sprite getSprite(int id) {
        return getSprite(id % getCols(), id / getCols());
    }

}
