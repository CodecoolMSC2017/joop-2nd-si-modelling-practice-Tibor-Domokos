import java.util.Scanner;

public class Player {

    String name;
    String gender;
    int weight;
    double dailyMetabolism;

    Player(String name, String gender, int weight, double dailyMetabolism) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.dailyMetabolism = dailyMetabolism;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public int getWeight() {
        return this.weight;
    }

    public double getDailyMetabolism() {
        return this.dailyMetabolism;
    }

    public void setWeight(int number) {
        this.weight += number;
    }

    public void playerStatus() {
        System.out.println("\nName: " + getName() + ", Weight: " + getWeight() + " kg, Daily calorie needs: " + getDailyMetabolism() + " kcal\n");
    }

}