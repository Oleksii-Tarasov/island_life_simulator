package ua.com.javarush.island_life_simulator.game_field;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;

public class ItemPosition {
    private int x;
    private int y;

    public ItemPosition() {
        this.x = (int) (Math.random() * (ISLAND_WIDTH));
        this.y = (int) (Math.random() * (ISLAND_HEIGHT));
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
