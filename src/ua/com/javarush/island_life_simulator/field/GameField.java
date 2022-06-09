package ua.com.javarush.island_life_simulator.field;

import ua.com.javarush.island_life_simulator.services.ItemCreator;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;

public class GameField {
    public static Cell[][] islandField;

    public void createIsland() {
        islandField = new Cell[ISLAND_HEIGHT][ISLAND_WIDTH];
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                islandField[y][x] = new Cell(new ItemPosition(y, x));
            }
        }
    }

    public void fillingIslandWithAnimals() {
//        ItemCreator itemCreator = new ItemCreator();
//        itemCreator.createAnimals();
//        itemCreator.putAnimalsOnTheField();
    }

    public void printIsland() {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                System.out.print(islandField[y][x]);
            }
            System.out.println();
        }
        System.out.println("______________________");
    }
}
