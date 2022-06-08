package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Caterpillar;

@ReproductionController(minAmount = 1000, maxAmount = 2000)
public class CaterpillarFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Caterpillar(0.01, 1000, 0, 0);
    }
}
