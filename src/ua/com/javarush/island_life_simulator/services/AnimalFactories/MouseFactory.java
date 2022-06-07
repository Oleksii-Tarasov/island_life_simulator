package ua.com.javarush.island_life_simulator.services.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.game_items.animals.Animal;
import ua.com.javarush.island_life_simulator.game_items.animals.herbivores.Mouse;

@ReproductionController(minAmount = 500, maxAmount = 1000)
public class MouseFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Mouse(0.05, 500, 1, 0.01);
    }
}
