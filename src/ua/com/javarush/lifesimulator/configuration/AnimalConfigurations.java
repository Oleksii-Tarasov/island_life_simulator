package ua.com.javarush.lifesimulator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_LOAD_CONFIGURATION_FILE;
import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;

public class AnimalConfigurations {
    private static final String ANIMALS_PATH_CLASS = "ua.com.javarush.lifesimulator.items.animals.";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File gameSettingsFile = new File("resources/gameSettings.json");
    private final File animalChanceToEatFile = new File("resources/chanceToEatAnimal.json");
    private final File animalCharacteristicFile = new File("resources/animalCharacteristics.json");

    public HashMap getAnimalChanceToEatMap() {
        HashMap animalChanceToEatMap = new HashMap();
        try {
            animalChanceToEatMap = objectMapper.readValue(animalChanceToEatFile, HashMap.class);
        } catch (IOException e) {
            System.out.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + e.getMessage());
        }
        return animalChanceToEatMap;
    }

    public Map<Class<?>, Characteristics> getAnimalCharacteristicsMap() {
        ItemSettings animalSettings = null;
        try {
            animalSettings = objectMapper.readValue(animalCharacteristicFile, ItemSettings.class);
        } catch (IOException e) {
            System.out.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + e.getMessage());
        }

        Map<Class<?>, Characteristics> animalCharacteristicsMap = new HashMap<>();

        for (AnimalCharacteristics ac : animalSettings.getAnimalCharacteristics()) {
            Characteristics characteristics = new Characteristics(ac.getAnimalClass(), ac.getWeight(), ac.getMaxAmountOnCell(), ac.getSpeed(), ac.getFullSaturation(), ac.getWeightLossPerDay(), ac.getIcon());

            try {
                Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(ANIMALS_PATH_CLASS + ac.getAnimalClass());
                animalCharacteristicsMap.put(aClass, characteristics);
            } catch (ClassNotFoundException e) {
                System.out.println(UNABLE_TO_PROCESS_CLASS + e.getMessage());
            }
        }

        return animalCharacteristicsMap;
    }
}
