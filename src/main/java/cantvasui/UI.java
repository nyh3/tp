package cantvasui;
import motivationalquote.MotivationalQuotes;
import timetable.Days;

import java.util.Scanner;

public class UI {

    private final Scanner in;

    public UI() {
        this.in = new Scanner(System.in);
    }

    public String getUserCommand() {
        String input;
        do {
            input = in.nextLine();
        } while (input.trim().isEmpty()); // Checks if the input is not just whitespace
        return input;
    }

    public static void printLogo() {
        System.out.println("   _____            ___      __\n"
                + "  / ____|          | \\ \\    / /\n"
                + " | |     __ _ _ __ | |\\ \\  / /_ _ ___\n"
                + " | |    / _` | '_ \\| __\\ \\/ / _` / __|\n"
                + " | |___| (_| | | | | |_ \\  / (_| \\__ \\\n"
                + "  \\_____\\__,_|_| |_|\\__| \\/ \\__,_|___/\n"
        );
    }

    public static void printHelpMessage() {
        System.out.println("Cantvas Help\n");
        expenditureHelpMessage();
        printLineBreak();
        timetableHelpMessage();
        printLineBreak();
        gpaHelpMessage();
        printLineBreak();
        gcIntroMessage();
        printLineBreak();
    }

    private static void expenditureHelpMessage() {
        System.out.println("Expenditure help:\n"
                + "\nTo input expenses, use format:\n"
                + "<< e/ add/ d/ <description> amt/ <cost> date/ <dd.mm.yyyy> >>\n"
                + "\nTo input expenses with type, use format:\n"
                + "<< e/ add/ d/ <description> t/ <type> amt/ <cost> date/ <dd.mm.yyyy> >>\n"
                + "\nTo delete saved expenses, use format:\n<< e/ del/ <index> >>\n"
                + "\nTo clear the entire expenditure list, use format: \n<< clearlist >>\n"
                + "\nTo List saved expenses, use format:\n<< e/ list/ >>\n"
                + "\nTo view saved expenses by month, use format:\n<< e/ view/ m/ <mm.yyyy> >>\n"
                + "\nTo view saved expenses by year, use format:\n<< e/ view/ y/ <yyyy> >>\n"
                + "\nTo view saved expenses by type, use format:\n<< e/ view/ t/ <type> >>\n"
                + "\nNote that:\n\n<description> should not be longer than 100 characters.\n"
                + "<type> should not be longer than 20 characters.\n");
    }

    private static void timetableHelpMessage() {
        System.out.println("\nTimetable help:\n"
                + "\nTo input class to timetable, use format:\n"
                + "<< tt/ add/ day/ <day> code/ <classCode> time/ <hh> duration/ <duration> location/ <location> >>\n"
                + "Range for day is 1 <= day <= 5; range for time is 1 <= time <= 23.\n"
                + "\nTo delete saved class, use format:\n"
                + "<< tt/ del/ day/ <day> code/ <code> >>\n"
                + "\nTo view you timetable, use format:\n"
                + "tt/ list/\n"
                + "\nTo view you timetable for the day, use format:\n"
                + "tt/ list -d/ <day>\n"
                + "Range for day is 1 <= day <= 5\n");
    }

    private static void gpaHelpMessage() {
        System.out.println("\nGPA help:\n"
                + "\nTo Use a step by step gpa calculator, " +
                "type 'gpastep' to continue\n"
                + "\nTo Use a advanced gpa calculator, type 'gpalist' to continue\n"
                + "\nTo end the program, type 'exit'\n");
    }

    public static void gcIntroMessage() {
        System.out.println("\nTo Use our Mathematical Graph Demo\n" +
                "\nType `gc` in the main Screen to enter!");
    }

    public static void gpaListHelpMessage() {
        System.out.println("\nGPA calculator by list help:\n"
                + "\nTo calculate gpa of modules that you entered" +
                ", type 'gpa'\n"
                + "\nTo end the program, type 'exit'\n"
                + "\nTo add modules into the list, type 'add'\n" +
                "\nTo delete modules that you have entered, type 'del'\n" +
                "\nTo view the Module List that you have input, type 'viewlist'\n" +
                "\nTo view the available commands, type 'help'\n");
    }


    public static void gcHelpMessage() {
        System.out.println("Mathematical Graph Demo help:\n" +
                        "\nCurrently we have implemented 3 graphs for you to view! \n" +
                        "\nTo view Graph of Y = X , type 'YX`.\n" +
                        "\nTo view Graph of Y = log(X), type `YlogX`.\n" +
                        "\nTo view Graph of Y = X^2, type `YXsq`.\n" +
                        "\nTo exit the Graph Demo,type `exit`.\n");
    }

    public static void printExitMessage() {
        System.out.println("Shutting down... Goodbye!!");
    }

    public static void printMotivationQuote() {
        String quote = MotivationalQuotes.getQuote();
        System.out.println("Here is your motivational quote of the day :D\n");
        System.out.println(quote);
        printLineBreak();
    }

    public static void printLineBreak() {
        System.out.println("-------------------------------------------------------------");
    }

    public static void printTimetableByDay(Days[] timetable) {
        System.out.println(" | Time  |  Code  | location |");
        for (int i = 0; i < 24; i ++) {
            printTimetableByHour(timetable[i]);
        }
    }

    private static void printTimetableByHour(Days timetable) {
        if (timetable != null) {
            System.out.println(timetable.toStringDay());
        }
    }

}
