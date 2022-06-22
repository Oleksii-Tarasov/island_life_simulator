package ua.com.javarush.lifesimulator.items;

import ua.com.javarush.lifesimulator.items.board.ItemPosition;

public abstract class BasicItem {
    protected int MaxAmountOnCell;
    protected double weight;
    protected ItemPosition itemPosition;

    public ItemPosition getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(ItemPosition itemPosition) {
        this.itemPosition = itemPosition;
    }

    public int getMaxAmountOnCell() {
        return MaxAmountOnCell;
    }

    public double getWeight() {
        return weight;
    }
}
