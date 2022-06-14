package ua.com.javarush.island_life_simulator.items.animals;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.BasicItem;

import java.util.List;

public abstract class Animal extends BasicItem {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private double weightLossPerDay;
    private ItemPosition animalPosition;
    private boolean alreadyWalked;

    public double getWeight() {
        return weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public boolean isAlreadyWalked(){
        return this.alreadyWalked;
    }

    public void setAlreadyWalked(boolean alreadyWalked){
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

    public abstract void reduceSaturation();

    public void setCurrentSaturation(double currentSaturation) {
        this.currentSaturation = currentSaturation;
    }

    public double getCurrentSaturation() {
        return this.currentSaturation;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }
}
