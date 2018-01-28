import java.util.Random;

public class Common {
    boolean isFridgeOpen;
    String displayMessage;
    String gamemMenuType;
 
    public static int random(int from, int to) {
        Random r = new Random();
        int result = r.nextInt((to+1)-from) + from;
        return result;
    }

    public void isFridgeOpen() {
        if(isFridgeOpen) {
            displayMessage = "The door is open.";
            // List of foods ???
        }
        else {
            displayMessage = "The door is closed.";
        }
    }

    public void useFridgeDoor() {
        if(isFridgeOpen) {
            isFridgeOpen = false;
            displayMessage = "You closed the fridge door.";  
        }
        else {
            isFridgeOpen = true;
            displayMessage = "You opened the fridge door.";
        }
    }

    public void eatOrDrink() {
        if(isFridgeOpen) {
            gamemMenuType = "eatAndDrink";
        }
        else {
            displayMessage = "And how? Through the closed door?";
        }
    }

    public static void errorMessage(String text) {
        System.out.println("\033[1m\033[31m" + text + "\033[0m");
    }
}