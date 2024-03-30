package timetable;

public class Days {

    protected String classCode;
    protected String classTime;
    protected String classDuration;
    protected String classLocation;

    public Days(String classCode, String classTime, String classDuration, String classLocation) {
        this.classCode = classCode;
        this.classTime = classTime;
        this.classDuration = classDuration;
        this.classLocation = classLocation;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getClassDuration() {
        return classDuration;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public String toStringStorage() {
        return classCode + " | " + classTime + " | " + classDuration + " | " + classLocation;
    }

}
