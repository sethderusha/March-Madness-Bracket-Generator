import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MarchMadness {

    public static void main(String[] args) {
        // Verify command line arguments
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }

        // Verify file existence and readability
        File bracket = new File(args[0]);
        if (!bracket.exists()) {
            System.err.println("Error: the file " + bracket.getAbsolutePath() + " does not exist.\n");
            System.exit(1);
        }
        if (!bracket.canRead()) {
            System.err.println("Error: the file " + bracket.getAbsolutePath() + " cannot be opened for reading.\n");
            System.exit(1);
        }

        // Create Divisions
        Round sixtyFour = new Round();

        // Open the file for reading using Scanner and CSV
        try (Scanner scanner = new Scanner(bracket)) {
            CSV csv = new CSV(scanner);

            // Iterate through each row and assign values based on header values
            ArrayList<String> row = csv.getNextRow();
            while (row != null) {
                try {
                    int seed = Integer.parseInt(row.get(0));
                    String name = row.get(1);
                    Team team = new Team(name, seed);
                    sixtyFour.add(team);
                    row = csv.getNextRow();
                } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                    // Continue quietly without printing any errors
                    row = csv.getNextRow();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file " + bracket.getAbsolutePath() + " cannot be opened for reading.\n");
            System.exit(1);
        }

        // Create brackets with different strategies
        Bracket randomBracket = createBracket(sixtyFour, "Random Bracket");
        Bracket weightedBracket = createBracket(sixtyFour, "Weighted Bracket");
        Bracket historicalBracket = createBracket(sixtyFour, "Historical Bracket");

        // Print brackets
        System.out.println("random -\n"+randomBracket+"\nweighted -\n"+weightedBracket+"\nhistorical -\n"+historicalBracket);
    }

    // Create a bracket with the specified strategy
    private static Bracket createBracket(Round sixtyFour, String strategy) {
        Round thirtyTwo;
        Round sixteen;
        Round eight;
        Round four;
        Round championship;
        Round champion;

        switch (strategy) {
            case "Random Bracket":
                thirtyTwo = sixtyFour.randomRound();
                sixteen = thirtyTwo.randomRound();
                eight = sixteen.randomRound();
                four = eight.randomRound();
                championship = four.randomRound();
                champion = championship.randomRound();
                break;
            case "Weighted Bracket":
                thirtyTwo = sixtyFour.weightedRound();
                sixteen = thirtyTwo.weightedRound();
                eight = sixteen.weightedRound();
                four = eight.weightedRound();
                championship = four.weightedRound();
                champion = championship.weightedRound();
                break;
            case "Historical Bracket":
                thirtyTwo = sixtyFour.historicalRound();
                sixteen = thirtyTwo.historicalRound();
                eight = sixteen.historicalRound();
                four = eight.historicalRound();
                championship = four.historicalRound();
                champion = championship.historicalRound();
                break;
            default:
                throw new IllegalArgumentException("Invalid bracket strategy");
        }

        return new Bracket(sixtyFour, thirtyTwo, sixteen, eight, four, championship, champion);
    }
}