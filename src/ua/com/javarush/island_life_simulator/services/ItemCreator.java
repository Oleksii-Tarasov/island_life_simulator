package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_field.Cell;
import ua.com.javarush.island_life_simulator.game_field.ItemPosition;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.AnimalFactories.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.animalFactories;
import static ua.com.javarush.island_life_simulator.game_field.IslandField.islandField;

public class ItemCreator {
    private static final List<Animal> animalList = new ArrayList<>();

    public void createAnimals(){
        for (AnimalFactory animalFactory : animalFactories) {
            ReproductionController animalAmount = animalFactory.getClass().getAnnotation(ReproductionController.class);
            int minAmount = animalAmount.minAmount();
            int maxAmount = animalAmount.maxAmount();
            int amount = ((int)(Math.random()*(maxAmount - minAmount))) + minAmount;

            for (int i = 0; i < amount; i++) {
                Animal animal = animalFactory.create();
                animalList.add(animal);
            }
        }
    }

    public void putAnimalsOnTheField() {
        for (Animal animal : animalList) {
            animal.setAnimalPosition(new ItemPosition());
            Cell cell = islandField[animal.getAnimalPosition().getY()][animal.getAnimalPosition().getX()];
            cell.fillAnimalListsByType(animal);
        }
    }
}
