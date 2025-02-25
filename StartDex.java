import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//TODO: do not use '*' import - only import that parts of the packages that you
// '*' import causes unnecessary bloat

public class StartDex {
    public void welcome() {
        System.out.println("\n" +
                "                                                                                                      \n" +
                "     _____           _____     ____    ____       ______        _____        ______                   \n" +
                " ___|\\    \\     ____|\\    \\   |    |  |    |  ___|\\     \\   ___|\\    \\   ___|\\     \\  _____      _____\n" +
                "|    |\\    \\   /     /\\    \\  |    |  |    | |     \\     \\ |    |\\    \\ |     \\     \\ \\    \\    /    /\n" +
                "|    | |    | /     /  \\    \\ |    | /    // |     ,_____/||    | |    ||     ,_____/| \\    \\  /    / \n" +
                "|    |/____/||     |    |    ||    |/ _ _//  |     \\--'\\_|/|    | |    ||     \\--'\\_|/  \\____\\/____/  \n" +
                "|    ||    |||     |    |    ||    |\\    \\'  |     /___/|  |    | |    ||     /___/|    /    /\\    \\  \n" +
                "|    ||____|/|\\     \\  /    /||    | \\    \\  |     \\____|\\ |    | |    ||     \\____|\\  /    /  \\    \\ \n" +
                "|____|       | \\_____\\/____/ ||____|  \\____\\ |____ '     /||____|/____/||____ '     /|/____/ /\\ \\____\\\n" +
                "|    |        \\ |    ||    | /|    |   |    ||    /_____/ ||    /    | ||    /_____/ ||    |/  \\|    |\n" +
                "|____|         \\|____||____|/ |____|   |____||____|     | /|____|____|/ |____|     | /|____|    |____|\n" +
                "  \\(              \\(    )/      \\(       )/    \\( |_____|/   \\(    )/     \\( |_____|/   \\(        )/  \n" +
                "   '               '    '        '       '      '    )/       '    '       '    )/       '        '   \n" +
                "                                                     '                          '                     ");
        System.out.println("You can search for pokemon by type or search for a single " +
                "pokemon with this method or get shown a random Pokemon");
    }

    // TODO: consider using picocli library for building the commandline functionality
    // https://www.baeldung.com/java-picocli-create-command-line-program
    // this will more easily allow you to parse user input
    // also consider using the *Command Pattern* so that you don't need to use so many statements and you can
    // consolidate all of the functionality of parsing user input into a single location - https://www.baeldung.com/java-command-pattern
    // https://refactoring.guru/design-patterns/command
    // https://www.tutorialspoint.com/design_pattern/command_pattern.htm
    public static void whichDex() {
        //TODO: changed the output to be easier to read
        System.out.println("Enter which Dex mode you want to use:\n" +
                "    'random' for random Dex entry\n" +
                "    'stats' to search by stats\n" +
                "    'regular' to search with given parameters:\n");
        Scanner which = new Scanner(System.in);
        String thisOne = which.next();
        boolean moreDex = true;
        while (moreDex) {
            if (thisOne.equalsIgnoreCase("random") || thisOne.contains("Ra") || thisOne.contains("ra")) {
                GetRandomMon randMon = new GetRandomMon();
                System.out.println("Your Random Pokemon:");
                randMon.getRandMon();
                moreDex = keepGoing();
            }
            if (thisOne.equalsIgnoreCase("stats") || thisOne.contains("St") || thisOne.contains("st")) {
                System.out.println("You chose to enter your own parameters\nEnter your" +
                        " chosen parameters:");
                GetMons sDex = new GetMons();
                sDex.getStats();
                moreDex = keepGoing();
            }
            if (thisOne.equalsIgnoreCase("regular") || thisOne.contains("Re") || thisOne.contains("re")) {
                System.out.println("You chose to enter your own parameters\nEnter your" +
                        " chosen parameters:");
                GetMons dex = new GetMons();
                dex.findEntry();
                moreDex = keepGoing();
            }
        /*if(!thisOne.contains("Re") || !thisOne.contains("re") || !thisOne.contains("Ra") || !thisOne.contains("ra") ||!thisOne.contains("St") || !thisOne.contains("st")){
            System.out.println("That's not an option :/");
        }*/
        }

    }

    //Asks players if they want to continue playing or stop
    public static boolean keepGoing() {
        String answer;
        boolean keepGoing = true;
        do {
            //Scanner takes input from keyboard and evaluates what it is, 'y' continues game, 'n' stops game, and any other option asks the question again
            System.out.println("\nIf you want to keep playing type 'y' if not, type 'n'.");
            Scanner keyboard = new Scanner(System.in);
            answer = keyboard.next();
        } while (answer.equals("y") || answer.equals("n")); // need to check strings with '.equals()'

        if (answer.toLowerCase().contains("n")) {
            EndDex seeYa = new EndDex();
            System.out.println("\n\nSee you next time!");
            keepGoing = false;
            seeYa.goodbye();
        }

        return keepGoing;
    }

    //This method is for debugging purposes
    public void getAllPokemon() {
        try {
            File all = new File("Pokemon.txt");
            Scanner reader = new Scanner(all);
            while (reader.hasNextLine()) {
                String printDex = reader.nextLine();
                System.out.println(printDex);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
}
