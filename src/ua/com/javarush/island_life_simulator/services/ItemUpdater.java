package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class ItemUpdater {
    private final GameField gameField;
    private final ItemCreator itemCreator;

    public ItemUpdater(GameField gameField, ItemCreator itemCreator) {
        this.gameField = gameField;
        this.itemCreator = itemCreator;
    }

    public void dailyWorldUpdate() {

        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
                Cell cell = gameField.getCellFromField(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if(animalList.isEmpty()) {
                    return;
                }

                reduceSaturation(animalList);
                starvingToDeath(animalList);
                resetWalkStatus(animalList);
                createNewPlants();
            }
        }
    }

    public void reduceSaturation(List<Animal> animalList) {
        animalList.forEach(Animal::reduceSaturation);
    }

    public void starvingToDeath(List<Animal> animalList) {
        animalList.removeIf(animal -> animal.getCurrentSaturation() <= 0);
    }

    public void resetWalkStatus(List<Animal> animalList) {
        animalList.forEach(animal -> animal.setAlreadyWalked(false));
    }

    public void createNewPlants() {
        itemCreator.createPlants();
    }
}
