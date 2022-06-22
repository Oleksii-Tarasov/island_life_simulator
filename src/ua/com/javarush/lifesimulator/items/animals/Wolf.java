package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;

@NumberOfItemsOnField
public class Wolf extends Animal implements Carnivores {

    public Wolf(double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
    }

    @Override
    public Animal clone() {
        return new Wolf(weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
