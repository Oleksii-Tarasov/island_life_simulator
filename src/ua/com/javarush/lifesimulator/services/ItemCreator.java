package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.factories.AnimalFactory;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.items.Plant;

import java.lang.reflect.InvocationTargetException;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;
import static ua.com.javarush.lifesimulator.constants.ItemSettings.ANIMAL_LIST_FOR_TEST;

public class ItemCreator {
    private final ItemPlacer itemPlacer;
    private final ItemConditionsChecker itemConditionsChecker;
    private final GameEventsController gameEventsController;

    public ItemCreator(ItemPlacer itemPlacer, ItemConditionsChecker itemConditionsChecker, GameEventsController gameEventsController) {
        this.itemPlacer = itemPlacer;
        this.itemConditionsChecker = itemConditionsChecker;
        this.gameEventsController = gameEventsController;
    }

    public void createAnimals() {
        for (Class<? extends Animal> animalClass : ANIMAL_LIST_FOR_TEST) {
            AnimalFactory animalFactory = new AnimalFactory(animalClass);
            NumberOfItemsOnField animalsAmount = animalClass.getAnnotation(NumberOfItemsOnField.class);
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


    public void createNewbornAnimal(Class<? extends Animal> animalClass, ItemPosition itemPosition) {
        try {
            Animal newAnimal = (Animal) Class.forName(animalClass.getName()).getConstructor().newInstance();
            newAnimal.setItemPosition(itemPosition);
            itemPlacer.putItemOnTheField(newAnimal);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println(UNABLE_TO_PROCESS_CLASS);
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
