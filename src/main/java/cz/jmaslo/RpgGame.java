package cz.jmaslo;

import cz.jmaslo.gameWindow.GameWindow;

public class RpgGame extends GameWindow {

    private static final double SPEED = 230;
    private static final int SIZE = 20;
    private static double x = 100;
    private static double y = 100;


    private RpgGame() {
        super(800, 600, "Demo");
        setShowFps(true);
    }

    @Override
    public void render(Renderer r) {
        r.fillRect((int) x, (int) y, SIZE, SIZE, 0x80e0f0);
    }

    @Override
    public void update(double delta) {
        if (isKeyPressed(Key.F)) {
            setShowFps(!getShowFps());
        }
        if (isKeyHeld(Key.A)) {
            x = x - SPEED * delta;
        }
        if (isKeyHeld(Key.D)) {
            x = x + SPEED * delta;
        }
        x = Math.max(0, x);
        x = Math.min(getWidth() - SIZE, x);
    }
    public static void main(String[] args) {

        new RpgGame().run();
    }
}
