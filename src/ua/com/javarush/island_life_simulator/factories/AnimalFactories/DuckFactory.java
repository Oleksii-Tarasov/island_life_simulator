package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Duck;

@ReproductionController(minAmount = 200, maxAmount = 600)
public class DuckFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Duck();
    }
}
