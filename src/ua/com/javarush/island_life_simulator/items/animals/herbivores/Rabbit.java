package ua.com.javarush.island_life_simulator.items.animals.herbivores;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.HerbivoreAnimal;

public class Rabbit extends HerbivoreAnimal {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private ItemPosition animalPosition;

    public Rabbit(double weight, int maxAmountOnCell, int speed, double fullSaturation) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.currentSaturation = fullSaturation;
    }

    @Override
    public void setAnimalPosition(ItemPosition animalPosition) {
        this.animalPosition = animalPosition;
    }

    @Override
    public ItemPosition getAnimalPosition() {
        return animalPosition;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public String move() {
        return chooseDirection();
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
