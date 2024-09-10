package cz.jmaslo;

import cz.jmaslo.gameWindow.GameWindow;
import cz.jmaslo.gameWindow.Key;
import cz.jmaslo.gameWindow.Renderer;

import java.awt.*;

public class RpgGame extends GameWindow {

    private static final double SPEED = 750;
    private static final int SIZE = 30;

    double PlayerY = 100;
    double PlayerX = 100;

    private static final int TILE_SIZE = 54;
    private static final int MAP_WIDTH = 11;
    private static final int MAP_HEIGHT = 11;

    private final int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private RpgGame() {super(800, 600, "RPG Game");}

    @Override
    public void render(Renderer r) {

        for (int y = 0;y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                if (map[y][x] == 0) {
                    r.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, Color.GREEN.getRGB());
                } else {
                    r.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, new Color(139, 69, 19).getRGB());
                }
            }
        }
        r.fillRect((int) PlayerX, (int) PlayerY, SIZE, SIZE, Color.RED.getRGB());
    }

    @Override
    public void update(double delta) {

        if (isKeyPressed(Key.W)) {PlayerY = PlayerY - SPEED * delta;}

        if (isKeyPressed(Key.S)) {PlayerY = PlayerY + SPEED * delta;}

        if (isKeyPressed(Key.A)) {PlayerX = PlayerX - SPEED * delta;}

        if (isKeyPressed(Key.D)) {PlayerX = PlayerX + SPEED * delta;}
    }
    public static void main(String[] args) {
        new RpgGame().run();
    }
}
