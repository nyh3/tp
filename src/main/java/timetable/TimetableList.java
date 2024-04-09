package timetable;

import exceptions.InvalidInputFormatException;
import cantvasui.UI;

public class TimetableList {
    public static int classCount;
    public static int[] classCountDay;
    protected static final int NUM_DAYS = 5;
    protected static final int HOURS_PER_DAY = 24;
    private static final String DAY_KEYWORD = "day/";
    private static Days[][] timetable;
    private static final String CODE_KEYWORD = " code/";
    private static final String TIME_KEYWORD = " time/";
    private static final String DURATION_KEYWORD = " duration/";
    private static final String LOCATION_KEYWORD = " location/";
    private static final String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public TimetableList() {
        timetable = new Days[NUM_DAYS][HOURS_PER_DAY];
        classCountDay = new int[NUM_DAYS];
        classCount = 0;
    }

    public static Days[][] getTimetable() {
        return timetable;
    }

    public static void addClass(String schedule, Boolean userAdded) {
        try {
            String[] parts = schedule.split(DAY_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Incomplete command. Please refer to the help message " +
                        "for format.");
            }

            if (parts[1].trim().isEmpty()) {
                throw new InvalidInputFormatException("Missing <day> and code/ <classCode> " +
                        "time/ <hh> duration/ <duration> location/ <location>.");
            }

            String classDayPart = parts[1].trim();

            parts = classDayPart.split(CODE_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Incomplete command. Please refer to the help message " +
                        "for format.");
            }

            if (parts[1].trim().isEmpty()) {
                throw new InvalidInputFormatException("Missing <code> and time/ <hh> " +
                        "duration/ <duration> location/ <location>.");
            }

            int classDay = Integer.parseInt(parts[0].trim());
            String classCodePart = parts[1].trim();

            parts = classCodePart.split(TIME_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Incomplete command. Please refer to the help message " +
                        "for format.");
            }

            if (parts[1].trim().isEmpty()) {
                throw new InvalidInputFormatException("Missing <time> and duration/ <duration> " +
                        "location/ <location>.");
            }

            String classCode = parts[0].trim();

            if (!isValidClassCode(classCode)) {
                return;
            }

            String classTimePart = parts[1].trim();

            parts = classTimePart.split(DURATION_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Incomplete command. Please refer to the help message " +
                        "for format.");
            }

            if (parts[1].trim().isEmpty()) {
                throw new InvalidInputFormatException("Missing <duration> and location/ <location>.");
            }

            int classTime = Integer.parseInt(parts[0].trim());
            String classDurationPart = parts[1].trim();

            parts = classDurationPart.split(LOCATION_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Incomplete command. Please refer to the help message " +
                        "for format.");
            }

            if (parts[1].trim().isEmpty()) {
                throw new InvalidInputFormatException("Missing <location> details.");
            }

            if (parts[1].trim().length() > 20) {
                throw new InvalidInputFormatException("Class location details should be within 20 characters.");
            }

            int classDuration = Integer.parseInt(parts[0].trim());
            String classLocation = parts[1].trim();

            if (isAbleToAddClass(classTime, classDay, classDuration)) {
                return;
            }

            //add class
            while(classDuration > 0) {
                timetable[classDay - 1][classTime] = new Days(classCode, classTime, classDuration, classLocation);
                classCountDay[classDay - 1]++;
                classDuration--;
                classTime++;
                classCount++;
            }
            userAddedMessage(userAdded);
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Must be an integer.");
        }
    }

    private static boolean isValidClassCode(String classCode) {
        if (classCode.length() > 7) {
            System.out.println("Class code should be within 7 characters.");
            return false;
        }
        return true;
    }

    /**
     * Combines all checks to verify if the class, time and duration
     * is a valid input with not timetable clashes
     * @param classTime Start time of class
     * @param classDay Day of class
     * @param classDuration Number of hours of class
     * @return true valid input, false if unable to add
     */
    private static boolean isAbleToAddClass(int classTime, int classDay, int classDuration) {
        if (!isValidClassTime(classTime) || !isValidDay(classDay)) {
            return true;
        }
        if (classTime + classDuration >= HOURS_PER_DAY) {
            System.out.println("Classes should not last overnight.");
            return true;
        }
        if (classDuration < 1 || classDuration > (HOURS_PER_DAY - classTime)) {
            System.out.println("Invalid class duration.");
            return true;
        }
        if (!isSlotAvailable(classDay, classTime, classDuration)) {
            System.out.println("There's already a class scheduled during this time period.");
            return true;
        }
        return false;
    }
    
    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Class added successfully.");
        }
    }

    /**
     * Deletes the specified class of the user within the
     * specified day
     * @param details String of input from user to process
     */
    public static void deleteClass(String details) {
        try {
            String[] parts = details.split(DAY_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            String classDayPart = parts[1].trim();
            parts = classDayPart.split(CODE_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class code.");
            }
            int classDay = Integer.parseInt(parts[0].trim());
            String classCode = parts[1].trim();

            if (!isValidDay(classDay)) {
                return;
            }

            boolean classDeleted = false;

            // Iterate over the timetable for the specified day
            classDeleted = isClassDeleted(classDay, classCode, classDeleted);

            classDeletedMessage(classDeleted);

        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Must be an integer.");
        }
    }

    /**
     * Handles the deletion of class.
     * iterated through the specified day and deletes the class
     * code if found and returns true. if class can't be found,
     * returns false
     * @param classDay day to search through
     * @param classCode class to delete
     * @param classDeleted true of deleted, false if not deleted
     * @return true of deleted, false if not deleted
     */
    private static boolean isClassDeleted(int classDay, String classCode, boolean classDeleted) {
        for (int hour = 0; hour < HOURS_PER_DAY; hour++) {
            if (timetable[classDay - 1][hour] != null) {
                if (timetable[classDay - 1][hour].getClassCode().equals(classCode)) {
                    // Found a class with the same class code on the specified day, delete it
                    timetable[classDay - 1][hour] = null;
                    classCountDay[classDay - 1]--;
                    classCount--;
                    classDeleted = true;
                }
            }
        }
        return classDeleted;
    }

    private static void classDeletedMessage(boolean classDeleted) {
        if (classDeleted) {
            System.out.println("Class removed successfully.");
        } else {
            System.out.println("Class not found. Please ensure day and class code has already been saved.");
        }
    }


    /**
     * Check if day input by user is valid
     *
     * @param classDay day of class 1-5 for monday-friday
     * @return True if valid day or False if not valid
     */
    private static boolean isValidDay(int classDay) {
        if (classDay < 1 || classDay > NUM_DAYS) {
            System.out.println("Day of the week does not exist.");
            return false;
        }
        return true;
    }

    /**
     * checks if classTime input by user is valid
     *
     * @param classTime start time of class
     * @return True if valid day or False if not valid
     */
    private static boolean isValidClassTime(int classTime) {
        if (classTime < 0 || classTime >= HOURS_PER_DAY) {
            System.out.println("Time of the day does not exist.");
            return false;
        }
        return true;
    }

    private static boolean isSlotAvailable(int classDay, int classTime, int classDuration) {
        for (int i = classTime; i < classTime + classDuration; i++) {
            if (timetable[classDay-1][i] != null) {
                return false;
            }
        }
        return true;
    }

    public static void listByDay(String day) {
        try {
            int classDay = Integer.parseInt(day);
            if (!isValidDay(classDay)) {
                return;
            }
            if (classCountDay[classDay - 1] == 0) {
                System.out.println("No class on that day.");
            } else {
                System.out.println(daysOfWeek[classDay - 1]);
                UI.printTimetableByDay(timetable[classDay - 1]);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + "Must be an integer.");
        }
    }

    /**
     * Main method to list entire timetable
     * prints classes from monday to friday,
     * Days with no classes will also be printed
     * but indicated as "No classes scheduled"
     */
    public static void listTimetableByOrderOfDays() {
        for (int day = 0; day < NUM_DAYS; day++) {
            System.out.println(daysOfWeek[day] + ":");
            boolean hasClasses = false;
            for (int hour = 0; hour < HOURS_PER_DAY; hour++) {
                Days classAtTime = timetable[day][hour];
                if (classAtTime != null) {
                    hasClasses = true;
                    System.out.println(" - " + classAtTime);
                }
            }
            if (!hasClasses) {
                System.out.println(" No classes scheduled.");
            }
            System.out.println();
        }
    }

}
