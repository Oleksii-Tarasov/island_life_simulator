package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.Utility;
import ua.com.javarush.lifesimulator.items.GameBoard;
import ua.com.javarush.lifesimulator.items.BasicItem;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameSettings.*;

public class ItemConditionsChecker {
    private final Utility utility;
//
//    private final AnimalConfiguration animalConfiguration;
    private final Random chance = new Random();

    public ItemConditionsChecker(Utility utility) {
        this.utility = utility;
//        this.gameBoard = gameBoard;
//        this.animalConfiguration = animalConfiguration;
    }

//    public boolean isAnimalHungry(Animal animal) {
//        return animal.getCurrentSaturation() < animal.getFullSaturation();
//    }
//
//    public boolean willAnimalBeEaten(String animalsPair) {
//        boolean haveWinner = false;
//        HashMap hashMap = animalConfiguration.getAnimalPairMap();
//
//        if (hashMap.containsKey(animalsPair)) {
//            int chanceToEat = (int) hashMap.get(animalsPair);
//            int realResult = (int) (Math.random() * ONE_HUNDRED_PERCENT_CHANCE);
//            haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
//        }
//
//        return haveWinner;
//    }
//
//    public boolean hasDestinationChanged(ItemPosition currentPosition, ItemPosition newItemPosition) {
//        return ((currentPosition.getX() != newItemPosition.getX()) || (currentPosition.getY() != newItemPosition.getY()));
//    }
//
//    public boolean canReproduce(Class<? extends Animal> animalClass, long currentQuantity) {
//        NumberOfItemsOnField numberOfItemsOnField = animalClass.getAnnotation(NumberOfItemsOnField.class);
//        int maxAmountOnCell = numberOfItemsOnField.maxAmountOnCell();
//
//        return ((currentQuantity < maxAmountOnCell));
//    }
//
//    public boolean isHuntingConditionsGood(Animal attackingAnimal, Animal animalToEat, List<Animal> eatenAnimalList) {
//        return (attackingAnimal instanceof Carnivores
//                && !eatenAnimalList.contains(attackingAnimal)
//                && !eatenAnimalList.contains(animalToEat)
//                && !attackingAnimal.getClass().getSimpleName().equals(animalToEat.getClass().getSimpleName()));
//    }
//
//    public boolean canEatPlants(Animal animal, Plant plant, List<Plant> eatenPlantList) {
//        return (animal instanceof Herbivores && !eatenPlantList.contains(plant));
//    }

    public boolean canAddItemToCell(GameBoard gameBoard, BasicItem item) {
        int maxAmountOnCell = item.getMaxAmountOnCell();
//        List<BasicItem> basicItemList = getBasicItemList(item);
        List<BasicItem> basicItemList = utility.getBasicItemListFromCell(gameBoard, item);

        Map<String, Long> basicItemMap = basicItemList.stream()
                .collect(groupingBy(BasicItem::toString, Collectors.counting()));

        if (!basicItemMap.containsKey(item.toString())) {
            return true;
        }

        Long currentAmount = basicItemMap.get(item.toString());

        return currentAmount < maxAmountOnCell;
    }

//    private List<BasicItem> getBasicItemList(BasicItem basicItem) {
//        int x = basicItem.getItemPosition().getX();
//        int y = basicItem.getItemPosition().getY();
//
////        Cell cell = gameField.getCellFromField(y, x);
//        Cell cell = gameBoard.getCell(y, x);
//
//        if (basicItem instanceof Animal) {
//            return new ArrayList<>(cell.getAnimalList());
//        } else {
//            return new ArrayList<>(cell.getPlantList());
//        }
//    }

    public boolean isWorldAlive(int numberOfLocationsWithoutAnimals) {
        return numberOfLocationsWithoutAnimals < GAME_FIELD_HEIGHT * GAME_FIELD_WIDTH;
    }
}
