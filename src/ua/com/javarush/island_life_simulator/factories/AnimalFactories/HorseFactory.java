package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Horse;

@ReproductionController(minAmount = 20, maxAmount = 60)
public class HorseFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Horse();
    }
}
