package storage;

import expenditure.ExpenditureList;
import gpa.ModuleList;
import gpa.Module;
import timetable.Class;
import timetable.TimetableList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private static final String EXPENDITURE_FILE_PATH = "./data/expenditure.txt";
    private static final String TIMETABLE_FILE_PATH = "./data/timetable.txt";
    private static final String GPA_FILE_PATH = "./data/gpa.txt";

    /**
     * Creates a new file of the specified type if it does not exist.
     *
     * @param type The type of file to create ("expenditure", "timetable", or "gpa")
     */
    public static void createNewFile(String type) {
        File file = null;
        if (type.equals("expenditure")) {
            file = new File(EXPENDITURE_FILE_PATH);
        } else if (type.equals("timetable")){
            file = new File(TIMETABLE_FILE_PATH);
        } else if (type.equals("gpa")){
            file = new File(GPA_FILE_PATH);
        }
        assert file != null;
        File directory = new File(file.getParent());
        try {
            createDirectoryAndFile(directory, file);
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
        assert directory.exists();
    }

    /**
     * Creates a directory and a new file if they do not exist.
     *
     * @param directory The directory in which the file should be created.
     * @param file      The file to create.
     * @throws IOException If an I/O error occurs while creating the directory or file.
     */
    private static void createDirectoryAndFile(File directory, File file) throws IOException {
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory: " + directory.getAbsolutePath());
            }
        }
        if (!file.createNewFile()) {
            System.out.println("Failed to create new file: " + file.getAbsolutePath());
        }
    }

    /**
     * Reads expenditure data from the expenditure file in startup and returns an ExpenditureList object.
     *
     * @return The ExpenditureList object containing the read expenditure data.
     */
    public static ExpenditureList readExpenditureFile() {
        ExpenditureList expenses = new ExpenditureList();
        File file = new File(EXPENDITURE_FILE_PATH);
        if (!file.exists()) {
            createNewFile("expenditure");
            return expenses;
        }
        assert file.exists() : "file should exist";
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String expenditure = processExpenditure(line);
                if (expenditure != null) {
                    ExpenditureList.addExpenditure(expenditure, false);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return expenses;
    }

    /**
     * Reads GPA data from the GPA file in startup and returns a ModuleList object.
     *
     * @return The ModuleList object containing the read GPA data.
     */
    public static ModuleList readGPAFile() {
        ModuleList modules = new ModuleList();
        File file = new File(GPA_FILE_PATH);
        if (!file.exists()) {
            createNewFile("gpa");
            return modules;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Module module = processModule(line);
                if (module != null) {
                    modules.addModule(module.getModuleName(), module.getModularCredit(), module.getExpectedGrade());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading GPA file: " + e.getMessage());
        }
        return modules;
    }

    /**
     * Reads timetable data from the timetable file and returns a TimetableList object.
     *
     * @return The TimetableList object containing the read timetable data.
     */
    public static TimetableList readTimetableFile() {
        TimetableList timetable = new TimetableList();
        File file = new File(TIMETABLE_FILE_PATH);
        if (!file.exists()) {
            createNewFile("timetable");
            return timetable;
        }
        assert file.exists() : "file should exist";
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String schedule = processTimetable(line);
                if (schedule != null) {
                    TimetableList.addClass(schedule, false);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return timetable;
    }

    /**
     * Processes a line of expenditure data and returns the formatted expenditure string.
     * to be added into the array when loading from data file
     *
     * @param line The line of expenditure data to process.
     * @return The formatted expenditure string.
     */
    private static String processExpenditure(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) {
            return null;
        }
        return ("d/" + parts[0] + "t/" + parts[1] + "amt/" + parts[2] + "date/" + parts[3]);
    }

    /**
     * Processes a line of GPA data and returns the corresponding Module object.
     * to be added into the array when loading from data file
     *
     * @param line The line of GPA data to process.
     * @return The Module object representing the GPA data.
     */
    private static Module processModule(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 3) {
            try {
                String moduleName = parts[0];
                int modularCredit = Integer.parseInt(parts[1]);
                String expectedGrade = parts[2];
                return new Module(moduleName, modularCredit, expectedGrade);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing module credit: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Processes a line of timetable data and returns the formatted timetable string.
     * to be added into the array when loading from data file
     *
     * @param line The line of timetable data to process.
     * @return The formatted timetable string.
     */
    private static String processTimetable(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 5) {
            return null;
        }
        return ("day/" + parts[0] + "code/" + parts[1] +
                "time/" + parts[2] + "duration/" + parts[3] + "location/" + parts[4]);
    }

    /**
     * Writes expenditure data to the expenditure file.
     *
     * @param expenses The ExpenditureList object containing the expenditure data to write.
     */
    public static void writeExpenditureToFile(ExpenditureList expenses) {
        try {
            PrintWriter fw = new PrintWriter(EXPENDITURE_FILE_PATH);
            for (int i = 0; i < ExpenditureList.expenditureCount; i++) {
                fw.println(expenses.getExpenditure(i).toStringStorage());
            }
            fw.close();
            fw = new PrintWriter(TIMETABLE_FILE_PATH);
            writeTimetableToFIle(fw, TimetableList.getTimetable());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

    /**
     * Writes the timetable data to the timetable file.
     *
     * @param timetable The 2D array of Class objects representing the timetable data.
     */
    private static void writeTimetableToFIle(PrintWriter fw, Class[][] timetable) {
        for (int i = 0; i < 5; i++) {
            writeDayTimetableToFile(fw, timetable[i], i);
        }
    }

    /**
     * Writes the timetable data for a specific day to the timetable file.
     *
     * @param fw The PrintWriter object used to write to the file.
     * @param timetable The array of Class objects representing the timetable data for a specific day.
     * @param day The index of the day (0-4) for which the timetable data is being written.
     */
    private static void writeDayTimetableToFile(PrintWriter fw, Class[] timetable, int day) {
        for (int j = 0; j < 24; j++) {
            writeHourTimetableToFIle(fw, timetable[j], day);
        }
    }

    /**
     * Writes the timetable data for a specific hour to the timetable file.
     *
     * @param fw The PrintWriter object used to write to the file.
     * @param timetable The Class object representing the timetable data for a specific hour.
     * @param day The index of the day (0-4) for which the timetable data is being written.
     */
    private static void writeHourTimetableToFIle(PrintWriter fw, Class timetable, int day) {
        if (timetable != null) {
            fw.println((day + 1) + " | " + timetable.toStringStorage());
        }
    }

    /**
     * Writes module data to the GPA file.
     *
     * @param moduleList The ModuleList object containing the module data to write.
     */
    public static void writeModuleListToFile(ModuleList moduleList) {
        try {
            PrintWriter writer = new PrintWriter(GPA_FILE_PATH);
            for (Module module : moduleList.getModules()) {
                writer.println(module.getModuleName() + "|" +
                        module.getModularCredit() + "|" + module.getExpectedGrade());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to GPA file: " + e.getMessage());
        }
    }
}
