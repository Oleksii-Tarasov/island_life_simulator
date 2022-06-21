package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.configuration.AnimalConfiguration;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.GameBoard;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.DELIMITER;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.EMPTY_CELL;

public class Utility {
    private final AnimalConfiguration animalConfiguration = new AnimalConfiguration();

    public List<Animal> getAnimalListFromConfigurations() {
        List<Animal> animalList = new ArrayList<>();
        Map<Class<?>, List<Number>> animalCharacteristicsMap = animalConfiguration.getAnimalCharacteristicsMap();

        for (Map.Entry<Class<?>, List<Number>> animalCharacteristic : animalCharacteristicsMap.entrySet()) {
            List<Number> characteristicList = new ArrayList<>(animalCharacteristic.getValue());
            double weight = (double) characteristicList.get(0);
            int maxAmountOnCell = (int) characteristicList.get(1);
            int speed = (int) characteristicList.get(2);
            double fullSaturation = (double) characteristicList.get(3);
            double weightLossPerDay = (double) characteristicList.get(4);
            Class<?> animalClass = animalCharacteristic.getKey();

            try {
                Constructor<?> animalConstructor = animalClass.getConstructor(double.class, int.class, int.class, double.class, double.class);
                Animal animal = (Animal) animalConstructor.newInstance(weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
                animalList.add(animal);

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println(UNABLE_TO_PROCESS_CLASS + e.getMessage());
            }
        }

        return animalList;
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

    public String getItemForPrint(Cell cell){
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

        if (!plantList.isEmpty())
        {
            basicItemList = new ArrayList<>(plantList);
            itemsForPrint.append(visualizeItemForPrint(basicItemList));
        }

        return itemsForPrint.toString();
    }

    private String visualizeItemForPrint(List<BasicItem> basicItemList) {
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
