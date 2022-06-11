package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Eagle;

@ReproductionController(minAmount = 20, maxAmount = 60)
public class EagleFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Eagle();
    }
}
