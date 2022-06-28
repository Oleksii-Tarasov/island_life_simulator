package ua.com.javarush.lifesimulator.controllers;

public class LifeController {
    GameController gameController = new GameController();

    public void startZeroDay() {
        gameController.createGameBoard();
        gameController.createAnimals();
        gameController.createPlants();
        gameController.compileWorldStatistics();
        gameController.printGame();

        startDailyCycle();
    }

    public void startDailyCycle() {
        boolean isWorldAlive = true;

        while (isWorldAlive) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            gameController.updateGameEvents();
            gameController.updateGameWorld();
            gameController.executeDayPhases();
            gameController.compileWorldStatistics();
            gameController.printGame();

            isWorldAlive = gameController.isGameOver();
        }
    }
}
