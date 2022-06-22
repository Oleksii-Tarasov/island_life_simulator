package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;

@NumberOfItemsOnField
public class Goat extends Animal implements Herbivores {

    public Goat(String animalType, double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay) {
        this.animalType = animalType;
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
    }

    @Override
    public Animal clone() {
        return new Goat(animalType, weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }
}
