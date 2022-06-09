package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.ItemMover;

import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;
import static ua.com.javarush.island_life_simulator.field.GameField.islandField;

public class LifeController {
    private final ItemMover itemMover = new ItemMover();

    public void startDayCycle() {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = islandField[y][x];
                List<Animal> animalsList = cell.getAnimalList();
                reduceSaturation(animalsList);
                starvingToDeath(animalsList);
                resetWalkStatus(animalsList);
                cell.setAnimalList(animalsList);
            }
        }
        /* test */
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = islandField[y][x];
                List<Animal> animalsList = cell.getAnimalList();
                for (Animal animal : animalsList) {
                    System.out.println(animal + " " + animal.getCurrentSaturation());
                }
            }
        }
    }

    public void movingAnimals(){
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = islandField[y][x];
                List<Animal> animalList = cell.getAnimalList();
                itemMover.moveItems(animalList);
            }
        }
    }

    private void reduceSaturation(List<Animal> animalList){
        animalList.forEach(Animal::reduceSaturation);
    }

    private void starvingToDeath(List<Animal> animalList) {
        animalList.removeIf(animal -> animal.getCurrentSaturation() <= 0);
    }

    private void resetWalkStatus(List<Animal> animalList) {
        animalList.forEach(animal -> animal.setAlreadyWalked(false));
    }
}
