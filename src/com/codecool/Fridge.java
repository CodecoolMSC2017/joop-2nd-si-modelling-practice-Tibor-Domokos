import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Fridge {
    static int totalSlots = 12;
    static int freeSlots;

    Fridge(int totalSlots, int freeSlots) {
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
                    default:    for(int i = 0; i < totalSlots; i++) {
                                    int randomItem = Common.random(1, sourceLineNum);
                                    String[] itemArray = new String[3];
                                    Scanner sc = new Scanner(sourceFile);
                                    int findLine = 1;
                                    while (sc.hasNext()) {
                                        String sourceLine = sc.next();
                                        if(findLine == randomItem) {
                                            if (sourceLine.split(",")[0].equals("drink")) {
                                                // JUST FOR TESTING
                                                itemArray[0] = sourceLine.split(",")[0];
                                                itemArray[1] = sourceLine.split(",")[1].replace("_", " ");
                                                itemArray[2] = sourceLine.split(",")[2];
                                            }
                                            else if(sourceLine.split(",")[0].equals("food")) {
                                                // JUST FOR TESTING
                                                itemArray[0] = sourceLine.split(",")[0];
                                                itemArray[1] = sourceLine.split(",")[1].replace("_", " ");
                                                itemArray[2] = sourceLine.split(",")[2];
                                            }
                                            else {
                                                // JUST FOR TESTING
                                                itemArray[0] = sourceLine.split(",")[0];
                                                itemArray[1] = sourceLine.split(",")[1].replace("_", " ");
                                                itemArray[2] = sourceLine.split(",")[2];
                                            }
/*
                                            itemArray[0] = sourceLine.split(",")[0];
                                            itemArray[1] = sourceLine.split(",")[1].replace("_", " ");
                                            itemArray[2] = sourceLine.split(",")[2];
*/
                                        }
                                        findLine++; 
                                    }
                                    System.out.println(randomItem + ", " + itemArray[0] + ", " + itemArray[1] + ", " + itemArray[2]);
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

    public void setFreeSlots() {

    }

    public void listItem() {
        
    }

    public void findItem() {
        
    }

}