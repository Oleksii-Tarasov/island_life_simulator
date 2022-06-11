package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Boar;

@ReproductionController(minAmount = 50, maxAmount = 150)

public class BoarFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Boar();
    }
}
