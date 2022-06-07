package ua.com.javarush.island_life_simulator.constants;

import ua.com.javarush.island_life_simulator.services.AnimalFactories.*;
import ua.com.javarush.island_life_simulator.services.AnimalFactories.AnimalFactory;

public final class GameSettings {
    private GameSettings(){}

    public static final AnimalFactory[] animalFactories = new AnimalFactory[]{new BeerFactory(), new BoaFactory(), new EagleFactory(),
            new FoxFactory(), new WolfFactory(), new BoarFactory(), new BuffaloFactory(), new CaterpillarFactory(),
            new DeerFactory(), new DuckFactory(), new GoatFactory(), new HorseFactory(), new MouseFactory(),
            new RabbitFactory(), new SheepFactory()};

    public static final int ISLAND_WIDTH = 10;
    public static final int ISLAND_HEIGHT = 200;

    public static final String OPEN_CELL = "[";
    public static final String EMPTY_CELL = "  ";
    public static  final String CLOSE_CELL = "] ";
    public static final String DELIMITER = " ";
}
