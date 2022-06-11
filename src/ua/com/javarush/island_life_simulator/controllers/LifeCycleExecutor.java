package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.ItemCreator;
import ua.com.javarush.island_life_simulator.services.ItemMover;
import ua.com.javarush.island_life_simulator.services.ItemRemover;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LifeCycleExecutor {
    private final ItemMover itemMover = new ItemMover();
    private final ItemCreator itemCreator = new ItemCreator();
    private final ItemRemover itemRemover = new ItemRemover();
    private final ItemStatusChecker itemStatusChecker = new ItemStatusChecker();

    public void reduceSaturation(List<Animal> animalListFromCell) {
        animalListFromCell.forEach(Animal::reduceSaturation);
    }

    public void starvingToDeath(List<Animal> animalListFromCell) {
        animalListFromCell.removeIf(animal -> animal.getCurrentSaturation() <= 0);
    }

    public void movingAnimals(List<Animal> animalListFromCell) {
        itemMover.moveItems(animalListFromCell);
    }

    public void resetWalkStatus(List<Animal> animalListFromCell) {
        animalListFromCell.forEach(animal -> animal.setAlreadyWalked(false));
    }

    public void reproduction(List<Animal> animalListFromCell, int y, int x) {
        Map<Class<? extends Animal>, Long> numberOfClassesInList = animalListFromCell.stream()
                .collect(groupingBy(Animal::getClass, Collectors.counting()));

        for (Map.Entry<Class<? extends Animal>, Long> animalClassFromMap : numberOfClassesInList.entrySet()) {
            long counter = animalClassFromMap.getValue();
            if (counter >= 2) {
                Class<? extends Animal> animalClass = animalClassFromMap.getKey();
                for (int i = 0; i < counter / 2; i++) {
                    itemCreator.createNewbornAnimal(animalClass, y, x);
                }
            }
        }
    }

    public void eat(List<Animal> animalListFromCell) {
        List<Animal> eatenAnimalList = new ArrayList<>();

        for (int i = 0; i < animalListFromCell.size(); i++) {
            String firstAnimal = animalListFromCell.get(i).getClass().getSimpleName();
            for (int j = 0; j < animalListFromCell.size(); j++) {
                Animal animal = animalListFromCell.get(j);

                if (eatenAnimalList.contains(animal) || !(itemStatusChecker.isAnimalHungry(animal))) {
                    continue;
                }

                String secondAnimal = animal.getClass().getSimpleName();
                String animalsPair = firstAnimal + secondAnimal;
                boolean isEaten = itemStatusChecker.isEaten(animalsPair);

                if (isEaten) {
                    eatenAnimalList.add(animalListFromCell.get(j));
                }
            }
        }
        itemRemover.removeItems(animalListFromCell, eatenAnimalList);
    }
}
