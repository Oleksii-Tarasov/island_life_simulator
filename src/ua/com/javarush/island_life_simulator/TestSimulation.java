package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.controllers.LifeController;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.services.ItemCreator;

public class TestSimulation {
    public void startTest() {
        GameField islandField = new GameField();
        ItemCreator itemCreator = new ItemCreator();
        LifeController lifeController = new LifeController();

        islandField.createIsland();

        /* создание животных */
        itemCreator.createAnimals();

        /* размещение животных */
        itemCreator.putAnimalsOnTheField();
        islandField.printIsland();

        /* перемещение животных */
        lifeController.movingAnimals();
        islandField.printIsland();

        /* уменьшаем насыщенность у животных */
        lifeController.startDayCycle();
        islandField.printIsland();

        /* перемещение животных */
        lifeController.movingAnimals();
        islandField.printIsland();

        System.out.println("__________");

        /* уменьшаем насыщенность у животных */
        lifeController.startDayCycle();
        islandField.printIsland();

        /* перемещение животных */
        lifeController.movingAnimals();
        islandField.printIsland();
    }
}
