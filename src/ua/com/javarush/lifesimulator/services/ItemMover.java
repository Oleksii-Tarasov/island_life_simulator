package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;

import java.util.ArrayList;
import java.util.List;

import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_WIDTH;


public class ItemMover {
    private final ConditionsChecker itemConditionsChecker;

    public ItemMover(ConditionsChecker itemConditionsChecker) {
        this.itemConditionsChecker = itemConditionsChecker;
    }

    public void moveAnimals(GameBoard gameBoard, List<Animal> animalList) {
        List<Animal> listAnimalsForRemoving = new ArrayList<>();

        for (Animal animal : animalList) {
            if (itemConditionsChecker.cantMove(animal)) {
                continue;
            }

            ItemPosition currentItemPosition = animal.getItemPosition();
            ItemPosition newItemPosition = calculateNewDestination(animal);

            if (itemConditionsChecker.hasDestinationChanged(currentItemPosition, newItemPosition)) {
                animal.setItemPosition(newItemPosition);
                if (itemConditionsChecker.canAddItemToCell(gameBoard, animal)) {
                    Cell cellForAddingAnimal = gameBoard.getCell(newItemPosition.getY(), newItemPosition.getX());
                    cellForAddingAnimal.addAnimalToList(animal);
                    animal.setAlreadyWalked(true);
                } else {
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
                    if (x + 1 < GAME_BOARD_WIDTH) {
                        position.setX(x + 1);
                    }
                }
                case "Up" -> {
                    if (y - 1 >= 0) {
                        position.setY(y - 1);
                    }
                }
                case "Down" -> {
                    if (y + 1 < GAME_BOARD_HEIGHT) {
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
