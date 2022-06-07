package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.game_items.animals.herbivores.Deer;

@ReproductionController(minAmount = 20, maxAmount = 100)
public class DeerFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Deer(300, 20, 4, 50);
    }
}
