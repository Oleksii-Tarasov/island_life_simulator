package ua.com.javarush.island_life_simulator.field;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;

public class GameField {
    public static Cell[][] islandField;

    public void createIsland() {
        islandField = new Cell[ISLAND_HEIGHT][ISLAND_WIDTH];
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                islandField[y][x] = new Cell(new ItemPosition(x, y));
            }
        }
    }
}
