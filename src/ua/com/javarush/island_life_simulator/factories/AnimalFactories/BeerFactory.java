package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Beer;

@ReproductionController(minAmount = 1,maxAmount = 3)
public class BeerFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Beer(500, 30, 2, 80);
    }
}
