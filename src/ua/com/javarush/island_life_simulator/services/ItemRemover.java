package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.List;

public class ItemRemover {
    public void removeItems(List<Animal> animalList, List<Animal> removingAnimalList) {
        animalList.removeAll(removingAnimalList);
    }
}
