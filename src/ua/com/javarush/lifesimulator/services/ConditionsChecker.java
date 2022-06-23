package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.interfaces.Carnivores;
import ua.com.javarush.lifesimulator.interfaces.Herbivores;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.lifesimulator.constants.GameConstants.*;

public class ConditionsChecker {
    private final Utility utility;

    public ConditionsChecker(Utility utility) {
        this.utility = utility;
    }

    public boolean isAnimalHungry(Animal animal) {
        return animal.getCurrentSaturation() < animal.getFullSaturation();
    }

    public boolean isHuntingConditionsGood(Animal attackingAnimal, Animal animalToEat, List<Animal> eatenAnimalList) {
        return (attackingAnimal instanceof Carnivores
                && !eatenAnimalList.contains(attackingAnimal)
                && !eatenAnimalList.contains(animalToEat)
                && !attackingAnimal.getClass().getSimpleName().equals(animalToEat.getClass().getSimpleName()));
    }

    public boolean willAnimalBeEaten(String animalsPair) {
        boolean haveWinner = false;
        HashMap hashMap = utility.getAnimalChanceToEatMap();

        if (hashMap.containsKey(animalsPair)) {
            int chanceToEat = (int) hashMap.get(animalsPair);
            int realResult = (int) (Math.random() * ONE_HUNDRED_PERCENT_CHANCE);
            haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
        }

        return haveWinner;
    }

    public boolean canEatPlants(Animal animal, Plant plant, List<Plant> eatenPlantList) {
        return (animal instanceof Herbivores && !eatenPlantList.contains(plant));
    }

    public boolean hasDestinationChanged(ItemPosition currentPosition, ItemPosition newItemPosition) {
        return ((currentPosition.getX() != newItemPosition.getX()) || (currentPosition.getY() != newItemPosition.getY()));
    }

    public boolean canReproduce(Animal animal, long currentAnimalNumber) {
        int maxAmountOnCell = animal.getMaxAmountOnCell();

        return ((currentAnimalNumber < maxAmountOnCell));
    }

    public boolean canAddItemToCell(GameBoard gameBoard, BasicItem item) {
        int maxAmountOnCell = item.getMaxAmountOnCell();
        List<BasicItem> basicItemList = utility.getBasicItemListFromCell(gameBoard, item);

        Map<String, Long> basicItemMap = basicItemList.stream()
                .collect(groupingBy(BasicItem::toString, Collectors.counting()));

        if (!basicItemMap.containsKey(item.toString())) {
            return true;
        }

        Long currentAmount = basicItemMap.get(item.toString());

        return currentAmount < maxAmountOnCell;
    }

    public boolean cantMove(Animal animal) {
        return (animal.isAlreadyWalked() || animal.getSpeed() == 0);
    }

    public boolean isWorldAlive(int numberOfLocationsWithoutAnimals) {
        return numberOfLocationsWithoutAnimals < (GAME_BOARD_HEIGHT * GAME_BOARD_WIDTH);
    }
}
