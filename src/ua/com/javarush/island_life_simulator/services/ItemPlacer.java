package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemPlacer {
    public void putItemsOnTheField(Object item) {
        if (item instanceof Animal animal) {
            Cell cell = islandField[animal.getAnimalPosition().getY()][animal.getAnimalPosition().getX()];
            cell.addAnimalToList(animal);
        }
    }
}
