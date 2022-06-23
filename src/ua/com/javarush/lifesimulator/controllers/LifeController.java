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
            gameController.updateGameEvents();
            gameController.updateGameWorld();
            gameController.executeDayPhases();
            gameController.compileWorldStatistics();
            gameController.printGame();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isWorldAlive = gameController.isGameOver();
        }
    }
}
