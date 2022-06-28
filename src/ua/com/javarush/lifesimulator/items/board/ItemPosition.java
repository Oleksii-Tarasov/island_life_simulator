package ua.com.javarush.lifesimulator.items.board;

import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_BOARD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_BOARD_WIDTH;

public class ItemPosition {
    private int x;
    private int y;

    public ItemPosition() {
        this.x = (int) (Math.random() * (GAME_BOARD_WIDTH));
        this.y = (int) (Math.random() * (GAME_BOARD_HEIGHT));
    }

    public ItemPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
