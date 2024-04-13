package gc;

import cantvasui.UI;

import static seedu.duke.CantVasMain.ui;

public class ProcessGCCommand {

    /**
     * Processes user commands in the Mathematics Graph Demo page. This method manages
     * the interaction flow, allowing the user to execute graph-related commands such as
     * displaying various types of mathematical graphs or exiting the graph demo.
     *
     * The method operates in a loop, prompting the user for commands until the 'exit'
     * command is issued. It supports several commands:
     * - 'yx': Displays a simple y = x graph.
     * - 'ylogx': Displays a logarithmic y = log(x) graph.
     * - 'yxsq': Displays a squared graph y = x^2.
     * - 'exit': Exits the graph demo and returns to the main menu.
     * - 'help': Displays available commands.
     *
     * If an unrecognized command is entered, the method prompts the user to try again,
     * enter 'help' for assistance, or 'exit' to leave the graph demo.
     *
     * @param input the initial input command (not currently used in method implementation).
     * @return true when the 'exit' command is issued, otherwise the loop continues indefinitely.
     */
    public boolean processGCCommand(String input) {
        System.out.println("Welcome to the Mathematics Graph Demo page!\n");
        UI.gcHelpMessage();
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
            case "yxsq":
                YXsq.main();
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


