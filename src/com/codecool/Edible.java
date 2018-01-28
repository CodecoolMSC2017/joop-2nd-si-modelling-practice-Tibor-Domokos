public class Edible {
    String type;
    String name;
    int calories;
    boolean isExpired;

    public Edible(String name, int calories, boolean isExpired) {
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.isExpired = isExpired;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public boolean getIsExpired() {
        return isExpired;
    }
    
}