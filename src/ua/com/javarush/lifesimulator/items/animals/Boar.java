package ua.com.javarush.lifesimulator.items.animals;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;

@NumberOfItemsOnField
public class Boar extends Animal implements Carnivores, Herbivores {

    public Boar(String animalType, double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay, String icon) {
        this.animalType = animalType;
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
        this.icon = icon;
    }

    @Override
    public Animal clone() {
        return new Boar(animalType,weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay, icon);
    }
}
