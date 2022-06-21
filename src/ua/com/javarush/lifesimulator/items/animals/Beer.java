package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;

@NumberOfItemsOnField
public class Beer extends Animal implements Carnivores {
    private final double weight;
    private final int maxAmountOnCell;
    private final int speed;
    private final double fullSaturation;
    private final double weightLossPerDay;
    private double currentSaturation;
    private boolean alreadyWalked;
    private ItemPosition animalPosition;

    public Beer(double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
    }

    @Override
    public Animal clone() {
        return new Beer(weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
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
        return this.fullSaturation;
    }

    @Override
    public void reduceSaturation() {
        this.currentSaturation = this.currentSaturation - this.weightLossPerDay;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3B";
    }
}
