package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.items.GameBoard;
//import ua.com.javarush.lifesimulator.services.ItemConditionsChecker;
import ua.com.javarush.lifesimulator.services.GameUpdater;
import ua.com.javarush.lifesimulator.services.ItemConditionsChecker;
import ua.com.javarush.lifesimulator.services.ItemCreator;
import ua.com.javarush.lifesimulator.services.ItemPrinter;

import static ua.com.javarush.lifesimulator.constants.GameSettings.CATACLYSM_DAY;

public class GameController {
    private GameBoard gameBoard;
    private final Utility utility = new Utility();
    GameEventsController gameEventsController = new GameEventsController();
    ItemConditionsChecker itemConditionsChecker = new ItemConditionsChecker(utility);
    ItemCreator itemCreator = new ItemCreator(gameEventsController, itemConditionsChecker, utility);
    GameUpdater gameUpdater = new GameUpdater(gameEventsController, itemCreator);
    ItemPrinter itemPrinter = new ItemPrinter(gameEventsController, utility);

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

    public void executeDailyPhase() {

    }
}
