package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Sheep;

@ReproductionController(minAmount = 140, maxAmount = 300)
public class SheepFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Sheep(70, 140, 3, 15);
    }
}
