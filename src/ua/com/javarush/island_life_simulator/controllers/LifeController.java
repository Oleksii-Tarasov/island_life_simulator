package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.ItemPrinter;

import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class LifeController {
    private final LifeCycleExecutor lifeCycleExecutor = new LifeCycleExecutor();
    ItemPrinter itemPrinter = new ItemPrinter();

    public void startDayCycle() {

        itemPrinter.printGameField();

        executeDayPhase(DailyPhase.STARVATION);
        itemPrinter.printGameField();
        executeDayPhase(DailyPhase.MOVE);
        itemPrinter.printGameField();
        executeDayPhase(DailyPhase.EAT);
        itemPrinter.printGameField();
        executeDayPhase(DailyPhase.REPRODUCE);
        itemPrinter.printGameField();

    }

    public void executeDayPhase(DailyPhase phase) {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = islandField[y][x];
                List<Animal> animalsList = cell.getAnimalList();
                if (animalsList.size() != 0) {
                    switch (phase) {
                        case STARVATION -> {
                            lifeCycleExecutor.reduceSaturation(animalsList);
                            lifeCycleExecutor.starvingToDeath(animalsList);
                        }
                        case MOVE -> {
                            lifeCycleExecutor.movingAnimals(animalsList);
                            lifeCycleExecutor.resetWalkStatus(animalsList);
                        }
                        case EAT -> {
                            if (cell.getPlantList().size() != 0) {
                                lifeCycleExecutor.eatPlants(animalsList, cell.getPlantList());
                            }
                            lifeCycleExecutor.eatOtherAnimals(animalsList);
                        }
                        case REPRODUCE -> lifeCycleExecutor.reproduction(animalsList, y, x);
                    }
                }
            }
        }
    }
}
