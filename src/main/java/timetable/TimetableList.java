package timetable;

import seedu.duke.InvalidInputFormatException;

import java.util.ArrayList;


public class TimetableList {

    public static int classCount;

    private static ArrayList<Days> mon;
    private static ArrayList<Days> tue;
    private static ArrayList<Days> wed;

    private static ArrayList<Days> thurs;
    private static ArrayList<Days> fri;

    public TimetableList() {
        mon = new ArrayList<>();
        tue = new ArrayList<>();
        wed = new ArrayList<>();
        thurs = new ArrayList<>();
        fri = new ArrayList<>();
        classCount = 0;
    }

    public ArrayList<Days> getMon() {
        return mon;
    }

    public ArrayList<Days> getTue() {
        return tue;
    }

    public ArrayList<Days> getWed() {
        return wed;
    }

    public ArrayList<Days> getThurs() {
        return thurs;
    }

    public ArrayList<Days> getFri() {
        return fri;
    }

    public static void addClass(String schedule, Boolean userAdded) {
        try {
            String[] parts = schedule.split("day/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            String classDayPart = parts[1].trim();

            parts = classDayPart.split(" code/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class code.");
            }
            String classDay = parts[0].trim();
            String classCodePart = parts[1].trim();

            parts = classCodePart.split(" time/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class time.");
            }
            String classCode = parts[0].trim();
            String classTimePart = parts[1].trim();

            parts = classTimePart.split(" duration/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class duration.");
            }
            String classTime = parts[0].trim();
            String classDurationPart = parts[1].trim();

            parts = classDurationPart.split(" location/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class location.");
            }
            String classDuration = parts[0].trim();
            String classLocation = parts[1].trim();

            ArrayList<Days> dayList = getDayList(classDay);
            if (dayList == null) {
                System.out.println("Day of the week does not exist");
                return;
            }
            dayList.add(new Days(classCode, classTime, classDuration, classLocation));
            userAddedMessage(userAdded);
            classCount++;
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Class added successfully.");
        }
    }

    public static void deleteClass(String details) {
        try {
            String[] parts = details.split("day/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            String classDayPart = parts[1].trim();

            parts = classDayPart.split(" code/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class code.");
            }
            String classDay = parts[0].trim();
            String classCodePart = parts[1].trim();

            parts = classCodePart.split(" time/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class time.");
            }
            String classCode = parts[0].trim();
            String classTime = parts[1].trim();

            ArrayList<Days> dayList = getDayList(classDay);
            if (dayList == null) {
                System.out.println("Invalid day of the week.");
                return;
            }

            Days classToRemove = null;
            for (Days day : dayList) {
                if (day.getClassCode().equals(classCode) && day.getClassTime().equals(classTime)) {
                    classToRemove = day;
                    break;
                }
            }

            if (classToRemove != null) {
                dayList.remove(classToRemove);
                classCount--;
                System.out.println("Class removed successfully.");
            } else {
                System.out.println("Class not found.");
            }
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Days> getDayList(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase()) {
        case "mon":
            return mon;
        case "tue":
            return tue;
        case "wed":
            return wed;
        case "thurs":
            return thurs;
        case "fri":
            return fri;
        default:
            return null;
        }
    }

}
