package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.BasicItem;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

public class ItemPlacer {
    public void putItemOnTheField(BasicItem item) {
        int x = item.getItemPosition().getX();
        int y = item.getItemPosition().getY();
        Cell cell = GameField.islandField[y][x];

        if (item instanceof Animal animal) {
            cell.addAnimalToList(animal);
        }
        if (item instanceof Plant plant) {
            cell.addPlantToList(plant);
        }
    }
}
