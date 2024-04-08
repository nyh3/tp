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

        TimetableList.addClass("day/1 code/CG2027 time/23 duration/-2 location/Room 101", false);
        assertTrue(outContent.toString().contains("Invalid class duration."));
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
        outContent.reset(); // Clear the output stream before the next operation
        TimetableList.addClass("day/1 code/CG2111A time/10 duration/1 location/Room 102", false);
        assertTrue(outContent.toString().contains("There's already a class scheduled at this time."));
    }

    @Test
    public void testDeleteClassValidInput() {
        TimetableList.addClass("day/2 code/LAG2101 time/12 duration/1 location/AS4-06-09", true);
        outContent.reset(); // Clear the output stream before the next delete operation
        TimetableList.deleteClass("day/2 code/LAG2101");
        assertTrue(outContent.toString().contains("Class removed successfully."));
    }

    @Test
    public void testDeleteClassNotFound() {
        TimetableList.deleteClass("day/2 code/CDE2000");
        assertTrue(outContent.toString().contains("Class not found. Please ensure day and class code has " +
                "already been saved."));
    }
}
