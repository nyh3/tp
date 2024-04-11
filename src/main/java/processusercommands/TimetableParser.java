package processusercommands;

import timetable.TimetableList;

public class TimetableParser {

    /**
     * When user wants to use Timetable functionalities,
     * this method checks the timetable specific functionality
     * that the user would like to use and passes the command
     * to the respective methods to handle
     * @param command user input to read and split
     */
    public static void parseTimetable(String command) {
        String[] commandParts = command.substring(3).trim().split("/", 2);
        if (commandParts.length < 2) {
            System.out.println("Incomplete command!");
            return;
        }

        String action = commandParts[0].trim();
        String details = commandParts[1].trim();

        switch (action) {
        case "add":
            TimetableList.addClass(details, true);
            break;
        case "del":
            TimetableList.deleteClass(details);
            break;
        case "list":
            TimetableList.listTimetableByOrderOfDays();
            break;
        case "list -d":
            TimetableList.listByDay(details);
            break;
        default:
            System.out.println("Unknown command " + details);
            break;
        }
    }
}
