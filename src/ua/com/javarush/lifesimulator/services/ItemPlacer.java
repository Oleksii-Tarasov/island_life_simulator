package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.items.Plant;

public class ItemPlacer {
    private final GameField gameField;

    public ItemPlacer(GameField gameField) {
        this.gameField = gameField;
    }

    public void putItemOnTheField(BasicItem item) {
        int x = item.getItemPosition().getX();
        int y = item.getItemPosition().getY();
        Cell cell = gameField.getCellFromField(y, x);

        if (item instanceof Animal animal) {
            cell.addAnimalToList(animal);
        }
        if (item instanceof Plant plant) {
            cell.addPlantToList(plant);
        }
    }
}
