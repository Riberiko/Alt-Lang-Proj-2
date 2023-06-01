import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        HashMap<String, LinkedList<Cell>> phoneOEM = new HashMap<>();

        try(Scanner sc = new Scanner(new File("src/main/resources/cells.csv")))
        {
            sc.nextLine();  //skips the header
            while(sc.hasNextLine())
            {
                Cell cell = ParseCell.parseCell(sc.nextLine());
                if(!phoneOEM.containsKey(cell.getOem())) phoneOEM.put(cell.getOem(), new LinkedList<>(List.of(cell)));
                else phoneOEM.get(cell.getOem()).add(cell);
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("File cells.cvs Not Found");
        }

        String highAvgOem = "";
        double highAvgVal = 0;
        double temp;

        LinkedList<Cell> announcedYearNotEqualReleaseYear = new LinkedList<>();
        LinkedList<Cell> oneFeature = new LinkedList<>();

        for (var pair: phoneOEM.entrySet())
        {
            temp = ParseCell.getAvgBodyWeight(pair.getValue());
            if(temp > highAvgVal)
            {
                highAvgOem = pair.getKey();
                highAvgVal = temp;
            }
            announcedYearNotEqualReleaseYear.addAll(ParseCell.getAnnouncedNotEReleased(pair.getValue()));
            oneFeature.addAll(ParseCell.getPhoneWithNumFeatures(pair.getValue(), 1));

        }

        System.out.println("OEM with Highest Average cell body weight");
        System.out.println(highAvgOem + " : " + highAvgVal + "\n");

        System.out.println("Cells which were announced in one year but then released in another");
        System.out.println(announcedYearNotEqualReleaseYear + "\n");

        System.out.println("Phones that only have one feature with v1 as a place holder for a feature");
        System.out.println(oneFeature.size()+ "\n");

        System.out.println("Year that had the most phones launched in the 2000s?");
        System.out.println(ParseCell.getYearMostLaunched(phoneOEM, 2000, 3000));


    }
}
