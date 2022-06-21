package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;

@NumberOfItemsOnField
public class Horse extends Animal implements Herbivores {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double weightLossPerDay;
    private double currentSaturation;
    private boolean alreadyWalked;
    private ItemPosition animalPosition;

    public Horse(double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
    }

    @Override
    public Animal clone() {
        return new Horse(weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
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
        this.currentSaturation = this.currentSaturation - weightLossPerDay;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0E";
    }
}
