package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Beer;
import ua.com.javarush.island_life_simulator.services.ItemMover;

public class TestSimulation {
    public void startTest(){
        GameField islandField = new GameField();
        islandField.createIsland();

        /* проверка движения животного */
        Beer beer = new Beer(500, 5, 2, 80);
        ItemPosition position = new ItemPosition();
        position.setX(1);
        position.setY(1);
        beer.setAnimalPosition(position);
        Cell cell = GameField.islandField[beer.getAnimalPosition().getY()][beer.getAnimalPosition().getX()];
        cell.addAnimalToListsByType(beer);
        ItemMover itemMover = new ItemMover();
        itemMover.moveItem(beer);

        /* печатаем остров */
        islandField.printIsland();
    }
}
