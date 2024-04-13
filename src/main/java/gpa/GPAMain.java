package gpa;


public class GPAMain {

    /**
     * Calculates the new GPA after adding grades from a list of modules to an existing GPA calculation.
     * This method computes the overall GPA by taking into account both the current GPA and the grades
     * expected from the modules yet to be completed. The formula used combines the total grade points
     * from existing credits and adds points from new modules based on their credit weight and expected grades.
     *
     * Steps to compute the new GPA:
     * 1. Convert the current GPA into total points by multiplying it with the total accumulated credits.
     * 2. Iterate over the list of modules, converting grades to points and aggregating them along with their
     * credit weights.
     * 3. Add the new credits to the accumulated credits to get a new total of credits.
     * 4. Divide the total points by the total number of credits to find the new GPA.
     *
     * @param currentGPA the current GPA of the student before adding the new modules.
     * @param totalAccumulatedCredits the total number of credits the student has accumulated so far.
     * @param moduleList a list of modules that includes modular credits and expected grades for each module.
     *                   This should be an instance of a class containing a list of Module objects.
     * @return the new calculated GPA after adding the expected grades from the moduleList.
     */
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

    /**
     * Calculates an updated GPA by incorporating grades from new modules into an existing GPA.
     * This method takes the current GPA and the total credits earned so far, then integrates the
     * expected grades from newly added modules. Each module's contribution to the GPA is calculated
     * based on its credit weight and the grade points derived from its expected grade.
     *
     * The method performs the following operations:
     * 1. Computes the initial total grade points by multiplying the current GPA with the total credits.
     * 2. Iterates over each module in the provided list, calculating the grade points for the expected
     *    grade and updating the total grade points and total credits accordingly.
     * 3. Divides the total accumulated grade points by the new total credits to compute the updated GPA.
     *
     * @param currentGPA The current GPA before considering the new modules, typically a double.
     * @param totalCredits The total number of credits earned by the student up to this point.
     * @param modules A ModuleList containing the modules to be considered for the GPA update. This should
     *                include modules with specified expected grades and modular credits.
     * @return The updated GPA after adding the grades from the new modules.
     */
    public static double calculateUpdatedGPA(double currentGPA, int totalCredits, ModuleList modules) {
        double totalGradePoints = currentGPA * totalCredits;
        int newTotalCredits = totalCredits;

        for (Module module : modules.getModules()) {
            double gradePoints = calculatePointsForGrade(module.getExpectedGrade());
            totalGradePoints += gradePoints * module.getModularCredit();
            newTotalCredits  += module.getModularCredit();
        }

        return totalGradePoints / newTotalCredits;
    }

    /**
     * Converts a letter grade into a corresponding GPA point value based on a predefined scale.
     * This method maps grades from 'A+' to 'F' to their respective GPA points. If an invalid grade
     * is provided, the method outputs an error message and treats the grade as 'F', returning 0.00.
     *
     * Grade to GPA mapping:
     * - 'A+' and 'A' map to 5.00
     * - 'A-' maps to 4.50
     * - 'B+' maps to 4.00
     * - 'B' maps to 3.50
     * - 'B-' maps to 3.00
     * - 'C+' maps to 2.50
     * - 'C' maps to 2.00
     * - 'D+' maps to 1.50
     * - 'D' maps to 1.00
     * - 'F' maps to 0.00
     *
     * @param grade A string representing the grade to be converted. Expected to be one of the valid grade
     *              identifiers ('A+', 'A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'D+', 'D', 'F').
     * @return The GPA points corresponding to the provided grade. Returns 0.00 for unrecognized grades
     *         along with a warning message about the invalid input.
     */
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
                    "You have input: " + grade + " which is invalid! \n" +
                    "I have assumed your invalid grade as F grade \n" +
                    "Which is 0.00 !");
            return 0.00;

        }
    }
}
