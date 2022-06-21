package ua.com.javarush.lifesimulator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_LOAD_CONFIGURATION_FILE;
import static ua.com.javarush.lifesimulator.constants.GameErrors.UNABLE_TO_PROCESS_CLASS;

public class AnimalConfigurations {
    private static final String ANIMALS_PATH_CLASS = "ua.com.javarush.lifesimulator.items.animals.";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File animalPairFile = new File("resources/chanceToEatAnimal.json");
    private final File animalCharacteristicFile = new File("resources/animalCharacteristics.json");

    public HashMap getAnimalChanceToEatMap() {
        HashMap animalPairMap = new HashMap();
        try {
            animalPairMap = objectMapper.readValue(animalPairFile, HashMap.class);
        } catch (IOException e) {
            System.out.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + e.getMessage());
        }
        return animalPairMap;
    }

    public Map<Class<?>, List<Number>> getAnimalCharacteristicsMap() {
        ItemSettings animalSettings = null;
        try {
            animalSettings = objectMapper.readValue(animalCharacteristicFile, ItemSettings.class);
        } catch (IOException e) {
            System.out.println(UNABLE_TO_LOAD_CONFIGURATION_FILE + e.getMessage());
        }

        Map<Class<?>, List<Number>> animalCharacteristicMap = new HashMap<>();

        for (AnimalCharacteristics animalCharacteristic : animalSettings.getAnimalCharacteristics()) {
            double weight = animalCharacteristic.getWeight();
            int maxAmountOnCell = animalCharacteristic.getMaxAmountOnCell();
            int speed = animalCharacteristic.getSpeed();
            double fullSaturation = animalCharacteristic.getFullSaturation();
            double weightLossPerDay = animalCharacteristic.getWeightLossPerDay();
            String animalClass = animalCharacteristic.getAnimalClass();

            List<Number> characteristicList = List.of(weight, maxAmountOnCell, speed, fullSaturation, weightLossPerDay);

            try {
                Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(ANIMALS_PATH_CLASS + animalClass);
                animalCharacteristicMap.put(aClass, characteristicList);
            } catch (ClassNotFoundException e) {
                System.out.println(UNABLE_TO_PROCESS_CLASS + e.getMessage());
            }
        }
        return animalCharacteristicMap;
    }
}
