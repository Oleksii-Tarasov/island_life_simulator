package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.game_items.animals.herbivores.Duck;

@ReproductionController(minAmount = 200, maxAmount = 400)
public class DuckFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Duck(1, 200, 4, 0.15);
    }
}
