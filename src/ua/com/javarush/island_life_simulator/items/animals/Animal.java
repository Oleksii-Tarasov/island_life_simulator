package ua.com.javarush.island_life_simulator.items.animals;

import ua.com.javarush.island_life_simulator.field.ItemPosition;

import java.util.List;

public abstract class Animal {
    private int weight;
    private int maxAmountOnCell;
    private int speed;
    private int fullSaturation;
    private int currentSaturation;
    private ItemPosition animalPosition;

    public abstract void setAnimalPosition(ItemPosition animalPosition);

    public abstract ItemPosition getAnimalPosition();

    public abstract int getSpeed();

    public abstract String move();

    public String chooseDirection() {
        List<String> directionsList = List.of("Left", "Right", "Up", "Down", "Stand");

        return directionsList.get((int) (Math.random() * directionsList.size()));
    }
}
