package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Beer;

@ReproductionController(minAmount = 1,maxAmount = 10)
//@ReproductionController(minAmount = 1,maxAmount = 20)
public class BeerFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Beer();
    }
}
