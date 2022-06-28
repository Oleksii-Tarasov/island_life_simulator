package ua.com.javarush.lifesimulator.constants;

public final class GameSettings {
    private GameSettings(){}

    public static final int GAME_BOARD_WIDTH = 10;
    public static final int GAME_BOARD_HEIGHT = 150;

    public static final int PLANTS_MAX_AMOUNT = 2000;
    public static final int ANIMAL_PAIR = 2;
    public static final int CATACLYSM_DAY = 30;
    public static final int ONE_HUNDRED_PERCENT_CHANCE = 100;

    public static final String ANIMALS_PATH_CLASS = "ua.com.javarush.lifesimulator.items.animals.";
    public static final String ANIMAL_CHANCE_TO_EAT_FILE_PATH = "/resources/chanceToEatAnimal.json";
    public static final String ANIMAL_CHARACTERISTIC_FILE_PATH = "/resources/animalCharacteristics.json";
}
