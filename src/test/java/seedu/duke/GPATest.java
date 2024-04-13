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

        double expectedGPA = 4.10;
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01);
    }

    @Test
    void testCalculateNewGPA_allBs() {
        double currentGPA = 4.0;
        int totalAccumulatedCredits = 120;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("Module1", 4, "B");
        moduleList.addModule("Module2", 4, "B");
        moduleList.addModule("Module3", 4, "B");
        moduleList.addModule("Module4", 2, "B");

        double expectedGPA = 3.95;
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01);
    }

    @Test
    void testCalculateUpdatedGPA_mixedGrades() {
        double currentGPA = 3.5;
        int totalCredits = 60;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("Module1", 4, "A");
        moduleList.addModule("Module2", 4, "C");
        moduleList.addModule("Module3", 4, "B");
        moduleList.addModule("Module4", 2, "F");

        double expectedGPA = 3.41;
        double resultGPA = GPAMain.calculateUpdatedGPA(currentGPA, totalCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01);
    }

    @Test
    void testCalculateNewGPA_withUnrecognizedGrade() {
        double currentGPA = 3.5;
        int totalAccumulatedCredits = 60;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("ValidModule1", 4, "A");
        moduleList.addModule("InvalidModule1",
                4, "Z");

        double expectedGPA = 3.38;
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01);
    }

    @Test
    void testCalculateNewGPA_allFailingGrades() {
        double currentGPA = 3.5;
        int totalAccumulatedCredits = 60;
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("Module1", 4, "F");
        moduleList.addModule("Module2", 4, "F");
        moduleList.addModule("Module3", 4, "F");
        moduleList.addModule("Module4", 2, "F");

        double expectedGPA = 2.83;
        double resultGPA = GPAMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, moduleList);

        assertEquals(expectedGPA, resultGPA, 0.01);
    }



}
