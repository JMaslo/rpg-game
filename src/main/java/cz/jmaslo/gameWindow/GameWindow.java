package cz.jmaslo.gameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


/**
 * Game window with a simple game loop. To create a game, extend this class and implement the {@link #update(double)}
 * and {@link #render(Renderer)} methods. The game loop calls these methods every frame. The window is not visible
 * until the {@link #run()} method is called.
 */
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
     * Loads an image from the specified file.
     * Any errors are rethrown as {@link RuntimeException}.
     * @param path path to the image file
     * @return loaded image
     * @see Renderer#drawImage(Image, int, int, int, int, int, int)
     */
    public Image loadTexture(String path) {
        try {
            return new Image(ImageIO.read(new File(path)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load texture: " + path, e);
        }
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
            render(new Renderer(g));
            if (showFps) {
                g.setColor(Color.WHITE);
                g.drawString("FPS: %.2f".formatted(fps), 0, 10);
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
