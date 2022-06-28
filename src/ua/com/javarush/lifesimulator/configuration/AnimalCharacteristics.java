package ua.com.javarush.lifesimulator.configuration;

public class AnimalCharacteristics {
    private double weight;
    private int maxAmountOnCell;
    private int speed;
    private double fullSaturation;
    private double weightLossPerDay;
    private String animalClass;
    private String icon;

    public AnimalCharacteristics() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public void setMaxAmountOnCell(int maxAmountOnCell) {
        this.maxAmountOnCell = maxAmountOnCell;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public void setFullSaturation(double fullSaturation) {
        this.fullSaturation = fullSaturation;
    }

    public double getWeightLossPerDay() {
        return weightLossPerDay;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    public String getIcon() {
        return icon;
    }
}
