package ua.com.javarush.lifesimulator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_LOAD_CLASS;
import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_LOAD_CONFIGURATION_FILE;

public class ItemsConfigurations {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ANIMALS_PATH_CLASS = "ua.com.javarush.lifesimulator.items.animals.";
    private final String pathToAnimalChanceToEatFile = "/resources/chanceToEatAnimal.json";
    private final String pathToAnimalCharacteristicFile = "/resources/animalCharacteristics.json";

    public HashMap getAnimalChanceToEatMap() {
        HashMap animalChanceToEatMap = new HashMap();

        try {
            InputStream inputStream = getClass().getResourceAsStream(pathToAnimalChanceToEatFile);
            byte[] bytesFromFile = inputStream.readAllBytes();

            animalChanceToEatMap = objectMapper.readValue(bytesFromFile, HashMap.class);
        } catch (IOException e) {
            System.err.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + pathToAnimalChanceToEatFile);
            e.printStackTrace();
            System.exit(1);
        }
        return animalChanceToEatMap;
    }

    public Map<Class<?>, Characteristics> getAnimalCharacteristicsMap() {
        ItemSettings animalSettings = null;

        try {
            InputStream inputStream = getClass().getResourceAsStream(pathToAnimalCharacteristicFile);
            byte[] bytesFromFile = inputStream.readAllBytes();
            animalSettings = objectMapper.readValue(bytesFromFile, ItemSettings.class);
        } catch (IOException e) {
            System.err.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + pathToAnimalCharacteristicFile);
            e.printStackTrace();
            System.exit(1);
        }

        Map<Class<?>, Characteristics> animalCharacteristicsMap = new HashMap<>();

        for (AnimalCharacteristics ac : animalSettings.getAnimalCharacteristics()) {
            Characteristics characteristics = new Characteristics(ac.getAnimalClass(), ac.getWeight(), ac.getMaxAmountOnCell(), ac.getSpeed(), ac.getFullSaturation(), ac.getWeightLossPerDay(), ac.getIcon());

            try {
                Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(ANIMALS_PATH_CLASS + ac.getAnimalClass());
                animalCharacteristicsMap.put(aClass, characteristics);
            } catch (ClassNotFoundException e) {
                System.err.println(UNABLE_TO_LOAD_CLASS + ac.getAnimalClass());
                System.exit(1);
            }
        }

        return animalCharacteristicsMap;
    }
}
