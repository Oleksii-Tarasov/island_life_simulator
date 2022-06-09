package ua.com.javarush.island_life_simulator.field;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;

public class Cell {
    private List<Animal> animalList = new ArrayList<>();
    private List<Plant> plantsList;
    private final ItemPosition cellPosition;

    public Cell(ItemPosition cellPosition) {
        this.cellPosition = cellPosition;
    }

    public void addAnimalToList(Animal animal) {
        this.animalList.add(animal);
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    private String getItemsForPrint() {
        StringBuilder itemsForPrint = new StringBuilder();

        if (animalList.isEmpty()) {
            return EMPTY_CELL;
        }

        return itemsForPrint.append(visualizeItems(animalList)).toString();
    }

    private String visualizeItems(List<? extends Animal> animalList) {
        StringBuilder items = new StringBuilder();
        List<String> listOfClasses = animalList.stream().map(Object::toString).toList();
        Set<String> setOfClasses = new HashSet<>(listOfClasses);

        for (String animal : setOfClasses) {
            int countAnimal = Collections.frequency(listOfClasses, animal);
            if (countAnimal == 1) {
                items.append(animal);
            }
            else {
                items.append(DELIMITER).append(animal).append("x").append(countAnimal).append(DELIMITER);
            }

        }

        return items.toString();
    }

    @Override
    public String toString() {
        return OPEN_CELL + getItemsForPrint() + CLOSE_CELL;
    }
}
