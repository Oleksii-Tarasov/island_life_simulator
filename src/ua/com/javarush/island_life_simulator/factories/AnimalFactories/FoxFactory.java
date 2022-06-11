package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Fox;

@ReproductionController(minAmount = 30, maxAmount = 90)
public class FoxFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Fox();
    }
}
