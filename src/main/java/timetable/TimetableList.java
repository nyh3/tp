package timetable;

import exceptions.InvalidInputFormatException;
import cantvasUI.UI;

public class TimetableList {
    public static int classCount;
    public static int[] classCountDay;
    public static final int NUM_DAYS = 5;
    public static final int HOURS_PER_DAY = 24;
    private static final String DAY_KEYWORD = "day/";
    private static Days[][] timetable;
    private static final String CODE_KEYWORD = " code/";
    private static final String TIME_KEYWORD = " time/";
    private static final String DURATION_KEYWORD = " duration/";
    private static final String LOCATION_KEYWORD = " location/";

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
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            String classDayPart = parts[1].trim();

            parts = classDayPart.split(CODE_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class code.");
            }
            int classDay = Integer.parseInt(parts[0].trim());
            String classCodePart = parts[1].trim();

            parts = classCodePart.split(TIME_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class time.");
            }
            String classCode = parts[0].trim();
            String classTimePart = parts[1].trim();

            parts = classTimePart.split(DURATION_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class duration.");
            }
            int classTime = Integer.parseInt(parts[0].trim());
            String classDurationPart = parts[1].trim();

            parts = classDurationPart.split(LOCATION_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class location.");
            }
            int classDuration = Integer.parseInt(parts[0].trim());
            String classLocation = parts[1].trim();

            // check if all requirements met to add class
            if (isAbleToAddClass(classTime, classDay, classDuration)) {
                return;
            }

            //add class
            while(classDuration > 0) {
                timetable[classDay - 1][classTime - 1] = new Days(classCode, classTime, classDuration, classLocation);
                classCountDay[classDay - 1]++;
                classDuration--;
                classTime++;
                classCount++;
            }
            userAddedMessage(userAdded);
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Must be an integer");
        }
    }

    /**
     * Combines all checks to verify if the class, time and duration
     * is a valid input with not timetableclashes
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
            System.out.println("Invalid class duration");
            return true;
        }
        if (!isSlotAvailable(classDay, classTime, classDuration)) {
            System.out.println("There's already a class scheduled at this time.");
            return true;
        }
        return false;
    }
    
    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Class added successfully.");
        }
    }

    public static void deleteClass(String details) {
        try {
            String[] parts = details.split(DAY_KEYWORD, 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            String classDayPart = parts[1].trim();
            parts = classDayPart.split(TIME_KEYWORD, 2);

            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class time.");
            }
            int classDay = Integer.parseInt(parts[0].trim());
            int classTime = Integer.parseInt(parts[1].trim());

            if (!isValidDay(classDay) || !isValidClassTime(classTime)) {
                return;
            }

            if (timetable[classDay - 1][classTime - 1] != null) {
                String classCode = timetable[classDay - 1][classTime - 1].getClassCode();
                timetable[classDay - 1][classTime - 1] = null;
                classCountDay[classDay - 1]--;
                classCount--;

                // Move down the current spot and remove that class code
                deleteClassOccurrences(classTime, classDay, classCode);
                System.out.println("Class removed successfully.");
            } else {
                System.out.println("Class not found.");
            }
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Must be an integer");
        }
    }

    /**
     * used with deleteClass method
     *  iterates through from the specified time
     *  and deletes classes with the same code,
     *  stops when a different class code is reached
     *
     * @param classTime start time of class
     * @param classDay day of class 1-5 for monday-friday
     * @param classCode module code of class
     */
    private static void deleteClassOccurrences(int classTime, int classDay, String classCode) {
        for (int hour = classTime; hour < HOURS_PER_DAY; hour++) {
            if (timetable[classDay - 1][hour] != null
                    && timetable[classDay - 1][hour].getClassCode().equals(classCode)) {
                timetable[classDay - 1][hour] = null;
                classCountDay[classDay - 1]--;
                classCount--;
            } else {
                break;  // Stop iterating once a different class is encountered
            }
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
            System.out.println("Day of the week does not exist");
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
        if (classTime < 1 || classTime >= HOURS_PER_DAY) {
            System.out.println("Time of the day does not exist");
            return false;
        }
        return true;
    }

    private static boolean isSlotAvailable(int classDay, int classTime, int classDuration) {
        for (int i = classTime - 1 ; i < classTime + classDuration - 1 ; i++) {
            if (timetable[classDay-1][i] != null) {
                return false;
            }
        }
        return true;
    }

    public static void listByDay(String day) {
        try {
            int classDay = Integer.parseInt(day);
            if (classDay < 1 || classDay > NUM_DAYS) {
                System.out.println("Day of the week does not exist");
                return;
            }
            if (classCountDay[classDay - 1] == 0) {
                System.out.println("No class on that day");
            } else {
                UI.printTimetableByDay(timetable[classDay - 1]);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + "Must be an integer");
        }
    }

    public static void listTimetableByOrderOfDays() {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int day = 0; day < NUM_DAYS; day++) {
            System.out.println(daysOfWeek[day] + ":");
            boolean hasClasses = false;
            for (int hour = 0; hour < HOURS_PER_DAY; hour++) {
                Days classAtTime = timetable[day][hour];
                if (classAtTime != null) {
                    hasClasses = true;
                    System.out.println(" - " + classAtTime.toString());
                }
            }
            if (!hasClasses) {
                System.out.println(" No classes scheduled.");
            }
            System.out.println();
        }
    }

}
