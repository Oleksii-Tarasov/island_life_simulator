package ua.com.javarush.island_life_simulator.items;

import ua.com.javarush.island_life_simulator.field.ItemPosition;

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
