package ua.com.javarush.island_life_simulator.constants;

import ua.com.javarush.island_life_simulator.items.factories.animal_factories.*;

import java.util.HashMap;
import java.util.Map;

public final class ItemSettings {
    private ItemSettings(){}

    public static final AnimalFactory[] ANIMAL_FACTORIES = new AnimalFactory[]{new BeerFactory(), new BoaFactory(), new EagleFactory(),
            new FoxFactory(), new WolfFactory(), new BoarFactory(), new BuffaloFactory(), new CaterpillarFactory(),
            new DeerFactory(), new DuckFactory(), new GoatFactory(), new HorseFactory(), new MouseFactory(),
            new RabbitFactory(), new SheepFactory()};

    public static final Map<String, Integer> CHANCE_TO_EAT_SOMEONE = new HashMap<>() {{
        put("WolfHorse", 10);
        put("WolfDeer", 15);
        put("WolfRabbit", 60);
        put("WolfMouse", 80);
        put("WolfGoat", 60);
        put("WolfSheep", 70);
        put("WolfBoar", 15);
        put("WolfBuffalo", 10);
        put("WolfDuck", 15);

        put("BoaFox", 15);
        put("BoaRabbit", 20);
        put("BoaMouse", 40);
        put("BoaDuck", 10);

        put("FoxRabbit", 70);
        put("FoxMouse", 90);
        put("FoxDuck", 60);
        put("FoxCaterpillar", 40);

        put("BeerBoa", 80);
        put("BeerHorse", 40);
        put("BeerDeer", 80);
        put("BeerRabbit", 80);
        put("BeerMouse", 90);
        put("BeerGoat", 70);
        put("BeerSheep", 70);
        put("BeerBoar", 50);
        put("BeerBuffalo", 20);
        put("BeerDuck", 10);

        put("EagleFox", 10);
        put("EagleRabbit", 90);
        put("EagleMouse", 90);
        put("EagleDuck", 80);

        put("MouseCaterpillar", 90);

        put("BoarMouse", 50);
        put("BoarCaterpillar", 90);

        put("DuckCaterpillar", 90);
    }};
}
