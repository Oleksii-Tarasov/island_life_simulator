package ua.com.javarush.island_life_simulator.items.animals;

import ua.com.javarush.island_life_simulator.field.ItemPosition;

import java.util.List;

public abstract class Animal {
    private int weight;
    private int maxAmountOnCell;
    private int speed;
    private int fullSaturation;
    private double currentSaturation;
    private double weightLossPerDay;
    private ItemPosition animalPosition;
    private boolean alreadyWalked;

    public boolean isAlreadyWalked(){
        return this.alreadyWalked;
    }

    public void setAlreadyWalked(boolean alreadyWalked){
        this.alreadyWalked = alreadyWalked;
    }

    public void setAnimalPosition(ItemPosition animalPosition) {
        this.animalPosition = animalPosition;
    }

    public ItemPosition getAnimalPosition() {
        return this.animalPosition;
    }

    public int getSpeed() {
        return this.speed;
    }

    public abstract void reduceSaturation();

    public double getCurrentSaturation() {
        return this.currentSaturation;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public String chooseDirection() {
        List<String> directionsList = List.of("Left", "Right", "Up", "Down", "Stand");

        return directionsList.get((int) (Math.random() * directionsList.size()));
    }
}
