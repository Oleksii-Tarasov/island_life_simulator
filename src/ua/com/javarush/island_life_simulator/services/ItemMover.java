package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.ArrayList;
import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemMover {
    public void moveItems(List<Animal> animalList) {
        List<Animal> listAnimalsForRemoving = new ArrayList<>();

        for (Animal animal : animalList) {
            if (animal.isAlreadyWalked() || animal.getSpeed() == 0) {
                continue;
            }

            ItemPosition currentItemPosition = animal.getItemPosition();
            ItemPosition newItemPosition = calculateNewDestination(animal);

            if (ItemConditionsChecker.hasDestinationChanged(currentItemPosition, newItemPosition) ) {
                animal.setItemPosition(newItemPosition);
                if (ItemConditionsChecker.canAddItemToCell(animal)) {
                    Cell cellForAddingAnimal = islandField[newItemPosition.getY()][newItemPosition.getX()];
                    cellForAddingAnimal.addAnimalToList(animal);
                    animal.setAlreadyWalked(true);
                }
                else {
                    animal.setItemPosition(currentItemPosition);
                }
                listAnimalsForRemoving.add(animal);
            }
        }
        new ItemRemover().removeAnimals(animalList, listAnimalsForRemoving);
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
                    if (x + 1 < ISLAND_WIDTH) {
                        position.setX(x + 1);
                    }
                }
                case "Up" -> {
                    if (y - 1 >= 0) {
                        position.setY(y - 1);
                    }
                }
                case "Down" -> {
                    if (y + 1 < ISLAND_HEIGHT) {
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
