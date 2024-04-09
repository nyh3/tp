package seedu.duke;

import gpa.GPAMain;
import gpa.ModuleList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GPATest {
    @Test
    void testCalculateNewGPA_allAs() {
        double currentGPA = 4.0;
        int totalAccumulatedCredits = 120;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("Module1", 4, "A");
        moduleList.addModule("Module2", 4, "A");
        moduleList.addModule("Module3", 4, "A");
        moduleList.addModule("Module4", 2, "A");

        double expectedGPA = 4.10; // Ensure this expected value is correct based on your GPA calculation logic
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01, "The GPA calculated for all A's did not match the expected value");
    }
}
