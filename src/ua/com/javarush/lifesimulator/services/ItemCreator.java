package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.configuration.AnimalConfiguration;
import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.factories.AnimalFactory;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.items.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;

public class ItemCreator {
    private final ItemPlacer itemPlacer;
    private final AnimalConfiguration animalConfiguration;
    private final ItemConditionsChecker itemConditionsChecker;
    private final GameEventsController gameEventsController;

    public ItemCreator(AnimalConfiguration animalConfiguration, ItemPlacer itemPlacer, ItemConditionsChecker itemConditionsChecker, GameEventsController gameEventsController) {
        this.animalConfiguration = animalConfiguration;
        this.itemPlacer = itemPlacer;
        this.itemConditionsChecker = itemConditionsChecker;
        this.gameEventsController = gameEventsController;
    }

    public void createAnimals() {
        List<Animal> animalList = getAnimalList();

        for (Animal animal : animalList) {
            AnimalFactory animalFactory = new AnimalFactory(animal);
            NumberOfItemsOnField animalsAmount = animal.getClass().getAnnotation(NumberOfItemsOnField.class);
            int minAmount = animalsAmount.minAmount();
            int maxAmount = animalsAmount.maxAmount();
            int resultAmount = ((int) (Math.random() * (maxAmount - minAmount))) + minAmount;

            for (int i = 0; i < resultAmount; i++) {
                Animal newAnimal = animalFactory.makeClone();
                boolean isPositionFind = false;

                do {
                    newAnimal.setItemPosition(new ItemPosition());
                    if (itemConditionsChecker.canAddItemToCell(newAnimal)) {
                        gameEventsController.countAnimals();
                        isPositionFind = true;
                    }
                } while (!isPositionFind);

                itemPlacer.putItemOnTheField(newAnimal);
            }
        }
    }

    private List<Animal> getAnimalList() {
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

    public void createNewbornAnimal(Class<? extends Animal> animalClass, ItemPosition itemPosition) {
        List<Animal> allAnimalsList = getAnimalList();

        for (Animal animal : allAnimalsList) {
            if (animal.getClass().equals(animalClass)) {
                Animal newAnimal = new AnimalFactory(animal).makeClone();
                newAnimal.setItemPosition(itemPosition);
                itemPlacer.putItemOnTheField(newAnimal);
            }
        }
    }

    public void createPlants() {
        for (int i = 0; i < 10; i++) {
            Plant plant = new Plant();
            boolean isPositionFind = false;

            do {
                plant.setItemPosition(new ItemPosition());
                if (itemConditionsChecker.canAddItemToCell(plant)) {
                    gameEventsController.countPlants();
                    isPositionFind = true;
                }
            } while (!isPositionFind);

            itemPlacer.putItemOnTheField(plant);
        }
    }
}
