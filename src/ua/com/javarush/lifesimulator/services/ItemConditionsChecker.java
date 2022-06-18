package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.interfaces.Carnivores;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameErrors.SOMETHING_WENT_WRONG;
import static ua.com.javarush.lifesimulator.constants.GameSettings.ONE_HUNDRED_PERCENT_CHANCE;
import static ua.com.javarush.lifesimulator.constants.ItemSettings.CHANCE_TO_EAT_SOMEONE;

public class ItemConditionsChecker {
    private final GameField gameField;

    public ItemConditionsChecker(GameField gameField) {
        this.gameField = gameField;
    }

    public boolean isAnimalHungry(Animal animal) {
        return animal.getCurrentSaturation() < animal.getFullSaturation();
    }

    public boolean willAnimalBeEaten(String animalsPair) {
        boolean haveWinner = false;
        for (Map.Entry<String, Integer> fightingAnimals : CHANCE_TO_EAT_SOMEONE.entrySet()) {
            if (fightingAnimals.getKey().equals(animalsPair)) {
                int chanceToEat = fightingAnimals.getValue();
                int realResult = new Random().nextInt(ONE_HUNDRED_PERCENT_CHANCE);
                haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
                break;
            }
        }
        return haveWinner;
    }

    public boolean hasDestinationChanged(ItemPosition currentPosition, ItemPosition newItemPosition) {
        return ((currentPosition.getX() != newItemPosition.getX()) || (currentPosition.getY() != newItemPosition.getY()));
    }

    public boolean canReproduce(Class<? extends Animal> animalClass, long currentQuantity) {
        int maxAmountOnCell = 0;
        try {
            Field fieldMaxAmountOnCell = animalClass.getDeclaredField("MAX_AMOUNT_ON_CELL");
            fieldMaxAmountOnCell.setAccessible(true);
            maxAmountOnCell = (int) fieldMaxAmountOnCell.get(animalClass);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(SOMETHING_WENT_WRONG);
        }

        return currentQuantity < maxAmountOnCell;
    }

    public boolean isHuntingConditionsGood(Animal attackingAnimal, Animal animalToEat, List<Animal> eatenAnimalList) {
        return ((attackingAnimal instanceof Carnivores)
                || !(eatenAnimalList.contains(animalToEat))
                || !(attackingAnimal.getClass().getSimpleName()).equals(animalToEat.getClass().getSimpleName()));
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
        }
        else  {
            return new ArrayList<>(cell.getPlantList());
        }
    }
}
