package ua.com.javarush.lifesimulator.items;

import ua.com.javarush.lifesimulator.field.ItemPosition;

public abstract class BasicItem {
    private int MaxAmountOnCell;
    private ItemPosition itemPosition;

    public ItemPosition getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(ItemPosition itemPosition) {
        this.itemPosition = itemPosition;
    }

    public int getMaxAmountOnCell() {
        return MaxAmountOnCell;
    }
}
