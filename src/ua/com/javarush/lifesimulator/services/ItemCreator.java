package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.factories.AnimalFactory;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.List;

import static ua.com.javarush.lifesimulator.constants.GameConstants.*;

public class ItemCreator {
    ItemPlacer itemPlacer = new ItemPlacer();
    private final Utility utility;
    private final ConditionsChecker itemConditionsChecker;
    private final GameEventsController gameEventsController;

    public ItemCreator(GameEventsController gameEventsController, ConditionsChecker itemConditionsChecker, Utility utility) {
        this.gameEventsController = gameEventsController;
        this.itemConditionsChecker = itemConditionsChecker;
        this.utility = utility;
    }

    public void createAnimals(GameBoard gameBoard) {
        List<Animal> animalList = utility.getListWithAllTypesOfAnimals();

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
                        gameEventsController.countingNewbornAnimals();
                        isPositionFind = true;
                    }
                } while (!isPositionFind);

                itemPlacer.putItemOnTheField(gameBoard, newAnimal);
            }
        }
    }

    public void createNewbornAnimal(GameBoard gameBoard, Animal animal) {
        ItemPosition currentItemPosition = animal.getItemPosition();
        Animal newBornAnimal = new AnimalFactory(animal).makeClone();
        newBornAnimal.setItemPosition(currentItemPosition);
        itemPlacer.putItemOnTheField(gameBoard,newBornAnimal);
    }

    public void createPlants(GameBoard gameBoard) {
        for (int i = 0; i < PLANTS_MAX_AMOUNT; i++) {
            Plant plant = new Plant();
            boolean isPositionFind = false;

            do {
                plant.setItemPosition(new ItemPosition());
                if (itemConditionsChecker.canAddItemToCell(gameBoard, plant)) {
                    isPositionFind = true;
                }
            } while (!isPositionFind);

            itemPlacer.putItemOnTheField(gameBoard, plant);
        }
    }

    public GameBoard createBoard() {
        Cell[][] newBoard = new Cell[GAME_BOARD_HEIGHT][GAME_BOARD_WIDTH];
        GameBoard gameBoard = new GameBoard(newBoard);

        for (int y = 0; y < GAME_BOARD_HEIGHT; y++) {
            for (int x = 0; x < GAME_BOARD_WIDTH; x++) {
                Cell cell = new Cell(new ItemPosition(x, y));
                gameBoard.setCell(cell, y, x);
            }
        }

        return gameBoard;
    }

//    public GameBoard createBoard(int width, int height) {
//        Cell[][] newBoard = new Cell[height][width];
//        GameBoard gameBoard = new GameBoard(newBoard, width, height);
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                Cell cell = new Cell(new ItemPosition(x, y));
//                gameBoard.setCell(cell, y, x);
//            }
//        }
//
//        return gameBoard;
//    }
}
