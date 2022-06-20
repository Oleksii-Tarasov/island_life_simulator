package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.items.Animal;

import java.util.List;

import static ua.com.javarush.lifesimulator.constants.GameSettings.*;

public class GameUpdater {
    private final GameField gameField;
    private final GameEventsController gameEventsController;
    private final ItemCreator itemCreator;

    public GameUpdater(GameField gameField, ItemCreator itemCreator, GameEventsController gameEventsController) {
        this.gameField = gameField;
        this.itemCreator = itemCreator;
        this.gameEventsController = gameEventsController;
    }

    public void dailyWorldUpdate() {

        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
                Cell cell = gameField.getCellFromField(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    continue;
                }

                reduceSaturation(animalList);
                starvingToDeath(animalList);
                resetWalkStatus(animalList);
            }
        }

        if (gameEventsController.getDaysNumber() < CATACLYSM_DAY) {
            createNewPlants();
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

    public void createNewPlants() {
        itemCreator.createPlants();
    }
}
