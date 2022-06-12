package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemPrinter {
    public void printGameField() {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = islandField[y][x];
                System.out.print(OPEN_CELL+ getItemForPrint(cell) + CLOSE_CELL);
            }
            System.out.println();
        }
        System.out.println("___________________");
    }

    private String getItemForPrint(Cell cell){
        StringBuilder itemsForPrint = new StringBuilder();
        List<Animal> animalList = cell.getAnimalList();
        List<Plant> plantList = cell.getPlantList();

        if (animalList.isEmpty() && plantList.isEmpty()) {
            return itemsForPrint.append(EMPTY_CELL).toString();
        }

        if (!animalList.isEmpty()) {
            itemsForPrint.append(visualizeAnimal(animalList));
        }

        if (!plantList.isEmpty())
        {
            itemsForPrint.append(plantList.get(0)).append("x").append(plantList.size());
        }

        return itemsForPrint.toString();
    }

    private String visualizeAnimal(List<? extends Animal> animalList) {
        StringBuilder items = new StringBuilder();

        Map<String, Long> animalMap = animalList.stream()
                .collect(groupingBy(Animal::toString, Collectors.counting()));

        for(Map.Entry<String, Long> animal : animalMap.entrySet()) {
            items.append(animal.getKey()).append("x").append(animal.getValue());
        }

        return items.toString();
    }
}
