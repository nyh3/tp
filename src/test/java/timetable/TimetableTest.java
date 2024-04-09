package timetable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimetableTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        new TimetableList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddClassValidInput() {
        TimetableList.addClass("day/1 code/MA1508E time/10 duration/2 location/E1-06-10", true);
        assertEquals("Class added successfully.", outContent.toString().trim());

        outContent.reset();

        TimetableList.addClass("day/2 code/CS2113 time/08 duration/1 location/COM1-02-03", true);
        assertEquals("Class added successfully.", outContent.toString().trim());

        outContent.reset();

        TimetableList.addClass("day/1 code/EE2026 time/16 duration/2 location/E4-03-07", true);
        assertEquals("Class added successfully.", outContent.toString().trim());
    }

    @Test
    public void testAddClassInvalidDayFormat() {
        TimetableList.addClass("day/XYZ code/MA1508E time/10 duration/2 location/E1-06-10", false);
        assertEquals("For input string: \"XYZ\" Must be an integer.",outContent.toString().trim());

        outContent.reset();

        TimetableList.addClass("day/-2 code/CS2113 time/08 duration/1 location/COM1-02-03", false);
        assertEquals("Day of the week does not exist.",outContent.toString().trim());

        outContent.reset();

        TimetableList.addClass("day/123456789 code/EE2026 time/16 duration/2 location/E4-03-07",
                false);
        assertEquals("Day of the week does not exist.",outContent.toString().trim());
    }

    @Test
    public void testAddClassInvalidTime() {
        TimetableList.addClass("day/1 code/CG2023 time/25 duration/2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Time of the day does not exist."));

        outContent.reset();

        TimetableList.addClass("day/1 code/CG2027 time/233333 duration/2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Time of the day does not exist."));

        outContent.reset();

        TimetableList.addClass("day/1 code/CG2027 time/-233333 duration/2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Time of the day does not exist."));
    }

    @Test
    public void testAddClassInvalidDuration() {
        TimetableList.addClass("day/1 code/CS1231 time/10 duration/0 location/Room 101", false);
        assertTrue(outContent.toString().contains("Invalid class duration."));

        outContent.reset();

        TimetableList.addClass("day/1 code/CS1231 time/23 duration/-2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Invalid class duration."));

        outContent.reset();

        TimetableList.addClass("day/1 code/CS1231 time/23 duration/2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Classes should not last overnight."));
    }

    @Test
    public void testAddClassWithClash() {
        TimetableList.addClass("day/1 code/CDE2501 time/10 duration/2 location/Room 101", true);
        outContent.reset();
        TimetableList.addClass("day/1 code/CG2111A time/10 duration/1 location/Room 102", false);
        assertTrue(outContent.toString().contains("There's already a class scheduled at this time."));
    }

    @Test
    public void testDeleteClassValidInput() {
        TimetableList.addClass("day/2 code/LAG2101 time/12 duration/1 location/AS4-06-09", true);
        outContent.reset();
        TimetableList.deleteClass("day/2 code/LAG2101");
        assertTrue(outContent.toString().contains("Class removed successfully."));
    }

    @Test
    public void testDeleteClassNotFound() {
        TimetableList.deleteClass("day/2 code/CDE2000");
        assertTrue(outContent.toString().contains("Class not found. Please ensure day and class code has " +
                "already been saved."));
    }

    @Test
    public void testInvalidClassCode() {
        TimetableList.addClass("day/1 code/ABCDEFGH time/10 duration/2 location/E1-06-10", false);
        assertEquals("Class code should be within 7 characters.", outContent.toString().trim());
    }

    @Test
    public void testEmptyClassDay() {
        TimetableList.addClass("day/", false);
        assertEquals("Missing <day> and code/ <classCode> time/ <hh> duration/ " +
                "<duration> location/ <location>.", outContent.toString().trim());
    }

    @Test
    public void testMissingEntireCommand() {
        TimetableList.addClass("", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassDayWithOtherDetails() {
        TimetableList.addClass("day/ code/LAG2101 time/12 duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassDayWithOtherDetails() {
        TimetableList.addClass("code/LAG2101 time/12 duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassCode() {
        TimetableList.addClass("day/1 code/", false);
        assertEquals("Missing <code> and time/ <hh> duration/ <duration> location/ <location>.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassCode() {
        TimetableList.addClass("day/1", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassCodeWithOtherDetails() {
        TimetableList.addClass("day/1 code/ time/12 duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassCodeWithOtherDetails() {
        TimetableList.addClass("day/1 time/12 duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassTime() {
        TimetableList.addClass("day/1 code/CS1231 time/", false);
        assertEquals("Missing <time> and duration/ <duration> location/ <location>.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassTime() {
        TimetableList.addClass("day/1 code/CS1231", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassTimeWithOtherDetails() {
        TimetableList.addClass("day/1 code/LAG2101 time/ duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassTimeWithOtherDetails() {
        TimetableList.addClass("day/1 code/LAG2101 duration/1 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassDuration() {
        TimetableList.addClass("day/1 code/LAG2101 time/12 duration/", false);
        assertEquals("Missing <duration> and location/ <location>.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassDuration() {
        TimetableList.addClass("day/1 code/LAG2101 time/12", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassDurationWithOtherDetails() {
        TimetableList.addClass("day/1 code/LAG2101 time/12 duration/ location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testMissingClassDurationWithOtherDetails() {
        TimetableList.addClass("day/1 code/LAG2101 time/12 location/AS4-06-09", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testEmptyClassLocation() {
        TimetableList.addClass("day/1 code/LAG2101 time/10 duration/2 location/", false);
        assertEquals("Missing <location> details.", outContent.toString().trim());
    }

    @Test
    public void testMissingClassLocation() {
        TimetableList.addClass("day/1 code/LAG2101 time/10 duration/2", false);
        assertEquals("Incomplete command. Please refer to the help message for format.",
                outContent.toString().trim());
    }

    @Test
    public void testListClassesByOrderEmpty() {
        TimetableList.listTimetableByOrderOfDays();
        assertTrue(outContent.toString().contains("No classes scheduled."));
    }

    @Test
    public void testListClassesEmptyDay() {
        TimetableList.listByDay("1");
        assertTrue(outContent.toString().contains("No class on that day."));
    }

    @Test
    public void testAddClassLongLocation() {
        TimetableList.addClass("day/1 code/CS1231 time/10 duration/2 location/" +
                "ThisIsAVeryLongLocationNameThatExceedsTheLimit", false);
        assertEquals("Class location details should be within 20 characters.",
                outContent.toString().trim());
    }

    @Test
    public void testListClassesByOrder() {
        TimetableList.addClass("day/1 code/CS1231 time/10 duration/2 location/Room 101", true);
        TimetableList.addClass("day/2 code/CS2113 time/08 duration/1 location/COM1-02-03", true);
        TimetableList.addClass("day/1 code/EE2026 time/16 duration/2 location/E4-03-07", true);
        TimetableList.listTimetableByOrderOfDays();
        assertTrue(outContent.toString().contains("Monday"));
        assertTrue(outContent.toString().contains("Tuesday"));
        assertTrue(outContent.toString().contains("Wednesday"));
        assertTrue(outContent.toString().contains("Thursday"));
        assertTrue(outContent.toString().contains("Friday"));
    }

    @Test
    public void testListClassesByDay() {
        TimetableList.addClass("day/1 code/CS1231 time/10 duration/2 location/Room 101", true);
        TimetableList.addClass("day/2 code/CS2113 time/08 duration/1 location/COM1-02-03", true);
        TimetableList.addClass("day/1 code/EE2026 time/16 duration/2 location/E4-03-07", true);
        TimetableList.listByDay("1");
        assertTrue(outContent.toString().contains(" | 10:00 | CS1231 | Room 101 | "));
        assertTrue(outContent.toString().contains(" | 11:00 | CS1231 | Room 101 | "));
        assertTrue(outContent.toString().contains(" | 16:00 | EE2026 | E4-03-07 | "));
        assertTrue(outContent.toString().contains(" | 17:00 | EE2026 | E4-03-07 | "));
        assertTrue(!outContent.toString().contains(" | 08:00 | CS2113 | COM1-02-03 | "));
    }

    @Test
    public void testListClassesByDayInvalidDay() {
        TimetableList.listByDay("6");
        assertEquals("Day of the week does not exist.", outContent.toString().trim());

        outContent.reset();

        TimetableList.listByDay("");
        assertEquals("For input string: \"\"Must be an integer.", outContent.toString().trim());

        outContent.reset();

        TimetableList.listByDay("XYZ");
        assertEquals("For input string: \"XYZ\"Must be an integer.", outContent.toString().trim());
    }

    @Test
    public void testListClassesByDayEmpty() {
        TimetableList.listByDay("1");
        assertTrue(outContent.toString().contains("No class on that day."));
    }

}
