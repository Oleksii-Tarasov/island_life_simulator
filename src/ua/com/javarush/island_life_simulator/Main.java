package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.game_field.IslandField;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** Island Life Simulator ***");
        IslandField islandField = new IslandField();

        islandField.createIsland();
        islandField.fillingIslandWithAnimals();
        islandField.printIsland();
    }
}
