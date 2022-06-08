package ua.com.javarush.island_life_simulator.field;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.CarnivoreAnimal;
import ua.com.javarush.island_life_simulator.items.animals.HerbivoreAnimal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.*;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.*;

public class Cell {
    private List<CarnivoreAnimal> carnivoreAnimalsList = new ArrayList<>();
    private List<HerbivoreAnimal> herbivoreAnimalsList = new ArrayList<>();
    private List<Plant> plantsList;
    private ItemPosition cellPosition;

    public Cell(ItemPosition cellPosition) {
        this.cellPosition = cellPosition;
    }

    public void addAnimalToListsByType(Animal animal) {
        if (animal instanceof CarnivoreAnimal) {
            carnivoreAnimalsList.add((CarnivoreAnimal) animal);
        }
        else {
            herbivoreAnimalsList.add((HerbivoreAnimal) animal);
        }
    }

    public void removeAnimalFromList(Animal animal) {
        carnivoreAnimalsList.remove(animal);
    }
    
    private String getItemsForPrint() {
        StringBuilder itemsForPrint = new StringBuilder();
        if (carnivoreAnimalsList.isEmpty() && herbivoreAnimalsList.isEmpty()) {
            return EMPTY_CELL;
        }
        
        if (!carnivoreAnimalsList.isEmpty()) {
            itemsForPrint.append(visualizeItems(carnivoreAnimalsList));
        }

        if (!herbivoreAnimalsList.isEmpty()) {
            itemsForPrint.append(visualizeItems(herbivoreAnimalsList));
        }

        return itemsForPrint.toString();
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
