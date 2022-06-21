package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;

import java.util.List;

public class WorldUpdater {
    private final GameEventsController gameEventsController;
    private final ItemCreator itemCreator;

    public WorldUpdater(GameEventsController gameEventsController, ItemCreator itemCreator) {
        this.gameEventsController = gameEventsController;
        this.itemCreator = itemCreator;
    }

    public void dailyWorldUpdate(GameBoard gameBoard) {

        for (int y = 0; y < gameBoard.getHeight(); y++) {
            for (int x = 0; x < gameBoard.getWidth(); x++) {
                Cell cell = gameBoard.getCell(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    continue;
                }

                reduceSaturation(animalList);
                starvingToDeath(animalList);
                resetWalkStatus(animalList);
            }
        }

        if (!gameEventsController.isCataclysmCome()) {
            createNewPlants(gameBoard);
        }
    }

    public void reduceSaturation(List<Animal> animalList) {
        animalList.forEach(Animal::reduceSaturation);
    }

    public void starvingToDeath(List<Animal> animalList) {
        animalList.stream().filter(animal -> animal.getCurrentSaturation() <= 0).forEach(animal -> gameEventsController.countingDeadAnimals());
        animalList.removeIf(animal -> animal.getCurrentSaturation() <= 0);
    }

    public void resetWalkStatus(List<Animal> animalList) {
        animalList.forEach(animal -> animal.setAlreadyWalked(false));
    }

    public void createNewPlants(GameBoard gameBoard) {
        itemCreator.createPlants(gameBoard);
    }
}
