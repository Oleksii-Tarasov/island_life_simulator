package ua.com.javarush.lifesimulator.field;

import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class ItemPosition {
    private int x;
    private int y;

    public ItemPosition() {
        this.x = (int) (Math.random() * (GAME_FIELD_WIDTH));
        this.y = (int) (Math.random() * (GAME_FIELD_HEIGHT));
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
