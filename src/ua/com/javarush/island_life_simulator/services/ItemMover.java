package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.ArrayList;
import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class ItemMover {
    private final ItemConditionsChecker itemConditionsChecker;
    private final GameField gameField;

    public ItemMover(GameField gameField, ItemConditionsChecker itemConditionsChecker) {
        this.itemConditionsChecker = itemConditionsChecker;
        this.gameField = gameField;
    }

    public void moveItems(List<Animal> animalList) {
        List<Animal> listAnimalsForRemoving = new ArrayList<>();

        for (Animal animal : animalList) {
            if (animal.isAlreadyWalked() || animal.getSpeed() == 0) {
                continue;
            }

            ItemPosition currentItemPosition = animal.getItemPosition();
            ItemPosition newItemPosition = calculateNewDestination(animal);

            if (itemConditionsChecker.hasDestinationChanged(currentItemPosition, newItemPosition) ) {
                animal.setItemPosition(newItemPosition);
                if (itemConditionsChecker.canAddItemToCell(animal)) {
                    Cell cellForAddingAnimal = gameField.getCellFromField(newItemPosition.getY(), newItemPosition.getX());
                    cellForAddingAnimal.addAnimalToList(animal);
                    animal.setAlreadyWalked(true);
                }
                else {
                    animal.setItemPosition(currentItemPosition);
                }
                listAnimalsForRemoving.add(animal);
            }
        }
        animalList.removeAll(listAnimalsForRemoving);
    }

    private ItemPosition calculateNewDestination(Animal animal) {
        int speed = animal.getSpeed();
        ItemPosition position = new ItemPosition(animal.getItemPosition().getX(), animal.getItemPosition().getY());

        for (int i = 0; i < speed; i++) {
            String direction = chooseDirection();
            int x = position.getX();
            int y = position.getY();

            switch (direction) {
                case "Left" -> {
                    if (x - 1 >= 0) {
                        position.setX(x - 1);
                    }
                }
                case "Right" -> {
                    if (x + 1 < GAME_FIELD_WIDTH) {
                        position.setX(x + 1);
                    }
                }
                case "Up" -> {
                    if (y - 1 >= 0) {
                        position.setY(y - 1);
                    }
                }
                case "Down" -> {
                    if (y + 1 < GAME_FIELD_HEIGHT) {
                        position.setY(y + 1);
                    }
                }
            }
        }
        return position;
    }

    private String chooseDirection() {
        List<String> directionsList = List.of("Left", "Right", "Up", "Down", "Stand");

        return directionsList.get((int) (Math.random() * directionsList.size()));
    }
}
