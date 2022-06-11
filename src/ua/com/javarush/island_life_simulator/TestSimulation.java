package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.controllers.LifeController;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.services.ItemCreator;

public class TestSimulation {
    public void startTest() {
        GameField islandField = new GameField();
        ItemCreator itemCreator = new ItemCreator();
        LifeController lifeController = new LifeController();

        /* создание острова */
        islandField.createIsland();

        /* создание животных */
        itemCreator.createAnimals();

        islandField.printIsland();

        /* стартуем дневной цикл жизни острова */
        lifeController.startDayCycle();

        islandField.printIsland();
    }
}
