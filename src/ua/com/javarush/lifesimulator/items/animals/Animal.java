package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.interfaces.BasicItemCloneable;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;

import java.util.Objects;

public abstract class Animal extends BasicItem implements BasicItemCloneable {
    protected String animalType;
    protected double weight;
    protected int maxAmountOnCell;
    protected int speed;
    protected double fullSaturation;
    protected double currentSaturation;
    protected double weightLossPerDay;
    protected ItemPosition animalPosition;
    protected boolean alreadyWalked;
    protected String icon;

    public abstract Animal clone();

    public double getWeight() {
        return weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public boolean isAlreadyWalked() {
        return this.alreadyWalked;
    }

    public void setAlreadyWalked(boolean alreadyWalked) {
        this.alreadyWalked = alreadyWalked;
    }

    public void setItemPosition(ItemPosition animalPosition) {
        this.animalPosition = animalPosition;
    }

    public ItemPosition getItemPosition() {
        return this.animalPosition;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void reduceSaturation() {
        this.currentSaturation = this.currentSaturation - this.weightLossPerDay;
    }

    public void setCurrentSaturation(double currentSaturation) {
        this.currentSaturation = currentSaturation;
    }

    public double getCurrentSaturation() {
        return this.currentSaturation;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public double getWeightLossPerDay() {
        return weightLossPerDay;
    }

    public ItemPosition getAnimalPosition() {
        return animalPosition;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalType, animal.animalType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalType);
    }
}
