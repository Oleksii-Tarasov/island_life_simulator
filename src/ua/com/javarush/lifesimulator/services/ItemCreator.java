package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.controllers.GameController;
import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.controllers.Utility;
import ua.com.javarush.lifesimulator.factories.AnimalFactory;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.items.GameBoard;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.List;

public class ItemCreator {
    ItemPlacer itemPlacer = new ItemPlacer();
    private final Utility utility;
    private final ItemConditionsChecker itemConditionsChecker;
    private final GameEventsController gameEventsController;

    public ItemCreator(GameEventsController gameEventsController, ItemConditionsChecker itemConditionsChecker, Utility utility) {
        this.gameEventsController = gameEventsController;
        this.itemConditionsChecker = itemConditionsChecker;
        this.utility = utility;
    }

    public void createAnimals(GameBoard gameBoard) {
        List<Animal> animalList = utility.getAnimalListFromConfigurations();

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
                    if (itemConditionsChecker.canAddItemToCell(gameBoard, newAnimal)) {
                        gameEventsController.countingAnimals();
                        isPositionFind = true;
                    }
                } while (!isPositionFind);

                itemPlacer.putItemOnTheField(gameBoard, newAnimal);
            }
        }
    }

//    public void createNewbornAnimal(Class<? extends Animal> animalClass, ItemPosition itemPosition) {
//        List<Animal> allAnimalsList = getAnimalList();
//
//        for (Animal animal : allAnimalsList) {
//            if (animal.getClass().equals(animalClass)) {
//                Animal newAnimal = new AnimalFactory(animal).makeClone();
//                newAnimal.setItemPosition(itemPosition);
//                itemPlacer.putItemOnTheField(newAnimal);
//            }
//        }
//    }

    public void createPlants(GameBoard gameBoard) {
        for (int i = 0; i < 2000; i++) {
            Plant plant = new Plant();
            boolean isPositionFind = false;

            do {
                plant.setItemPosition(new ItemPosition());
                if (itemConditionsChecker.canAddItemToCell(gameBoard, plant)) {
                    gameEventsController.countingPlants();
                    isPositionFind = true;
                }
            } while (!isPositionFind);

            itemPlacer.putItemOnTheField(gameBoard, plant);
        }
    }

    public GameBoard createBoard(int width, int height) {
        Cell[][] newBoard = new Cell[height][width];
        GameBoard gameBoard = new GameBoard(newBoard, width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(new ItemPosition(x, y));
                gameBoard.setCell(cell, y, x);
            }
        }

        return gameBoard;
    }
}
