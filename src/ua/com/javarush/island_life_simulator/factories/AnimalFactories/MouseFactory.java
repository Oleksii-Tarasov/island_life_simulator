package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Mouse;

@ReproductionController(minAmount = 1, maxAmount = 10)
//@ReproductionController(minAmount = 500, maxAmount = 1500)
public class MouseFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Mouse();
    }
}
