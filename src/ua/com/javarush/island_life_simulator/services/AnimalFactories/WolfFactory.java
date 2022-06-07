package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.game_items.animals.carnivores.Wolf;

@ReproductionController(minAmount = 30, maxAmount = 100)
public class WolfFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Wolf(50, 30, 3, 8);
    }
}
