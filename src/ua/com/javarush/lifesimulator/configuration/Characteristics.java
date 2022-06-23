package ua.com.javarush.lifesimulator.configuration;

public class Characteristics {
    private final String animalType;
    private final double weight;
    private final int maxAmountOnCell;
    private final int speed;
    private final double fullSaturation;
    private final double weightLossPerDay;
    private final String icon;

    public Characteristics(String animalType, double weight, int maxAmountOnCell, int speed, double fullSaturation, double weightLossPerDay, String icon) {
        this.animalType = animalType;
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.speed = speed;
        this.fullSaturation = fullSaturation;
        this.weightLossPerDay = weightLossPerDay;
        this.icon = icon;
    }

    public String getAnimalType() {
        return animalType;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public double getWeightLossPerDay() {
        return weightLossPerDay;
    }

    public String getIcon() {
        return icon;
    }
}
