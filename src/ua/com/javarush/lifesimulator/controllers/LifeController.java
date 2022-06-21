package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.services.ItemConditionsChecker;

public class LifeController {
    GameController gameController = new GameController();
    private boolean isWorldAlive;

    public void startZeroDay() {
        gameController.createGameBoard();
        gameController.createAnimals();
        gameController.createPlants();
        gameController.printGame();
        isWorldAlive = true;
    }

    public void startDailyCycle() {
        while (isWorldAlive) {
            gameController.updateGameEvents();
            gameController.updateGameWorld();

//            executeDailyPhase();
//
////            itemPrinter.printGameField();
//            itemPrinter.dailyInformer();
//
//            isWorldAlive = itemConditionsChecker.isWorldAlive(gameEventsController.getNumberOfLocationsWithoutAnimals());
            isWorldAlive = false;
        }
    }
//
//    private void executeDailyPhase() {
//        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
//            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
//                Cell cell = gameField.getCellFromField(y, x);
//                List<Animal> animalList = cell.getAnimalList();
//
//                if (animalList.isEmpty()) {
//                    gameEventsController.countingLocationsWithoutAnimals();
//                    continue;
//                }
//
//                lifeCycleExecutor.movingAnimals(animalList);
//                lifeCycleExecutor.eatPlants(animalList, cell.getPlantList());
//                lifeCycleExecutor.eatAnimals(animalList);
//                if (gameEventsController.getDaysNumber() < CATACLYSM_DAY) {
//                    lifeCycleExecutor.reproduction(cell);
//                }
//            }
//        }
//    }
}
