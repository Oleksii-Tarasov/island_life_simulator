package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;

import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_BOARD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_BOARD_WIDTH;
import static ua.com.javarush.lifesimulator.constants.PrintableFieldElements.*;

public class ItemPrinter {
    private final Utility utility;
    private final GameEventsController gameEventsController;

    public ItemPrinter(GameEventsController gameEventsController, Utility utility) {
        this.gameEventsController = gameEventsController;
        this.utility = utility;
    }

    public void printGameBoard(GameBoard gameBoard) {
        for (int y = 0; y < GAME_BOARD_HEIGHT; y++) {
            for (int x = 0; x < GAME_BOARD_WIDTH; x++) {
                Cell cell = gameBoard.getCell(y, x);
                System.out.print(OPEN_CELL + utility.getItemForPrint(cell) + CLOSE_CELL);
            }
            System.out.println();
        }
    }

    public void dailyInformer() {
        int daysNumber = gameEventsController.getDaysNumber();
        int newbornAnimalsNumber = gameEventsController.getNewbornAnimalsNumber();
        int deadAnimalsNumber = gameEventsController.getDeadAnimalsNumber();
        int allAnimalsNumber = gameEventsController.getAllAnimalsNumber();

        System.out.printf("\nOn the %d day in the World %d new animals were born.\n" + "%d animals died.\n" +
                "At all in the World %d animals.\n", daysNumber, newbornAnimalsNumber, deadAnimalsNumber, allAnimalsNumber);
        System.out.println(FIELD_DELIMITER);
    }
}
