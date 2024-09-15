package cz.jmaslo.gameWindow;

/**
 * Provides access to the current state of the user input.
 */
public final class Input {
    private static final boolean[] lastKeyState = new boolean[Key.values().length];
    private static final boolean[] keyState = new boolean[Key.values().length];

    private Input() {
    }

    /**
     * Returns whether a key is currently being held down.
     *
     * @param key key to check
     * @return {@code true} if the key is currently being held down, {@code false} otherwise
     */
    public static boolean isKeyHeld(Key key) {
        return keyState[key.ordinal()];
    }

    /**
     * Returns whether a key was pressed in the current frame.
     *
     * @param key key to check
     * @return {@code true} if the key was pressed in the current frame, {@code false} otherwise
     */
    public static boolean isKeyPressed(Key key) {
        return keyState[key.ordinal()] && !lastKeyState[key.ordinal()];
    }

    static void setKeyState(Key key, boolean pressed) {
        keyState[key.ordinal()] = pressed;
    }

    static void update() {
        System.arraycopy(keyState, 0, lastKeyState, 0, keyState.length);
    }
}
