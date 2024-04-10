package timetable;

public class Class {

    protected String classCode;
    protected int classTime;
    protected int classDuration;
    protected String classLocation;

    public Class(String classCode, int classTime, int classDuration, String classLocation) {
        this.classCode = classCode;
        this.classTime = classTime;
        this.classDuration = classDuration;
        this.classLocation = classLocation;
    }

    public String getClassCode() {
        return classCode;
    }

    public int getClassDuration() {
        return classDuration;
    }

    public int getClassTime() {
        return classTime;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public String toStringStorage() {
        return classCode + " | " + classTime + " | " + classDuration + " | " + classLocation;
    }

    @Override
    public String toString() {
        return "Code: " + classCode + ", Class Time: " + classTime + ", Duration: " + classDuration +
                ", Location: " + classLocation;
    }

    public String toStringDay() {
        String time24Hour = String.format("%02d", classTime);
        time24Hour += ":00";
        return " | " + time24Hour + " | " + classCode + " | " + classLocation + " | ";
    }
}
