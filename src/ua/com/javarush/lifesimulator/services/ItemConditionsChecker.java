package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.annotations.NumberOfItemsOnField;
import ua.com.javarush.lifesimulator.configuration.AnimalConfiguration;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameSettings.ONE_HUNDRED_PERCENT_CHANCE;

public class ItemConditionsChecker {
    private final GameField gameField;
    private final AnimalConfiguration animalConfiguration;

    public ItemConditionsChecker(GameField gameField, AnimalConfiguration animalConfiguration) {
        this.gameField = gameField;
        this.animalConfiguration = animalConfiguration;
    }

    public boolean isAnimalHungry(Animal animal) {
        return animal.getCurrentSaturation() < animal.getFullSaturation();
    }

    public boolean willAnimalBeEaten(String animalsPair) {
        boolean haveWinner = false;
        HashMap hashMap = animalConfiguration.getAnimalPairMap();

        if (hashMap.containsKey(animalsPair)) {
            int chanceToEat = (int) hashMap.get(animalsPair);
            int realResult = (int) (Math.random() * ONE_HUNDRED_PERCENT_CHANCE);
            haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
        }

        return haveWinner;
    }

    public boolean hasDestinationChanged(ItemPosition currentPosition, ItemPosition newItemPosition) {
        return ((currentPosition.getX() != newItemPosition.getX()) || (currentPosition.getY() != newItemPosition.getY()));
    }

    public boolean canReproduce(Class<? extends Animal> animalClass, long currentQuantity) {
        NumberOfItemsOnField numberOfItemsOnField = animalClass.getAnnotation(NumberOfItemsOnField.class);
        int maxAmountOnCell = numberOfItemsOnField.maxAmountOnCell();

        return currentQuantity < maxAmountOnCell;
    }

    public boolean isHuntingConditionsGood(Animal attackingAnimal, Animal animalToEat, List<Animal> eatenAnimalList) {
        return (attackingAnimal instanceof Carnivores
                && !eatenAnimalList.contains(attackingAnimal)
                && !eatenAnimalList.contains(animalToEat)
                && !attackingAnimal.getClass().getSimpleName().equals(animalToEat.getClass().getSimpleName()));
    }

    public boolean canEatPlants(Animal animal, Plant plant, List<Plant> eatenPlantList) {
        return (animal instanceof Herbivores && !eatenPlantList.contains(plant));
    }

    public boolean canAddItemToCell(BasicItem item) {
        int maxAmountOnCell = item.getMaxAmountOnCell();
        List<BasicItem> basicItemList = getBasicItemList(item);

        Map<String, Long> basicItemMap = basicItemList.stream()
                .collect(groupingBy(BasicItem::toString, Collectors.counting()));

        if (!basicItemMap.containsKey(item.toString())) {
            return true;
        }

        Long currentAmount = basicItemMap.get(item.toString());

        return currentAmount < maxAmountOnCell;
    }

    private List<BasicItem> getBasicItemList(BasicItem basicItem) {
        int x = basicItem.getItemPosition().getX();
        int y = basicItem.getItemPosition().getY();

        Cell cell = gameField.getCellFromField(y, x);

        if (basicItem instanceof Animal) {
            return new ArrayList<>(cell.getAnimalList());
        } else {
            return new ArrayList<>(cell.getPlantList());
        }
    }
}
