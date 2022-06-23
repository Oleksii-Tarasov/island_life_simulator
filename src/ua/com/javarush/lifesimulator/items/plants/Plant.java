package ua.com.javarush.lifesimulator.items.plants;

import ua.com.javarush.lifesimulator.items.board.ItemPosition;
import ua.com.javarush.lifesimulator.items.BasicItem;

public class Plant extends BasicItem {
    private final double WEIGHT = 1;
    private final int MAX_AMOUNT_ON_CELL = 200;
    private final String ICON = "\uD83C\uDF3F";

    private double weight;
    private int maxAmountOnCell;
    private String icon;
    private ItemPosition plantPosition;

    public Plant() {
        this.weight = WEIGHT;
        this.maxAmountOnCell = MAX_AMOUNT_ON_CELL;
        this.icon = ICON;
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

    @Override
    public String getIcon() {
        return icon;
    }
}
