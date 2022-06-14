package ua.com.javarush.island_life_simulator.items.animals.herbivores;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.interfaces.Carnivores;
import ua.com.javarush.island_life_simulator.items.animals.interfaces.Herbivores;

public class Mouse extends Animal implements Carnivores, Herbivores {
    private final static double WEIGHT = 0.05;
    private final static int MAX_AMOUNT_ON_CELL = 500;
    private final static int SPEED = 1;
    private final static double FULL_SATURATION = 0.01;
    private final static double WEIGHT_LOSS_PER_DAY = 0.0015;

    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private boolean alreadyWalked;
    private ItemPosition animalPosition;

    public Mouse() {
        this.weight = WEIGHT;
        this.maxAmountOnCell = MAX_AMOUNT_ON_CELL;
        this.speed = SPEED;
        this.fullSaturation = FULL_SATURATION;
        this.currentSaturation = FULL_SATURATION;
        this.alreadyWalked = false;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public int getMaxAmountOnCell() {
        return this.maxAmountOnCell;
    }

    @Override
    public void setItemPosition(ItemPosition animalPosition) {
        this.animalPosition = animalPosition;
    }

    @Override
    public ItemPosition getItemPosition() {
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
    public void setCurrentSaturation(double currentSaturation) {
        this.currentSaturation = currentSaturation;
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
        return "\uD83D\uDC01";
    }
}
