package ua.com.javarush.island_life_simulator.game_field;

import ua.com.javarush.island_life_simulator.services.ItemCreator;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;

public class IslandField {
    private final int width = ISLAND_WIDTH;
    private final int height = ISLAND_HEIGHT;
    public static Cell[][] islandField;

    public void createIsland() {
        islandField = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                islandField[i][j] = new Cell(new ItemPosition(i, j));
            }
        }
    }

    public void fillingIslandWithAnimals() {
        ItemCreator itemCreator = new ItemCreator();
        itemCreator.createAnimals();
        itemCreator.putAnimalsOnTheField();
    }

    public void printIsland() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(islandField[i][j]);
            }
            System.out.println();
        }
    }
}
