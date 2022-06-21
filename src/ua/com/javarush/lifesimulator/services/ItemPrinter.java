package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameController;
import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.controllers.Utility;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.items.GameBoard;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_WIDTH;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.*;

public class ItemPrinter {
    private final Utility utility;
    private final GameEventsController gameEventsController;

    public ItemPrinter(GameEventsController gameEventsController, Utility utility) {
        this.gameEventsController = gameEventsController;
        this.utility = utility;
    }

    public void printGameBoard(GameBoard gameBoard) {
        for (int y = 0; y < gameBoard.getHeight(); y++) {
            for (int x = 0; x < gameBoard.getWidth(); x++) {
                Cell cell = gameBoard.getCell(y, x);
                System.out.print(OPEN_CELL + utility.getItemForPrint(cell) + CLOSE_CELL);
            }
            System.out.println();
        }
    }

    public void dailyInformer() {
        int daysNumber = gameEventsController.getDaysNumber();
        int allAnimalsNumber = gameEventsController.getAllAnimalsNumber();
        int allPlantsNumber = gameEventsController.getAllPlantsNumber();

        int deadAnimalsNumber = gameEventsController.getDeadAnimalsNumber();
        int deadPlantsNumber = gameEventsController.getDeadPlantsNumber();
        int animalsNumber = gameEventsController.getAnimalsNumber();

        System.out.printf("On the %d day in the World %d new animals were born.\n" +
                "%d animals and %d plants died.\n" +
                "At all in the World %d animals and %d plants.\n", daysNumber, animalsNumber, deadAnimalsNumber, deadPlantsNumber, allAnimalsNumber, allPlantsNumber);
        System.out.println(FIELD_DELIMITER);
    }
}
