import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    public static boolean isFridgeOpen = false;
    public static String displayMessage = "You're in front of your beloved fridge.";
    public static String gamemMenuType = "main";
    public static int menuSelect;

    public static String name;
    public static String gender;
    public static int weight;
    public static double dailyMetabolism;
    public static int minWeight = 30;
    public static int maxWeight = 150;

    public static String saveFile = "save.csv";

    private static Fridge fridge;

    
    public static void main(String[] args) {
        clearScreen();
        System.out.println("\nPlease select:\n\n(1) New game\n(2) Load game\n(3) Quit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                menuSelect = Integer.parseInt(scanner.nextLine());
                switch(menuSelect) {
                    case 1: newGame();
                            break;
                    case 2: loadGame();
                            break;
                    case 3: clearScreen();
                            System.out.println("\nHave a nice day! Bye!\n");
                            System.exit(-1);
                            break;
                    default: displayMessage = "ERROR: It's not a valid menu option."; break;
                }
            }
            catch(NumberFormatException e) {
                Common.errorMessage("It's not a valid NUMBER");
            }
        }
    }

    public static void newGame() {
        // ********** Set Player's data ********** //

        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("\nSetting up your game profile...");
        System.out.print("\nWhat is your name?\t>> ");
        name = scanner.nextLine();
        if(name.equals("")) {
            name = "Nameless Player";
        }
        
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

        try {
            Fridge.fillUpFridge();
        }
        catch(IOException e) {
            Common.errorMessage("ERROR: IOException  IN: newGame()");
        }

        // ********** Let's start :) ********** //

        letStart();
        System.out.println(">>> GAME OVER <<<");
        System.exit(0);
    }

    private static void saveGame() {
        try {
/*
            ObjectOutputStream oos;
            oos.writeObject(fridge);
*/
            Formatter newFile = new Formatter(saveFile);
            newFile.format(name + "\n");
            newFile.format(gender + "\n");
            newFile.format(weight + "\n");
            newFile.format(dailyMetabolism + "\n");

            Fridge fl = new Fridge(Fridge.totalSlots);
            for (Edible edible : fl.getFridgeList()) {
                System.out.println(edible);
            }
            
            
            newFile.format("fridge content 1\n");
            newFile.format("fridge content 2\n");
            newFile.format("fridge content 3\n");
            newFile.format("fridge content 4\n");
            newFile.format("fridge content...");
            newFile.close();

            displayMessage = "Game saved successfully! :)";
        }
        catch (Exception e) {
            Common.errorMessage("ERROR: Exception  IN: saveGame()");
        }
    }

    private static void loadGame() {
        File sourceFile = new File(saveFile);
        if (sourceFile.exists()) {
            try {
                Scanner lg = new Scanner(sourceFile);
                int lineCounter = 1;
                while (lg.hasNext()) {
                    String saveGameLine = lg.next();
                    if(lineCounter == 1) name = saveGameLine;
                    else if(lineCounter == 2) gender = saveGameLine;
                    else if(lineCounter == 3) weight = Integer.parseInt(saveGameLine);
                    else if(lineCounter == 4) dailyMetabolism = Double.parseDouble(saveGameLine);

                    // Fridge content will goes here...

                    lineCounter++;
                }

                displayMessage = "Game loaded successfully! :)";

                letStart();
                System.out.println(">>> GAME OVER <<<");
                System.exit(0);
            }
            catch(FileNotFoundException e) {
                Common.errorMessage("ERROR: FileNotFoundException  IN: loadGame()");
            }
        }
        else {
            Common.errorMessage("No saved games at this moment! Sorry, yeah?!");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void letStart() {
        Player player = new Player(name, gender.toLowerCase(), weight, dailyMetabolism);
        while(minWeight <= player.getWeight() && player.getWeight() <= maxWeight) {
            clearScreen();
            player.playerStatus();
            System.out.println("\033[32m" + displayMessage + "\033[0m\n");
            gamemMenu();
        }
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
                    case 4: saveGame();
                            break;
                    case 5: clearScreen();
                            System.out.println("\nHave a nice day! Bye!\n");
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
            System.out.println("Choose one of the followings:");

            // Edieble list in the fridge
            Fridge fl = new Fridge(Fridge.totalSlots);
            for (Edible edible : fl.getFridgeList()) {
                System.out.println("  - " + edible.getName() + "(" + edible.getCalories() + ")");
            }

            System.out.println("\nWhat do you wanna eat or drink?");
            Scanner scanner = new Scanner(System.in);
            String edibleSelect = scanner.nextLine();

            if(edibleSelect.equals("") || edibleSelect.equals(" ")) {
                displayMessage = "Nothing is nothing...";
            }
            else {
                // Check the selected item
                boolean edibleFound = false;
                for (Edible edible : fl.getFridgeList()) {
                    String searchEdieble = edibleSelect.toLowerCase();
                    String ediebleInList = edible.getName().toLowerCase();
                    if(ediebleInList.contains(searchEdieble)) edibleFound = true;
                }
                if(edibleFound == false) {
                    displayMessage = "There's nothing in the fridge like " + edibleSelect;
                }

                // Remove selected item from the list
                if(edibleFound) {
                    Edible[] fridgeListTemp = new Edible[Fridge.fridgeList.length - 1];
                    int listPosCounter = 0;
                    boolean searchMore = true;
                    for (Edible edible : fl.getFridgeList()) {
                        String searchEdieble = edibleSelect.toLowerCase();
                        String ediebleInList = edible.getName().toLowerCase();
                        if(ediebleInList.contains(searchEdieble) && searchMore == true) {
                            displayMessage = "You the orcádba toltál one " + edible.getName();
                            listPosCounter--;
                            searchMore = false;
                            Fridge.totalSlots = fridgeListTemp.length;

                            // MODIFY PLAYER'S INFO

                        }
                        else { 
                            fridgeListTemp[listPosCounter] = edible;
                        }
                        listPosCounter++;
                    }
                    Fridge.fridgeList = fridgeListTemp;
                }
            }
            gamemMenuType = "main";
        }
    }

}