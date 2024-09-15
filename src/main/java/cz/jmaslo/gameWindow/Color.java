package cz.jmaslo.gameWindow;

public final class Color {

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color MAGENTA = new Color(255, 0, 255);

    private final int rgb;

    public Color(int r, int g, int b) {
        assert r >= 0 && r < 256;
        assert g >= 0 && g < 256;
        assert b >= 0 && b < 256;
        this.rgb = (r << 16) | (g << 8) | b;
    }

    public int getRGB() {
        return rgb;
    }
}
