package ua.com.javarush.island_life_simulator.items.plants;

import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.BasicItem;

public class Plant extends BasicItem {
    private final double WEIGHT = 1;
    private final int MAX_AMOUNT_ON_CELL = 200;

    private double weight;
    private int maxAmountOnCell;
    ItemPosition plantPosition;

    public Plant() {
        this.weight = WEIGHT;
        this.maxAmountOnCell = MAX_AMOUNT_ON_CELL;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public ItemPosition getItemPosition() {
        return plantPosition;
    }

    public void setItemPosition(ItemPosition plantPosition) {
        this.plantPosition = plantPosition;
    }

    public String toString() {
        return "\uD83C\uDF3F";
    }
}
