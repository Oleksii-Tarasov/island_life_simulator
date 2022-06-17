package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.controllers.GameEventsController;
import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.BasicItem;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_WIDTH;
import static ua.com.javarush.island_life_simulator.constants.PrintableFieldElements.*;

public class ItemPrinter {
    private final GameField gameField;
    private final GameEventsController gameEventsController;

    public ItemPrinter(GameField gameField, GameEventsController gameEventsController) {
        this.gameField = gameField;this.gameEventsController = gameEventsController;
    }

    public void printGameField() {
        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
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

    public void zeroDayInformer() {
        int animalsNumber = gameEventsController.getAnimalsNumber();
        int plantsNumber = gameEventsController.getPlantsNumber();

        System.out.printf("On the first day of the creation of the World %d animals and %d plants were created. \n", animalsNumber, plantsNumber);
        System.out.println(FIELD_DELIMITER);
    }

    public void dailyInformer() {
        int daysNumber = gameEventsController.getDaysNumber();
        int deadAnimalsNumber = gameEventsController.getDeadAnimalsNumber();
        int deadPlantsNumber = gameEventsController.getDeadPlantsNumber();
        int newbornAnimalsNumber = gameEventsController.getNewbornAnimalsNumber();
        int plantsNumber = gameEventsController.getPlantsNumber();

        System.out.printf("On the %d day %d animals and %d plants died.\n " +
                "%d new animals were born and %d new plants grew.", daysNumber, deadAnimalsNumber, deadPlantsNumber, newbornAnimalsNumber, plantsNumber);
    }
}
