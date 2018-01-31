
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Fridge {
    static int totalSlots = 12;
    static int freeSlots;
    static boolean rottenOrNot;
    static Edible[] fridgeList;

    Fridge(int totalSlots) {
        this.totalSlots = totalSlots;
        this.freeSlots = freeSlots;
    }

    public static void fillUpFridge() throws IOException {
        String fileName = "edible.csv";
        File sourceFile = new File(fileName);
        if (sourceFile.exists()) {
            try {
                // Count the lines in the source file
                BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                int sourceLineNum = 0;
                while (reader.readLine() != null) sourceLineNum++;
                reader.close();

                // Chose random items for "totalSlots" times from the source file
                switch (sourceLineNum) {
                    case 0:     Common.errorMessage("ERROR: No data in source file!   IN: fillUpFridge()");
                                System.exit(-1);
                                break;
                    default:    fridgeList = new Edible[totalSlots];
                                for(int i = 0; i < totalSlots; i++) {
                                    int randomItem = Common.random(1, sourceLineNum);
                                    Scanner sc = new Scanner(sourceFile);
                                    int findLine = 1;
                                    while (sc.hasNext()) {
                                        String sourceLine = sc.next();
                                        if(findLine == randomItem) {
                                            String itemName = sourceLine.split(",")[1].replace("_", " ");
                                            int itemCals = Integer.parseInt(sourceLine.split(",")[2]);
                                            int itemCounter = 1;

                                            int randomRotten = Common.random(1, 5);
                                            if(randomRotten == 1) rottenOrNot = true;
                                            else rottenOrNot = false;

                                            if (sourceLine.split(",")[0].equals("drink")) {
                                                fridgeList[i] = new Drink(itemName, itemCals, false);
                                                itemCounter++;
                                            }
                                            else if(sourceLine.split(",")[0].equals("food")) {
                                                fridgeList[i] = new Food(itemName, itemCals, rottenOrNot);
                                                itemCounter++;
                                            }
                                            else {
                                                fridgeList[i] = new Other(itemName, itemCals, true);
                                                itemCounter++;
                                            }
                                        }
                                        findLine++; 
                                    }
                                }
                                break;
                }

            }
            catch(FileNotFoundException e) {
                Common.errorMessage("ERROR: FileNotFoundException   IN: fillUpFridge");
            }
        }
        else {
            Common.errorMessage("ERROR: File is missing!   IN: fillUpFridge()");
            System.exit(-1);
        }
    }

    public Edible[] getFridgeList() {
        return fridgeList;
    }

    public void setFreeSlots() {

    }

    public void listItem() {
        
    }

    public void findItem() {
        
    }

}