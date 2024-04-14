package seedu.duke;

import gpa.GPAMain;
import gpa.ModuleList;
import gpa.Module;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

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

    @Test
    public void testToString() {
        Module module = new Module("CS1010", 4, "A");
        String result = module.toString();
        String expected = "Module{moduleName='CS1010', modularCredit=4, expectedGrade='A'}";
        assertEquals(expected, result, "The toString method should return the correct string " +
                "representation of the module.");
    }

    @Test
    void testGetTotalModularCredits() {
        ModuleList list = new ModuleList();
        list.addModule("CS1010", 4, "A");
        list.addModule("CS1020", 2, "B");

        int totalCredits = list.getTotalModularCredits();

        assertEquals(6, totalCredits, "Total credits should be the sum of module credits.");
    }

    @BeforeEach
    void setUp() {
        ModuleList moduleList = new ModuleList();
    }

    @Test
    void testGetModuleCountInitially() {
        assertEquals(0, ModuleList.getModuleCount(), "Initially, module count should be 0.");
    }

    @Test
    void testGetModuleCountAfterAddingModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS1010", 4, "A");
        moduleList.addModule("CS1020", 2, "B");
        assertEquals(2, ModuleList.getModuleCount(), "Module count should be 2 after " +
                "adding two modules.");
    }

    @Test
    void testGetModuleCountAfterRemovingModule() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS1010", 4, "A");
        moduleList.addModule("CS1020", 2, "B");
        moduleList.removeModule(0);
        assertEquals(1, ModuleList.getModuleCount(), "Module count should be 1 after " +
                "removing one module.");
    }

    @Test
    void testGetModuleCountAfterClearingModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS1010", 4, "A");
        moduleList.addModule("CS1020", 2, "B");
        moduleList.clearModules();
        assertEquals(0, ModuleList.getModuleCount(), "Module count should be 0 after clearing " +
                "all modules.");
    }

}
