package ua.com.javarush.lifesimulator.factories;

import ua.com.javarush.lifesimulator.items.Animal;

public class AnimalFactory {
    Animal animal;

    public AnimalFactory(Animal animal) {
        createPrototype(animal);
    }

    public void createPrototype(Animal animal) {
        this.animal = animal;
    }

    public Animal makeClone() {
        return animal.clone();
    }
}
