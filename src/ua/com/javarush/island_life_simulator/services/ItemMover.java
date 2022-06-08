package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemMover {
    public void moveItem(Animal animal) {
        ItemPosition currentPosition = animal.getAnimalPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        ItemPosition newItemPosition = calculateNewDestination(animal);

        if (checkingIfDestinationHasChanged(x, y, newItemPosition)) {
            Cell cellForRemovingAnimal = islandField[y][x];
            cellForRemovingAnimal.removeAnimalFromList(animal);

            Cell cell = islandField[newItemPosition.getY()][newItemPosition.getX()];
            cell.addAnimalToListsByType(animal);
        }
    }

    private ItemPosition calculateNewDestination(Animal animal) {
        int speed = animal.getSpeed();
        System.out.println(speed);
        ItemPosition position = animal.getAnimalPosition();

        for (int i = 0; i < speed; i++) {
            String direction = animal.chooseDirection();
            System.out.println(direction);
            int x = position.getX();
            int y = position.getY();

            switch (direction) {
                case "Left" -> {
                    if (x - 1 >= 0) {
                        position.setX(x - 1);
                        animal.setAnimalPosition(position);
                    }
                }
                case "Right" -> {
                    if (x + 1 <= ISLAND_WIDTH) {
                        position.setX(x + 1);
                        animal.setAnimalPosition(position);
                    }
                }
                case "Up" -> {
                    if (y - 1 >= 0) {
                        position.setY(y - 1);
                        animal.setAnimalPosition(position);
                    }
                }
                case "Down" -> {
                    if (y + 1 <= ISLAND_HEIGHT) {
                        position.setY(y + 1);
                        animal.setAnimalPosition(position);
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
