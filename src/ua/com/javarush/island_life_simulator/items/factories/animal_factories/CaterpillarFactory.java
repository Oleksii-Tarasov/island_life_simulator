package ua.com.javarush.island_life_simulator.items.factories.animal_factories;

import ua.com.javarush.island_life_simulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Caterpillar;

@NumberOfItemsOnField(minAmount = 500, maxAmount = 1000)
public class CaterpillarFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Caterpillar();
    }
}
