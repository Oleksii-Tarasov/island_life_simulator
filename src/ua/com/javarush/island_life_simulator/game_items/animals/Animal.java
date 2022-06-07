package ua.com.javarush.island_life_simulator.game_items.animals;


import ua.com.javarush.island_life_simulator.game_field.ItemPosition;


public abstract class Animal {
    private int weight;
    private int maxAmountOnCell;
    private int speed;
    private int fullSaturation;
    private int currentSaturation;
    private ItemPosition animalPosition;

    public abstract void setAnimalPosition(ItemPosition animalPosition);

    public abstract ItemPosition getAnimalPosition();
}
