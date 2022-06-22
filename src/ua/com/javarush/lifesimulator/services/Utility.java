package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.configuration.AnimalConfigurations;
import ua.com.javarush.lifesimulator.configuration.Characteristics;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.DELIMITER;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.EMPTY_CELL;

public class Utility {
    private final AnimalConfigurations animalConfiguration = new AnimalConfigurations();

    public List<Animal> getListWithAllTypesOfAnimals() {
        List<Animal> animalList = new ArrayList<>();
        Map<Class<?>, Characteristics> animalCharacteristicsMap = animalConfiguration.getAnimalCharacteristicsMap();

        for (Map.Entry<Class<?>, Characteristics> animalCharacteristic : animalCharacteristicsMap.entrySet()) {
            Class<?> animalClass = animalCharacteristic.getKey();
            String animalType = animalCharacteristic.getValue().getAnimalType();
            double weight = animalCharacteristic.getValue().getWeight();
            int maxAmountOnCell = animalCharacteristic.getValue().getMaxAmountOnCell();
            int speed = animalCharacteristic.getValue().getSpeed();
            double fullSaturation = animalCharacteristic.getValue().getFullSaturation();
            double weightLossPerDay = animalCharacteristic.getValue().getWeightLossPerDay();

            try {
                Constructor<?> animalConstructor = animalClass.getConstructor(String.class, double.class, int.class, int.class, double.class, double.class);
                Animal animal = (Animal) animalConstructor.newInstance(animalType, weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
                animalList.add(animal);

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println(UNABLE_TO_PROCESS_CLASS + e.getMessage());
            }
        }

        return animalList;
    }

    public HashMap getAnimalChanceToEatMap() {
        return animalConfiguration.getAnimalChanceToEatMap();
    }

    public List<BasicItem> getBasicItemListFromCell(GameBoard gameBoard, BasicItem basicItem) {
        int x = basicItem.getItemPosition().getX();
        int y = basicItem.getItemPosition().getY();

        Cell cell = gameBoard.getCell(y, x);

        if (basicItem instanceof Animal) {
            return new ArrayList<>(cell.getAnimalList());
        } else {
            return new ArrayList<>(cell.getPlantList());
        }
    }

    public String getItemForPrint(Cell cell) {
        StringBuilder itemsForPrint = new StringBuilder();
        List<Animal> animalList = cell.getAnimalList();
        List<Plant> plantList = cell.getPlantList();
        List<BasicItem> basicItemList;

        if (animalList.isEmpty() && plantList.isEmpty()) {
            return itemsForPrint.append(EMPTY_CELL).toString();
        }

        if (!animalList.isEmpty()) {
            basicItemList = new ArrayList<>(animalList);
            itemsForPrint.append(visualizeItemForPrint(basicItemList));
        }

        if (!plantList.isEmpty()) {
            basicItemList = new ArrayList<>(plantList);
            itemsForPrint.append(visualizeItemForPrint(basicItemList));
        }

        return itemsForPrint.toString();
    }

    private String visualizeItemForPrint(List<BasicItem> basicItemList) {
        StringBuilder items = new StringBuilder();

        Map<String, Long> itemMap = basicItemList.stream()
                .collect(groupingBy(BasicItem::toString, Collectors.counting()));

        for (Map.Entry<String, Long> basicItem : itemMap.entrySet()) {
            if (basicItem.getValue() == 1) {
                items.append(basicItem.getKey()).append(DELIMITER);
            } else {
                items.append(basicItem.getKey()).append("x").append(basicItem.getValue()).append(DELIMITER);
            }
        }
        return items.toString();
    }
}
