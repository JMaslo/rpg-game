package cz.jmaslo;

import cz.jmaslo.gameWindow.Color;
import cz.jmaslo.gameWindow.GameWindow;
import cz.jmaslo.gameWindow.Image;
import cz.jmaslo.gameWindow.Key;
import cz.jmaslo.gameWindow.Renderer;
import cz.jmaslo.gameWindow.SpriteSheet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static cz.jmaslo.gameWindow.Input.isKeyPressed;

public class RpgGame extends GameWindow {

    private static final SpriteSheet heart = Image.load("files/heart.png", 2).getSpriteSheet(32);

    private int playerY = 5;
    private int playerX = 5;

    private static final int TILE_SIZE = 54;
    private static final int MAP_WIDTH = 11;
    private static final int MAP_HEIGHT = 11;



    private final int[][] map;

    String filename = "files/map.txt";


    private RpgGame() throws IOException {
        super(800, 600, "RPG Game");
        map = readMapFromFile(filename);
    }

    private int[][] readMapFromFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(line -> line.split(", "))
                .map(values -> Arrays.stream(values).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }

    @Override
    public void render(Renderer r) {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                if (map[y][x] == 0) {
                    r.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, Color.GREEN.getRGB());
                } else {
                    r.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, new Color(139, 69, 19).getRGB());
                }
            }
        }
        r.fillRect(playerX * TILE_SIZE, playerY * TILE_SIZE, TILE_SIZE, TILE_SIZE, Color.RED.getRGB());
        r.drawSprite(heart.getSprite(4), 600, 200);
        r.drawSprite(heart.getSprite(2), 632, 200);
    }



    @Override
    public void update(double delta) {

        int newX = playerX;
        int newY = playerY;

        if (isKeyPressed(Key.W)) {
            newY--;
        }
        if (isKeyPressed(Key.S)) {
            newY++;
        }
        if (isKeyPressed(Key.A)) {
            newX--;
        }
        if (isKeyPressed(Key.D)) {
            newX++;
        }

        if (map[newY][newX] == 0) {
            playerX = newX;
            playerY = newY;
        }
    }

    public static void main(String[] args) throws IOException {
        new RpgGame().run();
    }
}