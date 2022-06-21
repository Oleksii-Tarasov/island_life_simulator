package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.plants.Plant;
import ua.com.javarush.lifesimulator.services.*;

import java.util.List;

public class GameController {
    private GameBoard gameBoard;
    private final Utility utility = new Utility();
    private final GameEventsController gameEventsController = new GameEventsController();
    private final ConditionsChecker itemConditionsChecker = new ConditionsChecker(utility);
    private final ItemCreator itemCreator = new ItemCreator(gameEventsController, itemConditionsChecker, utility);
    private final WorldUpdater gameUpdater = new WorldUpdater(gameEventsController, itemCreator);
    private final ItemMover itemMover = new ItemMover(itemConditionsChecker);
    private final ItemPrinter itemPrinter = new ItemPrinter(gameEventsController, utility);
    LifeProcessHandler lifeProcessHandler = new LifeProcessHandler(itemCreator, itemConditionsChecker, gameEventsController);

    public void createGameBoard() {
        int width = 10;
        int height = 100;
        gameBoard = itemCreator.createBoard(width, height);
    }

    public void createAnimals() {
        itemCreator.createAnimals(gameBoard);
    }

    public void createPlants() {
        itemCreator.createPlants(gameBoard);
    }

    public void printGame() {
        itemPrinter.printGameBoard(gameBoard);
        itemPrinter.dailyInformer();
    }

    public void updateGameEvents() {
        gameEventsController.updateDailyEvents();
        gameEventsController.countingDays();
    }

    public void updateGameWorld() {
        gameUpdater.dailyWorldUpdate(gameBoard);
    }

    public void executeDayPhases() {
        for (int y = 0; y < gameBoard.getHeight(); y++) {
            for (int x = 0; x < gameBoard.getWidth(); x++) {
                Cell cell = gameBoard.getCell(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    gameEventsController.countingLocationsWithoutAnimals();
                    continue;
                }

                move(animalList);
                eat(animalList, cell.getPlantList());
                if (!gameEventsController.isCataclysmCome()) {
                    reproduction(cell);
                }
            }
        }
    }

    private void move(List<Animal> animalList) {
        itemMover.moveAnimals(gameBoard, animalList);
    }

    private void eat(List<Animal> animalList, List<Plant> plantList) {
        lifeProcessHandler.eatAnimals(animalList);
        lifeProcessHandler.eatPlants(animalList, plantList);
    }

    private void reproduction(Cell cell) {
        lifeProcessHandler.reproduction(gameBoard, cell);
    }

    public boolean isGameOver() {
        return itemConditionsChecker.isWorldAlive(gameBoard, gameEventsController.getNumberOfLocationsWithoutAnimals());
    }
}
