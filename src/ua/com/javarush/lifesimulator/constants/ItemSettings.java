package ua.com.javarush.lifesimulator.constants;

import ua.com.javarush.lifesimulator.items.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ItemSettings {
    private ItemSettings(){}

    public static final List<Class<? extends Animal>> ANIMAL_LIST = List.of(Beer.class, Boa.class, Boar.class, Buffalo.class, Caterpillar.class,
            Deer.class, Duck.class, Eagle.class, Fox.class, Goat.class, Horse.class, Mouse.class, Rabbit.class, Sheep.class, Wolf.class);

    public static final List<Class<? extends Animal>> ANIMAL_LIST_FOR_TEST = List.of(Beer.class, Deer.class, Mouse.class, Wolf.class);

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
