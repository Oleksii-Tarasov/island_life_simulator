package ua.com.javarush.island_life_simulator.items.factories.animal_factories;

import ua.com.javarush.island_life_simulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Wolf;

@NumberOfItemsOnField()
public class WolfFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Wolf();
    }
}
