package timetable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processusercommands.TimetableParser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TimetableParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testParseTimetableAdd() {
        TimetableParser.parseTimetable("tt/ add/description here");
        assertFalse(outContent.toString().contains("Adding class logic"), "Output should indicate that " +
                "adding class logic was called");
    }

    @Test
    void testParseTimetableDelete() {
        TimetableParser.parseTimetable("tt/ del/description here");
        assertFalse(outContent.toString().contains("Deleting class logic"), "Output should " +
                "indicate that deleting class logic was called");
    }

    @Test
    void testParseTimetableList() {
        TimetableParser.parseTimetable("tt/ list");
        assertFalse(outContent.toString().contains("Listing timetable"), "Output should indicate that " +
                "listing timetable logic was called");
    }

    @Test
    void testParseTimetableListByDay() {
        TimetableParser.parseTimetable("tt/ list -d/ Monday");
        assertFalse(outContent.toString().contains("Listing by day"), "Output should indicate that " +
                "listing by day logic was called");
    }

    @Test
    void testParseTimetableUnknownCommand() {
        TimetableParser.parseTimetable("tt/ foo/description here");
        assertTrue(outContent.toString().contains("Unknown command description here"), "Output " +
                "should indicate unknown command was encountered");
    }

    @Test
    void testParseTimetableIncompleteCommand() {
        TimetableParser.parseTimetable("tt/ add");
        assertTrue(outContent.toString().contains("Incomplete command!"), "Output should indicate " +
                "incomplete command was encountered");
    }
}
