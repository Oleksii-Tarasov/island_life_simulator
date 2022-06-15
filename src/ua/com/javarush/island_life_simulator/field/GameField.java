package ua.com.javarush.island_life_simulator.field;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;

public class GameField {
    private final Cell[][] islandField = new Cell[ISLAND_HEIGHT][ISLAND_WIDTH];

    public void createIsland() {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                islandField[y][x] = new Cell(new ItemPosition(x, y));
            }
        }
    }

    public Cell getCellFromField(int y, int x) {
        return islandField[y][x];
    }
}
