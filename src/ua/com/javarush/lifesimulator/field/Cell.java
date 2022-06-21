package ua.com.javarush.lifesimulator.field;

import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;


public class Cell {
    private List<Animal> animalList = new ArrayList<>();
    private List<Plant> plantList = new ArrayList<Plant>();
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
