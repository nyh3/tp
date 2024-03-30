package storage;

import expenditure.ExpenditureList;
import seedu.duke.ProcessCommand;
import timetable.Days;
import timetable.TimetableList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String EXPENDITURE_FILE_PATH = "./data/expenditure.txt";
    private static final String TIMETABLE_FILE_PATH = "./data/timetable.txt";

    private static void createNewFile(String type) {
        File file = null;
        if (type.equals("expenditure")) {
            file = new File(EXPENDITURE_FILE_PATH);
        } else if (type.equals("timetable")){
            file = new File(TIMETABLE_FILE_PATH);
        }
        assert file != null;
        File directory = new File(file.getParent());
        try {
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                }
            }
            if (!file.createNewFile()) {
                System.out.println("Failed to create new file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
        assert directory.exists();
    }

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
                ExpenditureList.addExpenditure(expenditure,false);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return expenses;
    }

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
                TimetableList.addClass(schedule,false);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return timetable;
    }

    private static String processExpenditure(String line) {
        String[] parts = line.split("\\|");
        return ("d/" + parts[0] + "t/" + parts[1] + "amt/" + parts[2] + "date/" + parts[3]);
    }

    private static String processTimetable(String line) {
        String[] parts = line.split("\\|");
        return ("day/" + parts[0] + "code/" + parts[1] +
                "time/" + parts[2] + "duration/" + parts[3] + "location/" + parts[4]);
    }

    public static void writeToFile(ExpenditureList expenses, TimetableList timetable) {
        try {
            PrintWriter fw = new PrintWriter(EXPENDITURE_FILE_PATH);
            for (int i = 0; i < ExpenditureList.expenditureCount; i++) {
                fw.println(expenses.getExpenditure(i).toStringStorage());
            }
            fw.close();
            fw = new PrintWriter(TIMETABLE_FILE_PATH);
            writeTimetableToFIle(fw, timetable.getMon(), "mon");
            writeTimetableToFIle(fw, timetable.getTue(), "tue");
            writeTimetableToFIle(fw, timetable.getWed(), "wed");
            writeTimetableToFIle(fw, timetable.getThurs(), "thurs");
            writeTimetableToFIle(fw, timetable.getFri(), "fri");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

    private static void writeTimetableToFIle(PrintWriter fw, ArrayList<Days> timetable, String day) {
        for (Days classDetails : timetable) {
            fw.println(day + " | " + classDetails.toStringStorage());
        }
    }
}
