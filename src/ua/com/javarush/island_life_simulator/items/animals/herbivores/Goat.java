package ua.com.javarush.island_life_simulator.items.animals.herbivores;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

public class Goat extends Animal {
    private final static double WEIGHT = 60;
    private final static int MAX_AMOUNT_ON_CELL = 140;
    private final static int SPEED = 3;
    private final static double FULL_SATURATION = 10;
    private final static double WEIGHT_LOSS_PER_DAY = 2;

    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private boolean alreadyWalked;
    private ItemPosition animalPosition;

    public Goat() {
        this.weight = WEIGHT;
        this.maxAmountOnCell = MAX_AMOUNT_ON_CELL;
        this.speed = SPEED;
        this.fullSaturation = FULL_SATURATION;
        this.currentSaturation = FULL_SATURATION;
        this.alreadyWalked = false;
    }

    @Override
    public void setAnimalPosition(ItemPosition animalPosition) {
        this.animalPosition = animalPosition;
    }

    @Override
    public ItemPosition getAnimalPosition() {
        return this.animalPosition;
    }

    @Override
    public void setAlreadyWalked(boolean alreadyWalked) {
        this.alreadyWalked = alreadyWalked;
    }

    @Override
    public boolean isAlreadyWalked() {
        return alreadyWalked;
    }

    @Override
    public double getCurrentSaturation() {
        return this.currentSaturation;
    }

    @Override
    public double getFullSaturation() {
        return fullSaturation;
    }

    @Override
    public void reduceSaturation() {
        this.currentSaturation = this.currentSaturation - WEIGHT_LOSS_PER_DAY;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }
}
