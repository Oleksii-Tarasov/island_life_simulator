package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.plants.Plant;

public class ItemPlacer {
    public void putItemOnTheField(GameBoard gameBoard, BasicItem item) {
        int x = item.getItemPosition().getX();
        int y = item.getItemPosition().getY();
        Cell cell = gameBoard.getCell(y, x);

        if (item instanceof Animal animal) {
            cell.addAnimalToList(animal);
        }
        if (item instanceof Plant plant) {
            cell.addPlantToList(plant);
        }
    }
}
