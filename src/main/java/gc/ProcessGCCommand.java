package gc;

import static seedu.duke.CantVasMain.ui;

public class ProcessGCCommand {

    public boolean processGCCommand(String input) {
        System.out.println("Welcome to the Mathematics Graphing Demo page!");

        while (true) {
            System.out.println("Please enter a command (or type 'exit' to return):");
            String command = ui.getUserCommand().trim().toLowerCase();
            switch (command) {
            case "yx":
                YX.main();
                break;
            case "ylogx":
                YlogX.main();
                break;
            case "exit":
                System.out.println("Roger! Brining you back to the main window now. \n" +
                        "You can type 'help' again to view commands available!");
                return true;
            case "help":
                System.out.println("YX or YlogX");

                break;

            default:
                System.out.println("We haven't implement the graph of : '" + command + "'. \n" +
                        "Please try again or type 'exit' to return to the main menu. \n" +
                        "or type 'help' to view available drawings");
                command = ui.getUserCommand(); // Re-prompt the user for command
                if ("exit".equalsIgnoreCase(command.trim())) {
                    System.out.println("Exiting the GPA calculator. Thank you for using it!");
                    return true; // Use true to indicate exit
                }
                break;

            }

        }

    }
}


