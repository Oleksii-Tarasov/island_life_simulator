package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Deer;

@ReproductionController(minAmount = 1, maxAmount = 3)
public class DeerFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Deer(300, 20, 4, 50);
    }
}
