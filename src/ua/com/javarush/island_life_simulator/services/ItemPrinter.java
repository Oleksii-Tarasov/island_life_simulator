package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.BasicItem;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.constants.PrintableFieldElements.*;

public class ItemPrinter {
    private final GameField gameField;

    public ItemPrinter(GameField gameField) {
        this.gameField = gameField;
    }

    public void printGameField() {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = gameField.getCellFromField(y, x);
                System.out.print(OPEN_CELL + getItemForPrint(cell) + CLOSE_CELL);
            }
            System.out.println();
        }
        System.out.println(FIELD_DELIMITER);
    }

    private String getItemForPrint(Cell cell){
        StringBuilder itemsForPrint = new StringBuilder();
        List<Animal> animalList = cell.getAnimalList();
        List<Plant> plantList = cell.getPlantList();
        List<BasicItem> basicItemList;

        if (animalList.isEmpty() && plantList.isEmpty()) {
            return itemsForPrint.append(EMPTY_CELL).toString();
        }

        if (!animalList.isEmpty()) {
            basicItemList = new ArrayList<>(animalList);
            itemsForPrint.append(visualizeItem(basicItemList));
        }

        if (!plantList.isEmpty())
        {
            basicItemList = new ArrayList<>(plantList);
            itemsForPrint.append(visualizeItem(basicItemList));
        }

        return itemsForPrint.toString();
    }

    private String visualizeItem(List<BasicItem> basicItemList) {
        StringBuilder items = new StringBuilder();

        Map<String, Long> itemMap = basicItemList.stream()
                .collect(groupingBy(BasicItem::toString, Collectors.counting()));

        for(Map.Entry<String, Long> basicItem : itemMap.entrySet()) {
            if (basicItem.getValue() == 1) {
                items.append(basicItem.getKey()).append(DELIMITER);
            }
            else {
                items.append(basicItem.getKey()).append("x").append(basicItem.getValue()).append(DELIMITER);
            }
        }
        return items.toString();
    }
}
