package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.island_life_simulator.items.factories.animal_factories.*;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.lang.reflect.InvocationTargetException;

import static ua.com.javarush.island_life_simulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;
import static ua.com.javarush.island_life_simulator.constants.ItemSettings.ANIMAL_FACTORIES;

public class ItemCreator {
    private ItemPlacer itemPlacer;
//    public static final AnimalFactory[] testFactory = new AnimalFactory[]{new BeerFactory(), new DeerFactory(), new MouseFactory(), new WolfFactory()};

    public ItemCreator(ItemPlacer itemPlacer) {
        this.itemPlacer = itemPlacer;
    }

    public void createAnimals(){
        for (AnimalFactory animalFactory : ANIMAL_FACTORIES) {
            NumberOfItemsOnField animalAmount = animalFactory.getClass().getAnnotation(NumberOfItemsOnField.class);
            int minAmount = animalAmount.minAmount();
            int maxAmount = animalAmount.maxAmount();
            int amount = ((int)(Math.random()*(maxAmount - minAmount))) + minAmount;

            for (int i = 0; i < amount; i++) {
                Animal animal = animalFactory.create();
                boolean isPositionFind = false;

                do {
                    animal.setItemPosition(new ItemPosition());
                    if (ItemConditionsChecker.canAddItemToCell(animal)) {
                        isPositionFind = true;
                    }
                } while (!isPositionFind);

                itemPlacer.putItemOnTheField(animal);
            }
        }
    }

    public void createNewbornAnimal(Class<? extends Animal> animalClass, ItemPosition itemPosition) {
        try {
            Animal newAnimal = (Animal) Class.forName(animalClass.getName()).getConstructor().newInstance();
            newAnimal.setItemPosition(itemPosition);
            itemPlacer.putItemOnTheField(newAnimal);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            System.out.println(UNABLE_TO_PROCESS_CLASS);
        }
    }

    public void createPlants(){
        for (int i = 0; i < 500; i++) {
            Plant plant = new Plant();
            boolean isPositionFind = false;

            do {
                plant.setItemPosition(new ItemPosition());
                if (ItemConditionsChecker.canAddItemToCell(plant)) {
                    isPositionFind = true;
                }
            } while (!isPositionFind);

            itemPlacer.putItemOnTheField(plant);
        }
    }
}
