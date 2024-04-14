package ProcessCommand;

import expenditure.ExpenditureList;
import org.junit.jupiter.api.Test;
import processusercommands.ProcessCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessCommandTest {

    @Test
    public void testValidProcessExpenditureUserCommand() {

        ExpenditureList expenditureList = new ExpenditureList();
        ProcessCommand processCommand = new ProcessCommand();

        processCommand.userCommand("e/ add/ d/ Lunch t/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(1, ExpenditureList.expenditureCount);
    }

    @Test
    public void testInvalidProcessExpenditureUserCommand() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ExpenditureList expenditureList = new ExpenditureList();
        ProcessCommand processCommand = new ProcessCommand();

        // missing / for d/
        processCommand.userCommand("e/ add/ d Lunch t/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Invalid input format for description.", outContent.toString().trim());
        outContent.reset();

        //amt and t swap
        processCommand.userCommand("e/ add/ d/ Lunch amt/Food t/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Invalid input format for amount.", outContent.toString().trim());
        outContent.reset();

        // Call the userCommand method with the command to add an expenditure
        processCommand.userCommand("e/ add/ d/ Lunch amt/Food t/10.12 date02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Invalid input format for amount.", outContent.toString().trim());
        outContent.reset();

        // completely wrong input
        processCommand.userCommand("/e add/ d/ Lunch amt/Food t/10.12 date02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type " +
                "'help' for more information!", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void testSpacesBetweenCommands() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ExpenditureList expenditureList = new ExpenditureList();
        ProcessCommand processCommand = new ProcessCommand();

        // missing spaces 1
        processCommand.userCommand("e/add/ d/ Lunch t/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!"
                , outContent.toString().trim());
        outContent.reset();

        // missing spaces 2
        processCommand.userCommand("e/add/d/ Lunch t/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!"
                , outContent.toString().trim());
        outContent.reset();

        // missing spaces 3
        processCommand.userCommand("e/add/d/Lunch t/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!"
                , outContent.toString().trim());
        outContent.reset();

        // missing spaces 4
        processCommand.userCommand("e/add/d/Luncht/Food amt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!", outContent.toString().trim());
        outContent.reset();

        // missing spaces 5
        processCommand.userCommand("e/add/d/Luncht/Foodamt/10.12 date/02.02.2023", expenditureList);
        assertEquals(0, ExpenditureList.expenditureCount);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!", outContent.toString().trim());
        outContent.reset();

    }

    @Test
    public void invalidCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProcessCommand processCommand = new ProcessCommand();
        ExpenditureList expenditureList = new ExpenditureList();

        processCommand.userCommand(" ", expenditureList);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!", outContent.toString().trim());
        outContent.reset();

        processCommand.userCommand("1111111111111", expenditureList);
        assertEquals("Unknown command. Please try again! Type 'help' for more information!", outContent.toString().trim());
        outContent.reset();

    }

    @Test
    public void helpCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProcessCommand processCommand = new ProcessCommand();
        ExpenditureList expenditureList = new ExpenditureList();

        processCommand.userCommand("help", expenditureList);
        assertTrue(outContent.toString().contains("Cantvas Help"));
        outContent.reset();
    }

}
