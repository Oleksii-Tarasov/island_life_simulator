package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.List;

public class ItemRemover {
    public void removeAnimals(List<Animal> animalList, List<Animal> removingAnimalList) {
        animalList.removeAll(removingAnimalList);
    }

    public void removePlants(List<Plant> plantList, List<Plant> removingPlantList) {
        plantList.removeAll(removingPlantList);
    }
}
