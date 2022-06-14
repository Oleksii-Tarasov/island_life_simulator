package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.controllers.LifeController;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.services.ItemCreator;
import ua.com.javarush.island_life_simulator.services.ItemPlacer;

public class TestSimulation {

    /* класс для тестов */

    public void startTest() {
        ItemPlacer itemPlacer = new ItemPlacer();
        ItemCreator itemCreator = new ItemCreator(itemPlacer);

        /* создание острова */
        new GameField().createIsland();

        /* создание животных */
        itemCreator.createAnimals();

        /* создание растений */
        itemCreator.createPlants();

        /* стартуем дневной цикл жизни острова */
         new LifeController().startDayCycle();
    }
}
