import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static boolean isFridgeOpen = false;
    public static String displayMessage = "You're in front of your beloved fridge.";
    public static String gamemMenuType = "main";
    public static int menuSelect;
    
    public static void main(String[] args) {
        clearScreen();
        System.out.println("Available commands: :new, :load, :exit");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine(); 
            if (":new".equals(option)) {
                newGame();
            }
            else if (":load".equals(option)) {
                loadGame();
            }
            else if (":exit".equals(option)) {
                break;
            }
        }
    }

    public static void newGame() {
        // ********** Set Player's data ********** //

        String name;
        String gender;
        int weight;
        double dailyMetabolism;
        int minWeight = 30;
        int maxWeight = 150;

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nWhat is your name?\t>> ");
        name = scanner.nextLine();

        while(true) {
            System.out.print("What is your Gender?\t>> ");
            gender = scanner.nextLine();
            if(gender.toLowerCase().equals("female") || gender.toLowerCase().equals("male")) {
                break;
            }
            Common.errorMessage("The 2 possible options are: female, male");
        }

        while(true) {
            System.out.print("What is your weight?\t>> ");
            try {
                weight = Integer.parseInt(scanner.nextLine());
                if(minWeight <= weight  && weight <= maxWeight) {
                    break;
                }
                Common.errorMessage("The minimun weight is " + minWeight + " kg, and the maximum is " + maxWeight + " kg");
            }
            catch(NumberFormatException e) {
                Common.errorMessage("The only acceptable answer is a NUMBER");
            }
        }

        if(gender == "female") {
            dailyMetabolism = (weight * 10 + 700) * 1.3;
        }
        else {
            dailyMetabolism = (weight * 10 + 900) * 1.3;
        }

        Player player = new Player(name, gender.toLowerCase(), weight, dailyMetabolism);
        try {
            Fridge.fillUpFridge();
        }
        catch(IOException e) {
            Common.errorMessage("ERROR: IOException  IN: newGame()");
        }

        // ********** Let's start :) ********** //

        while(minWeight <= player.getWeight() && player.getWeight() <= maxWeight) {
            clearScreen();
            player.playerStatus();
            System.out.println("\033[32m" + displayMessage + "\033[0m\n");
            gamemMenu();
        }

        System.out.println(">>> GAME OVER <<<");
        System.exit(0);
    }

    private static void saveGame() {
    }

    private static void loadGame() {
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } 

    public static void gamemMenu() {
        if(gamemMenuType == "main") {
            System.out.println("What do you wanna do now?\n(1) Eat & Drink\n(2) Open/Close fridge\n(3) Check the fridge\n\n(4) Save game\n(5) Exit game");
            Scanner scanner = new Scanner(System.in);
            try {
                menuSelect = Integer.parseInt(scanner.nextLine());
                switch(menuSelect) {
                    case 1: if(isFridgeOpen) gamemMenuType = "eatAndDrink";
                            else displayMessage = "And how? Through the closed door?";
                            break;
                    case 2: if(isFridgeOpen) {
                                isFridgeOpen = false;
                                displayMessage = "You closed the fridge door.";  
                            }
                            else {
                                isFridgeOpen = true;
                                displayMessage = "You opened the fridge door.";
                            }
                            break;
                    case 3: if(isFridgeOpen) {
                                displayMessage = "The door is open.";
                                // List of foods ???
                            }
                            else {
                                displayMessage = "The door is closed.";
                            }
                            break;
                    case 4: break; // SAVE
                    case 5: clearScreen();
                            System.out.println("Have a nice day! Bye!\n");
                            System.exit(-1);
                            break;
                    default: displayMessage = "ERROR: It's not a valid menu option."; break;
                }
            }
            catch(NumberFormatException e) {
                Common.errorMessage("It's not a valid NUMBER");
            }
        }
        else if(gamemMenuType == "eatAndDrink") {
            System.out.println("What do you wanna eat or drink?");
            Scanner scanner = new Scanner(System.in);
            String edibleSelect = scanner.nextLine();

            // Check is it in the fridge
            // Previously a random gen filled up the fridge from the CVS file
            // Yes: adds calories, No: Gives error msg

            displayMessage = "The door is still open.";
            gamemMenuType = "main";
        }
    }

}