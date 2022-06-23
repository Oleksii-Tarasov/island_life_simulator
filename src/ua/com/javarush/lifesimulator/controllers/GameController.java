package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.services.*;

import java.util.List;

import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_WIDTH;

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
        gameBoard = itemCreator.createBoard();
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
    }

    public void updateGameWorld() {
        gameUpdater.dailyWorldUpdate(gameBoard);
    }

    public void executeDayPhases() {
        for (int y = 0; y < GAME_BOARD_HEIGHT; y++) {
            for (int x = 0; x < GAME_BOARD_WIDTH; x++) {
                Cell cell = gameBoard.getCell(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    gameEventsController.countingLocationsWithoutAnimals();
                    continue;
                }

                itemMover.moveAnimals(gameBoard, animalList);
//                lifeProcessHandler.eatPlants(animalList, cell.getPlantList());
//                lifeProcessHandler.eatAnimals(animalList);
                if (!gameEventsController.isCataclysmCome()) {
//                    lifeProcessHandler.reproduction(gameBoard, animalList);
                }
            }
        }
    }

    public void compileWorldStatistics() {
        gameEventsController.countingDays();
        gameEventsController.setAllAnimalsNumber(utility.getAllAnimalsNumberInTheWorld(gameBoard));
    }

    public boolean isGameOver() {
        return itemConditionsChecker.isWorldAlive(gameEventsController.getNumberOfLocationsWithoutAnimals());
    }
}
