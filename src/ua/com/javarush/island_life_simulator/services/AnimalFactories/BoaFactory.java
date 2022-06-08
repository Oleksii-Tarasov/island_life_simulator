package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.carnivores.Boa;

@ReproductionController(minAmount = 30, maxAmount = 100)
public class BoaFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Boa(15,30,1,3);
    }
}
