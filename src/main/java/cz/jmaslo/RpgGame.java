package cz.jmaslo;

import cz.jmaslo.gameWindow.GameWindow;
import cz.jmaslo.gameWindow.Key;
import cz.jmaslo.gameWindow.Renderer;

import java.awt.*;

public class RpgGame extends GameWindow {

    private static final double SPEED = 750;
    private static final int SIZE = 54;

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

    private RpgGame() {
        super(800, 600, "RPG Game");
    }

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

        double moveX = 0;
        double moveY = 0;

        if (isKeyPressed(Key.W)) {
            moveY -= TILE_SIZE;
        }
        if (isKeyPressed(Key.S)) {
            moveY += TILE_SIZE;
        }
        if (isKeyPressed(Key.A)) {
            moveX -= TILE_SIZE;
        }
        if (isKeyPressed(Key.D)) {
            moveX += TILE_SIZE;
        }

        double newPlayerX = PlayerX + moveX;
        double newPlayerY = PlayerY + moveY;

        PlayerX = newPlayerX;
        PlayerY = newPlayerY;

        playerMove();
//        checkWallCollision();
    }

    public void checkWallCollision() {

        // Check if there is a collision
        // When player would like to go throw the wall, it will send him back to his last position

        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {

            }
        }


    }

    public void playerMove() {

        Rectangle playerBox = new Rectangle((int) PlayerX, (int) PlayerY, TILE_SIZE, TILE_SIZE);

        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                    Rectangle wallBox = new Rectangle(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (playerBox.intersects(wallBox)) {
                        if (playerBox.x < wallBox.x) {
                            PlayerX = wallBox.x - TILE_SIZE;
                        }
                        if (playerBox.x + TILE_SIZE > wallBox.x + TILE_SIZE) {
                            PlayerX = wallBox.x + TILE_SIZE;
                        }
                        if (playerBox.y < wallBox.y) {
                            PlayerY = wallBox.y - TILE_SIZE;
                        }
                        if (playerBox.y + TILE_SIZE > wallBox.y + TILE_SIZE) {
                            PlayerY = wallBox.y + TILE_SIZE;
                        }
                    }
            }
        }

    }
    public static void main(String[] args) {
        new RpgGame().run();
    }
}