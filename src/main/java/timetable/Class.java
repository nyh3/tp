package timetable;

public class Class {

    protected String classCode;
    protected int classTime;
    protected int classDuration;
    protected String classLocation;

    /**
     * Constructor for Class.
     *
     * @param classCode The code of the class.
     * @param classTime The time of the class.
     * @param classDuration The duration of the class.
     * @param classLocation The location of the class.
     */
    public Class(String classCode, int classTime, int classDuration, String classLocation) {
        this.classCode = classCode;
        this.classTime = classTime;
        this.classDuration = classDuration;
        this.classLocation = classLocation;
    }

    /**
     * Retrieves the code of the class.
     *
     * @return The code of the class.
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Returns a string representation of the class for storage.
     *
     * @return A string representation of the class for storage.
     */
    public String toStringStorage() {
        return classCode + " | " + classTime + " | " + classDuration + " | " + classLocation;
    }

    /**
     * Returns a string representation of the class.
     *
     * @return A string representation of the class.
     */
    @Override
    public String toString() {
        return "Code: " + classCode + ", Class Time: " + classTime + ", Duration: " + classDuration +
                ", Location: " + classLocation;
    }

    /**
     * Returns a string representation of the class for a specific day.
     *
     * @return A string representation of the class for a specific day.
     */
    public String toStringDay() {
        String time24Hour = String.format("%02d", classTime);
        time24Hour += ":00";
        return " | " + time24Hour + " | " + classCode + " | " + classLocation + " | ";
    }
}
