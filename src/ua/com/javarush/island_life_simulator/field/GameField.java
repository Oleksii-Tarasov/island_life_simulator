package ua.com.javarush.island_life_simulator.field;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class GameField {
    private final Cell[][] islandField = new Cell[GAME_FIELD_HEIGHT][GAME_FIELD_WIDTH];

    public void createIsland() {
        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
                islandField[y][x] = new Cell(new ItemPosition(x, y));
            }
        }
    }

    public Cell getCellFromField(int y, int x) {
        return islandField[y][x];
    }
}
