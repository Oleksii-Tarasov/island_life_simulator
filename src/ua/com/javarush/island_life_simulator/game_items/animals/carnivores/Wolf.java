package ua.com.javarush.island_life_simulator.game_items.animals.carnivores;

import ua.com.javarush.island_life_simulator.game_field.ItemPosition;
import ua.com.javarush.island_life_simulator.game_items.animals.CarnivoreAnimal;

public class Wolf extends CarnivoreAnimal {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double currentSaturation;
    private ItemPosition animalPosition;

    public Wolf(double weight, int maxAmountOnCell, int speed, double fullSaturation) {
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
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
