package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.factories.AnimalFactories.*;
import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.ArrayList;
import java.util.List;

import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class ItemCreator {
    public static final List<Animal> animalsFullList = new ArrayList<>();

    public static final AnimalFactory[] testFactory = new AnimalFactory[]{new BeerFactory(), new DeerFactory(), new MouseFactory(), new WolfFactory()};

    public void createAnimals(){
        for (AnimalFactory animalFactory : testFactory) {
            ReproductionController animalAmount = animalFactory.getClass().getAnnotation(ReproductionController.class);
            int minAmount = animalAmount.minAmount();
            int maxAmount = animalAmount.maxAmount();
            int amount = ((int)(Math.random()*(maxAmount - minAmount))) + minAmount;

            for (int i = 0; i < amount; i++) {
                Animal animal = animalFactory.create();
                animalsFullList.add(animal);
            }
        }
    }

    public void putAnimalsOnTheField() {
        for (Animal animal : animalsFullList) {
            animal.setAnimalPosition(new ItemPosition());
            Cell cell = islandField[animal.getAnimalPosition().getY()][animal.getAnimalPosition().getX()];
            cell.addAnimalToList(animal);
        }
    }
}
