package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;

@NumberOfItemsOnField
public class Duck extends Animal implements Carnivores, Herbivores {

    public Duck(String animalType, double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay) {
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
        return new Duck(animalType, weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
