package gpa;

public class Module {
    private String moduleName; // New field for storing module name
    private int modularCredit;
    private String expectedGrade;

    public Module(String moduleName, int modularCredit, String expectedGrade) {
        this.moduleName = moduleName;
        this.modularCredit = modularCredit;
        this.expectedGrade = expectedGrade.toUpperCase(); // Assuming grades are stored in uppercase.
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getModularCredit() {
        return modularCredit;
    }

    public String getExpectedGrade() {
        return expectedGrade;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", modularCredit=" + modularCredit +
                ", expectedGrade='" + expectedGrade + '\'' +
                '}';
    }
}
