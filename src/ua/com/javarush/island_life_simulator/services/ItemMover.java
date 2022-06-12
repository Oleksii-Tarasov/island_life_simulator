package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemMover {
    public void moveItems(List<Animal> animalList) {
        List<Animal> listAnimalsForRemoving = new ArrayList<>();
        Map<Animal, ItemPosition> mapAnimalsForMoving = new HashMap<>();

        for (Animal animal : animalList) {
            if (!animal.isAlreadyWalked()) {
                ItemPosition currentItemPosition = animal.getAnimalPosition();
                int x = currentItemPosition.getX();
                int y = currentItemPosition.getY();
                ItemPosition newItemPosition = calculateNewDestination(animal);

                if (checkingIfDestinationHasChanged(x, y, newItemPosition)) {
                    mapAnimalsForMoving.put(animal, newItemPosition);
                    listAnimalsForRemoving.add(animal);
                }
            }
        }

        for (Map.Entry<Animal, ItemPosition> pair : mapAnimalsForMoving.entrySet()) {
            Animal animal = pair.getKey();
            ItemPosition newItemPosition = pair.getValue();
            animal.setAnimalPosition(newItemPosition);
            animal.setAlreadyWalked(true);

            Cell cellForAddingAnimal = islandField[newItemPosition.getY()][newItemPosition.getX()];
            cellForAddingAnimal.addAnimalToList(animal);
        }

        new ItemRemover().removeAnimals(animalList, listAnimalsForRemoving);
    }

    private ItemPosition calculateNewDestination(Animal animal) {
        int speed = animal.getSpeed();
        ItemPosition position = animal.getAnimalPosition();

        for (int i = 0; i < speed; i++) {
            String direction = animal.chooseDirection();
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

    private boolean checkingIfDestinationHasChanged(int x, int y, ItemPosition newItemPosition) {
        return (y != newItemPosition.getY() || x != newItemPosition.getX());
    }
}
