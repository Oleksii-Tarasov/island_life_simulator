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
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
