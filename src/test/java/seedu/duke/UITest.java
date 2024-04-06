package seedu.duke;

import CantvasUI.UI;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

    @Test
    void printHelpMessage() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        UI.printHelpMessage();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Cantvas Help"));
        assertTrue(output.contains("Expenditure help"));
        assertTrue(output.contains("Timetable help"));
        assertTrue(output.contains("GPA help"));
        assertTrue(output.contains("To input expenses, use format:"));
        assertTrue(output.contains("To input expenses with type, use format:"));
        assertTrue(output.contains("To delete saved expenses, use format:"));
        assertTrue(output.contains("To clear the entire expenditure list, use format:"));
        assertTrue(output.contains("To List saved expenses, use format:"));
        assertTrue(output.contains("To view saved expenses by month, use format:"));
        assertTrue(output.contains("To view saved expenses by year, use format:"));
        assertTrue(output.contains("To view saved expenses by type, use format:"));
        assertTrue(output.contains("To input class to timetable, use format:"));
        assertTrue(output.contains("To delete saved class, use format:"));
        assertTrue(output.contains("To view you timetable, use format"));
        assertTrue(output.contains("To Use gpa calculator, type 'gpa' to continue"));
        assertTrue(output.contains("To end the program, type 'exit'"));
    }

    @Test
    void printMotivationQuote() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        UI.printMotivationQuote();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Here is your motivational quote of the day:"));
    }

}
