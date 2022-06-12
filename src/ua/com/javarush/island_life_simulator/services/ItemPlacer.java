package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemPlacer {
    public void putItemsOnTheField(Object item) {
        if (item instanceof Animal animal) {
            Cell cell = islandField[animal.getAnimalPosition().getY()][animal.getAnimalPosition().getX()];
            cell.addAnimalToList(animal);
        }
        if (item instanceof Plant plant) {
            Cell cell = islandField[plant.getPlantPosition().getY()][plant.getPlantPosition().getX()];
            cell.addPlantToList(plant);
        }
    }
}
