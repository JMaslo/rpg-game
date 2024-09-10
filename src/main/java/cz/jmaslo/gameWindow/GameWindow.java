package cz.jmaslo.gameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameWindow {
    private final int width;
    private final int height;
    private final String title;
    private JFrame mainFrame;
    private long lastFrameTime;
    private double fps;
    private boolean showFps;
    private final boolean[] lastKeyState = new boolean[Key.values().length];
    private final boolean[] keyState = new boolean[Key.values().length];

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

    /**
     * Creates a new game window. The window is not visible until the {@link #run()} method is called.
     * @param width canvas width in pixels
     * @param height canvas height in pixels
     * @param title window title
     */
    public GameWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    /**
     * Makes the window visible and starts the game loop.
     * This method should be called from the {@code main} method. It returns immediately, the game loop runs in
     * a separate thread.
     */
    public final void run() {
        SwingUtilities.invokeLater(this::start);
    }

    /**
     * Called every frame to update the game state.
     * @param delta time in seconds since the last frame
     */
    public void update(double delta) {
    }

    /**
     * Called every frame to render the game.
     * @param r rendering context
     */
    public void render(Renderer r) {
    }

    /**
     * Returns whether the current frame rate is displayed in the window.
     * @return {@code true} if the frame rate is displayed, {@code false} otherwise
     */
    public boolean getShowFps() {
        return showFps;
    }

    /**
     * Sets whether the current frame rate should be displayed in the window.
     * @param showFps {@code true} to display the frame rate, {@code false} otherwise
     */
    public void setShowFps(boolean showFps) {
        this.showFps = showFps;
    }

    /**
     * Returns the canvas width.
     * @return canvas width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the canvas height.
     * @return canvas height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns whether a key is currently being held down.
     * @param key key to check
     * @return {@code true} if the key is currently being held down, {@code false} otherwise
     */
    public boolean isKeyHeld(Key key) {
        return keyState[key.ordinal()];
    }

    /**
     * Returns whether a key was pressed in the current frame.
     * @param key key to check
     * @return {@code true} if the key was pressed in the current frame, {@code false} otherwise
     */
    public boolean isKeyPressed(Key key) {
        return keyState[key.ordinal()] && !lastKeyState[key.ordinal()];
    }

    /**
     * Rendering context for drawing shapes.
     */
    public static final class Renderer {
        private final Graphics g;

        private Renderer(Graphics g) {
            this.g = g;
        }

        /**
         * Draws a filled rectangle.
         * @param x x-coordinate of the top-left corner
         * @param y y-coordinate of the top-left corner
         * @param width rectangle width
         * @param height rectangle height
         * @param color RGB color as an integer (0xRRGGBB), similar to CSS colors
         */
        public void fillRect(int x, int y, int width, int height, int color) {
            g.setColor(new Color(color));
            g.fillRect(x, y, width, height);
        }
    }

    public enum Key {
        W, S, A, D, F
    }

    private void start() {
        mainFrame = new JFrame(title);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().add(new Canvas());
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setBackground(Color.BLACK);
        mainFrame.addKeyListener(new KeyboardHandler());
        lastFrameTime = System.nanoTime();
        new Timer(15, e -> {
            long time = System.nanoTime();
            double delta = (time - lastFrameTime) / 1_000_000_000.0;
            lastFrameTime = time;
            fps = 1 / delta;
            update(delta);
            mainFrame.repaint();
            System.arraycopy(keyState, 0, lastKeyState, 0, keyState.length);
        }).start();
    }

    private final class Canvas extends JPanel {
        private Canvas() {
            setDoubleBuffered(true);
            setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(new Renderer(g));
            if (showFps) {
                g.setColor(Color.WHITE);
                g.drawString("FPS: %.2f".formatted(fps), 0, 10);
            }


            for (int y = 0;y < MAP_HEIGHT; y++) {
                for (int x = 0; x < MAP_WIDTH; x++) {

                    if (map[y][x] == 0) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(new Color(139, 69, 19));
                    }
//                    g.setColor(Color.GREEN);
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private final class KeyboardHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
            Key key = mapKeyCode(e.getKeyCode());
            if (key != null) {
                keyState[key.ordinal()] = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Key key = mapKeyCode(e.getKeyCode());
            if (key != null) {
                keyState[key.ordinal()] = false;
            }
        }

        private Key mapKeyCode(int keyCode) {
            return switch (keyCode) {
                case KeyEvent.VK_W -> Key.W;
                case KeyEvent.VK_S -> Key.S;
                case KeyEvent.VK_A -> Key.A;
                case KeyEvent.VK_D -> Key.D;
                case KeyEvent.VK_F -> Key.F;
                default -> null;
            };
        }
    }

}
