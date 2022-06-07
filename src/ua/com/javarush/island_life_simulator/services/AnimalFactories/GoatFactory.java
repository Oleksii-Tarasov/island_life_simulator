package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.game_items.animals.herbivores.Goat;

@ReproductionController(minAmount = 140, maxAmount = 300)
public class GoatFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Goat(60, 140, 3, 10);
    }
}
