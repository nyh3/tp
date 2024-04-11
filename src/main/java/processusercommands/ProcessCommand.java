package processusercommands;

import cantvasui.UI;
import expenditure.ExpenditureList;
import storage.Storage;
import gpa.GPACommand;
import gc.ProcessGCCommand;
import timetable.TimetableList;
import gpa.ProcessListCommand;

public class ProcessCommand {

    /**
     * handles the main inputs related to the
     * expenditure
     * @param command
     */
    public void processExpenditureUserCommand(String command) {
        String[] commandParts = command.substring(2).trim().split("/", 2);

        if (commandParts.length < 2) {
            System.out.println("Incomplete command!");
            return;
        }

        String action = commandParts[0].trim();
        String actionDetails = commandParts[1].trim();

        switch (action) {
        case "add":
            ExpenditureList.addExpenditure(actionDetails,true);
            break;
        case "del":
            try {
                int index = Integer.parseInt(actionDetails);
                ExpenditureList.deleteExpenditure(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid index format for deletion.");
            }
            break;
        case "clearlist":
            ExpenditureList.clearlist();
            break;
        case "list":
            ExpenditureList.listExpenses();
            break;
        case "view":
            processViewUserCommand(actionDetails);
            break;
        default:
            System.out.println("Unknown function: " + action);
            break;
        }
    }

    /**
     * when user wants to view filtered expenditure
     * @param command user input to parse
     */
    public void processViewUserCommand(String command) {
        String[] viewCommandParts = command.split("/", 2);

        if (viewCommandParts.length < 2) {
            System.out.println("Incomplete view command!");
            return;
        }

        String action = viewCommandParts[0].trim();
        String actionDetails = viewCommandParts.length > 1 ? viewCommandParts[1].trim() : "";

        switch (action) {
        case "m":
            if (!actionDetails.isEmpty()) {
                ExpenditureList.listExpensesByMonth(actionDetails);
            } else {
                System.out.println("Please provide a month and year in MM.YYYY format after " +
                        "'view/ m/'.");
            }
            break;
        case "y":
            if (!actionDetails.isEmpty()) {
                ExpenditureList.listExpensesByYear(actionDetails);
            } else {
                System.out.println("Please provide a year in YYYY format after 'view/ y/'.");
            }
            break;
        case "t":
            if (!actionDetails.isEmpty()) {
                ExpenditureList.listExpensesByType(actionDetails.toUpperCase());
            } else {
                System.out.println("Please provide a type after 'view/ t/'.");
            }
            break;
        default:
            System.out.println("Unknown view command: " + action);
            break;
        }
    }

    /**
     * Main method to split user commands into
     * the separate CantVas functionalities
     *
     * @param input command input by user
     * @param expenses expenditure to add
     * @param timetable class to addg
     * @return does this while true
     */
    public boolean userCommand(String input, ExpenditureList expenses, TimetableList timetable) {
        assert input != null;
        String command;
        int index = input.indexOf(" ");
        if (index != -1) {
            command = input.substring(0, index).trim().toLowerCase();
        } else {
            command = input.trim().toLowerCase();
        }
        switch (command) {
        case "gpastep":
            GPACommand.processGPACommand();
            UI.printHelpMessage();
            break;
        case "gc":
            ProcessGCCommand gcCommand = new ProcessGCCommand();
            gcCommand.processGCCommand(input);
            break;
        case "exit":
            UI.printExitMessage();
            Storage.writeToFile(expenses);
            return true;
        case "help":
            UI.printHelpMessage();
            break;
        case "tt/":
            TimetableParser.parseTimetable(input);
            break;
        case "e/":
            processExpenditureUserCommand(input);
            break;
        case "gpalist":
            ProcessListCommand listCommand = new ProcessListCommand();
            listCommand.processModuleListCommand();
            System.out.println("Back to main menu.");
            break;
        default:
            System.out.println("Unknown command. Please try again! Type 'help' for more information!");
            break;
        }
        return false;
    }


}
