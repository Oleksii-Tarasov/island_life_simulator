package ua.com.javarush.island_life_simulator.constants;

import ua.com.javarush.island_life_simulator.factories.AnimalFactories.*;
import ua.com.javarush.island_life_simulator.factories.AnimalFactories.AnimalFactory;

import java.util.HashMap;
import java.util.Map;

public final class GameSettings {
    private GameSettings(){}

    public static final AnimalFactory[] animalFactories = new AnimalFactory[]{new BeerFactory(), new BoaFactory(), new EagleFactory(),
            new FoxFactory(), new WolfFactory(), new BoarFactory(), new BuffaloFactory(), new CaterpillarFactory(),
            new DeerFactory(), new DuckFactory(), new GoatFactory(), new HorseFactory(), new MouseFactory(),
            new RabbitFactory(), new SheepFactory()};

    public static final Map<String, Integer> chanceToEatSomeone = new HashMap<>() {{
        put("WolfHorse", 10);
        put("WolfDeer", 15);
        put("WolfRabbit", 60);
        put("WolfMouse", 80);
        put("WolfGoat", 60);
        put("WolfSheep", 70);

        put("BoaFox", 15);
        put("BoaRabbit", 20);
        put("BoaMouse", 40);

        put("FoxRabbit", 70);
        put("FoxMouse", 90);

        put("BeerBoa", 80);
        put("BeerHorse", 40);
        put("BeerDeer", 80);
        put("BeerRabbit", 80);
        put("BeerMouse", 90);
        put("BeerGoat", 70);
        put("BeerSheep", 70);

        put("EagleFox", 10);
        put("EagleRabbit", 90);
        put("EagleMouse", 90);

        put("BoarMouse", 50);
    }};

    public static final int ISLAND_WIDTH = 5;
    public static final int ISLAND_HEIGHT = 5;

    public static final String OPEN_CELL = "[";
    public static final String EMPTY_CELL = "  ";
    public static  final String CLOSE_CELL = "] ";
    public static final String DELIMITER = " ";

    public static final int ONE_HUNDRED_PERCENT_CHANCE = 100;
}
