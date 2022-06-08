package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Buffalo;

@ReproductionController(minAmount = 10, maxAmount = 100)
public class BuffaloFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Buffalo(700, 10, 3, 100);
    }
}
