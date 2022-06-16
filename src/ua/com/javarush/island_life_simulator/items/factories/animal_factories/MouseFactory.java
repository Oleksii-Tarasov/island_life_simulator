package ua.com.javarush.island_life_simulator.items.factories.animal_factories;

import ua.com.javarush.island_life_simulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.herbivores.Mouse;

@NumberOfItemsOnField(minAmount = 1, maxAmount = 5)
public class MouseFactory implements AnimalFactory {
    @Override
    public Animal create() {
        return new Mouse();
    }
}
