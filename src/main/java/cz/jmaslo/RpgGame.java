package cz.jmaslo;

import cz.jmaslo.gameWindow.GameWindow;
import cz.jmaslo.gameWindow.Key;
import cz.jmaslo.gameWindow.Renderer;

import java.awt.*;

public class RpgGame extends GameWindow {

    private static final double SPEED = 500;
    private static final int SIZE = 20;

    double PlayerY = 100;
    double PlayerX = 100;

    private RpgGame() {super(800, 600, "Demo");}

    @Override
    public void render(Renderer r) {
        r.fillRect((int) PlayerX, (int) PlayerY, SIZE, SIZE, 0x80e0f0);
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
