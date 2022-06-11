package ua.com.javarush.island_life_simulator.factories.AnimalFactories;

import ua.com.javarush.island_life_simulator.annotations.ReproductionController;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Rabbit;

@ReproductionController(minAmount = 150, maxAmount = 450)
public class RabbitFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Rabbit();
    }
}
