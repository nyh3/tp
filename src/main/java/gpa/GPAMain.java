package gpa;


public class GPAMain {

    // Method to calculate new GPA with inputs provided as parameters
    public static double calculateNewGPA(double currentGPA, int totalAccumulatedCredits, ModuleList moduleList) {
        double totalPoints = currentGPA * totalAccumulatedCredits;
        int newCredits = 0;

        for (Module module : moduleList.getModules()) {
            double gradePoints = calculatePointsForGrade(module.getExpectedGrade());
            totalPoints += gradePoints * module.getModularCredit();
            newCredits += module.getModularCredit();
        }

        return totalPoints / (totalAccumulatedCredits + newCredits);
    }

    static double calculatePointsForGrade(String grade) {
        // Define GPA points for grades
        switch (grade) {
        case "A+":
        case "A":
            return 5.00;
        case "A-":
            return 4.50;
        case "B+":
            return 4.00;
        case "B":
            return 3.50;
        case "B-":
            return 3.00;
        case "C+":
            return 2.50;
        case "C":
            return 2.00;
        case "D+":
            return 1.50;
        case "D":
            return 1.00;
        case "F":
            return 0.00;

        default:
            System.out.println("Invalid grade input! \n" +
                    "You have input: " + grade + "which is invalid! \n" +
                    "I have assumed your invalid grade as F grade \n" +
                    "Which is 0.00 !");
            return 0.00;

        }
    }
}
