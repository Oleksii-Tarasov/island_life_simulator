package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.factories.AnimalFactories.*;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.lang.reflect.InvocationTargetException;

public class ItemCreator {
    private final ItemPlacer itemPlacer = new ItemPlacer();
    public static final AnimalFactory[] testFactory = new AnimalFactory[]{new BeerFactory(), new DeerFactory(), new MouseFactory(), new WolfFactory()};

    public void createAnimals(){
        for (AnimalFactory animalFactory : testFactory) {
            ReproductionController animalAmount = animalFactory.getClass().getAnnotation(ReproductionController.class);
            int minAmount = animalAmount.minAmount();
            int maxAmount = animalAmount.maxAmount();
            int amount = ((int)(Math.random()*(maxAmount - minAmount))) + minAmount;

            for (int i = 0; i < amount; i++) {
                Animal animal = animalFactory.create();
                animal.setAnimalPosition(new ItemPosition());
                itemPlacer.putItemsOnTheField(animal);
            }
        }
    }

    public void createNewbornAnimal(Class<? extends Animal> animalClass, int y, int x) {
        try {
            Animal newAnimal = (Animal) Class.forName(animalClass.getName()).getConstructor().newInstance();
            newAnimal.setAnimalPosition(new ItemPosition(x, y));
            itemPlacer.putItemsOnTheField(newAnimal);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            System.out.println(e);
        }
    }

    public void createPlants(){
        for (int i = 0; i < 100; i++) {
            Plant plant = new Plant();
            plant.setPlantPosition(new ItemPosition());

            itemPlacer.putItemsOnTheField(plant);
        }
    }

}
