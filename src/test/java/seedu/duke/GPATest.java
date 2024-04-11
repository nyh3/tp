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

        assertEquals(expectedGPA, resultGPA, 0.01,
                "The GPA calculated for all A's did not match the expected value");
    }
    void testCalculateNewGPA_allBs() {
        double currentGPA = 4.0;
        int totalAccumulatedCredits = 120;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("Module1", 4, "B");
        moduleList.addModule("Module2", 4, "B");
        moduleList.addModule("Module3", 4, "B");
        moduleList.addModule("Module4", 2, "B");

        double expectedGPA = 3.95; // Ensure this expected value is correct based on your GPA calculation logic
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01,
                "The GPA calculated for all B's did not match the expected value");
    }

    @Test
    void testCalculateUpdatedGPA_mixedGrades() {
        double currentGPA = 3.5;
        int totalCredits = 60;
        ModuleList moduleList = new ModuleList();
        // Assuming the calculatePointsForGrade logic and GPA calculation logic is correctly implemented
        moduleList.addModule("Module1", 4, "A");
        moduleList.addModule("Module2", 4, "C");
        moduleList.addModule("Module3", 4, "B");
        moduleList.addModule("Module4", 2, "F");

        // Calculate expectedGPA based on your logic for GPA calculation
        double expectedGPA = 3.41;
        double resultGPA = GPAMain.calculateUpdatedGPA(currentGPA, totalCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01,
                "The GPA calculated for mixed grades did not match the expected value");
    }

    @Test
    void testCalculateNewGPA_withUnrecognizedGrade() {
        double currentGPA = 3.5;
        int totalAccumulatedCredits = 60;
        ModuleList moduleList = new ModuleList();

        // Adding modules with valid and invalid (unrecognized) grades
        moduleList.addModule("ValidModule1", 4, "A");
        moduleList.addModule("InvalidModule1",
                4, "Z"); // Unrecognized grade

        // Expected GPA calculation considering "Z" as 0.00
        // Ensure this expected value is correct based on your GPA calculation logic,
        // considering unrecognized grades as 0.00 in the calculation
        double expectedGPA = 3.38;
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01,
                "The GPA calculation did not handle an unrecognized grade as expected.");
    }
}
