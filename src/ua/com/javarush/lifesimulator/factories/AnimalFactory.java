package ua.com.javarush.lifesimulator.factories;

import ua.com.javarush.lifesimulator.items.Animal;

import java.lang.reflect.InvocationTargetException;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;

public class AnimalFactory {
    Animal animal;

    public AnimalFactory(Class<? extends Animal> animalType) {
        createPrototype(animalType);
    }

    public void createPrototype(Class<? extends Animal> animalType) {
        try {
            this.animal = (Animal) Class.forName(animalType.getName()).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(UNABLE_TO_PROCESS_CLASS);
        }
    }

    public Animal makeClone() {
        return animal.clone();
    }
}
