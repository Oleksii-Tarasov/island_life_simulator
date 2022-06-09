package ua.com.javarush.island_life_simulator.items.animals.carnivores;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.CarnivoreAnimal;

public class Fox extends CarnivoreAnimal {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private double weightLossPerDay = 5;
    private ItemPosition animalPosition;
    private boolean alreadyWalked;

    public Fox(double weight, int maxAmountOnCell, int speed, double fullSaturation) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
    }

    @Override
    public void reduceSaturation() {
        this.currentSaturation = this.currentSaturation - weightLossPerDay;
    }

    @Override
    public double getCurrentSaturation() {
        return this.currentSaturation;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
