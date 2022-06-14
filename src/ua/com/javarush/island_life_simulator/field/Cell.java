package ua.com.javarush.island_life_simulator.field;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;

public class Cell {
    private List<Animal> animalList = new ArrayList<>();
    private List<Plant> plantList = new ArrayList<>();
    private final ItemPosition cellPosition;

    public Cell(ItemPosition cellPosition) {
        this.cellPosition = cellPosition;
    }

    public ItemPosition getCellPosition() {
        return cellPosition;
    }

    public void addAnimalToList(Animal animal) {
        this.animalList.add(animal);
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void addPlantToList(Plant plant) {
        this.plantList.add(plant);
    }

    public List<Plant> getPlantList() {
        return plantList;
    }
}
