package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Eagle;

@ReproductionController(minAmount = 20, maxAmount = 100)
public class EagleFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Eagle(6, 20, 3, 1);
    }
}
