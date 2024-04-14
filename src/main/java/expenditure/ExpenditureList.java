package expenditure;

import exceptions.InvalidInputFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class ExpenditureList {
    public static int expenditureCount;
    private static final double MINIMUM_AMOUNT = 0.00F;
    private static final float MAX_AMOUNT = 9999999.99F;
    private static final int MONTHYEARINPUTLENGTH = 7;
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private static final int MAX_TYPE_LENGTH = 20;
    private static ArrayList<Expenditure> expenditureList = new ArrayList<>();

    /**
     * Constructor for ExpenditureList.
     */
    public ExpenditureList() {
        expenditureList = new ArrayList<>();
        expenditureCount = 0;
    }

    /**
     * Lists all expenditures for a specific month and year.
     *
     * @param monthYear The month and year in MM.yyyy format.
     * @return A list of expenditures for the specified month and year.
     */
    public static List<Expenditure> listExpensesByMonth(String monthYear) {
        assert monthYear.length() == MONTHYEARINPUTLENGTH;
        if (!monthYear.matches("\\d{2}\\.\\d{4}")) {
            System.out.println("Month and year format incorrect! Please use MM.yyyy format.");
            return null;
        }

        String[] monthYearParts = monthYear.split("\\.");
        String targetMonth = monthYearParts[0];
        if (!isValidMonth(targetMonth)) {
            return null;
        }
        String targetYear = monthYearParts[1];
        List<Expenditure> filteredExpenses = new ArrayList<>();

        for (Expenditure exp : expenditureList) {
            String[] dateParts = exp.getDate().split("\\.");
            String expenseMonth = dateParts[1];
            String expenseYear = dateParts[2];

            if (expenseMonth.equals(targetMonth) && expenseYear.equals(targetYear)) {
                filteredExpenses.add(exp);
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");
        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found for " + monthYear);
        } else {
            System.out.println("Expenses for the month & year " + monthYear + ":");
            int count = 1;
            Float totalExpenses = 0.0F;
            for (Expenditure exp: filteredExpenses) {
                Expenditure expenditure = filteredExpenses.get(count - 1);
                System.out.println(count++ + ". " + expenditure.getDescription() +
                        " | " + expenditure.getType() +
                        " | Cost: $" + df.format(expenditure.getAmount()) +
                        " | date: " + expenditure.getDate());
                totalExpenses += exp.getAmount();
            }
            System.out.println("Total expenses for " + monthYear + ": $" + df.format(totalExpenses));
        }
        return filteredExpenses;
    }

    /**
     * Lists all expenditures for a specific year.
     *
     * @param year The year in yyyy format.
     * @return A list of expenditures for the specified year.
     */
    public static List<Expenditure> listExpensesByYear(String year) {
        List<Expenditure> filteredExpenses = new ArrayList<>();

        if (!year.matches("\\d{4}")) {
            System.out.println("Year format incorrect. Please use yyyy format.");
            return filteredExpenses;
        }

        for (Expenditure exp : expenditureList) {
            String[] dateParts = exp.getDate().split("\\.");
            String expenseYear = dateParts[2];

            if (expenseYear.equals(year)) {
                filteredExpenses.add(exp);
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");
        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found for year " + year);
        } else {
            System.out.println("Expenses for the year " + year + ":");
            int count = 1;
            Float totalExpenses = 0.0F;
            for (Expenditure exp: filteredExpenses) {
                Expenditure expenditure = filteredExpenses.get(count - 1);
                System.out.println(count++ + ". " + expenditure.getDescription() +
                        " | " + expenditure.getType() +
                        " | Cost: $" + df.format(expenditure.getAmount()) +
                        " | date: " + expenditure.getDate());
                totalExpenses += exp.getAmount();
            }
            System.out.println("Total expenses for " + year + ": $" + df.format(totalExpenses));
        }

        return filteredExpenses;
    }

    /**
     * Lists all expenditures of a specific type.
     *
     * @param type The type of expenditure.
     * @return A list of expenditures of the specified type.
     */
    public static List<Expenditure> listExpensesByType(String type) {
        List<Expenditure> filteredExpenses = new ArrayList<>();
        for (Expenditure exp: expenditureList) {
            String expensesType = exp.getType();
            if (expensesType.equals(type)) {
                filteredExpenses.add(exp);
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found for type: " + type);
        } else {
            System.out.println("Expenses for " + type);
            int count = 1;
            Float totalExpenses = 0.0F;
            for (Expenditure exp: filteredExpenses) {
                Expenditure expenditure = filteredExpenses.get(count - 1);
                System.out.println(count++ + ". " + expenditure.getDescription() +
                        " | " + expenditure.getType() +
                        " | Cost: $" + df.format(expenditure.getAmount()) +
                        " | date: " + expenditure.getDate());
                totalExpenses += exp.getAmount();
            }
            System.out.println("Total expenses for " + type + ": $" + df.format(totalExpenses));
        }
        return filteredExpenses;
    }

    /**
     * Adds a new expenditure to the list.
     *
     * @param expenditure The expenditure to add.
     * @param isUserAdded Whether the expenditure was added by the user.
     */
    public static void addExpenditure(String expenditure, Boolean isUserAdded) {
        try {
            String[] parts = expenditure.split("d/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format. Please ensure spaces " +
                        "or `/` is used or refer to the help list by using `help`.");
            }

            String descriptionPart = parts[1].trim();

            parts = descriptionPart.split(" t/", 2);
            String description = "";
            String type = "";
            if (parts.length < 2) {
                type = "NA";
            } else {
                description = parts[0].trim();
                descriptionPart = parts[1].trim();
            }

            parts = descriptionPart.split(" amt/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format. Please ensure spaces " +
                        "or `/` is used or refer to the help list by using `help`.");
            }
            if (type.isEmpty()) {
                type = parts[0].trim().toUpperCase();
            } else {
                description = parts[0].trim();
            }
            String amountAndDate = parts[1].trim();

            parts = amountAndDate.split(" date/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format. Please ensure spaces " +
                        "or `/` is used or refer to the help list by using `help`.");
            }

            String amount = parts[0].trim();
            String date = parts[1].trim();
            float amountValue = Float.parseFloat(amount);

            if (isValidDate(date) && isValidAmount(amountValue) && isValidType(type)
                    && isValidDescription(description)) {
                expenditureList.add(new Expenditure(description, type, amountValue, date));
                expenditureCount += 1;
                userAddedMessage(isUserAdded);
            }
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format!");
        }
    }

    /**
     * Prints a success message if an expenditure was added by the user.
     *
     * @param isUserAdded A boolean indicating whether the expenditure was added by the user.
     */
    private static void userAddedMessage(Boolean isUserAdded) {
        if (isUserAdded){
            System.out.println("Expenditure added successfully.");
        }
    }

    /**
     * Deletes an expenditure from the list.
     *
     * @param index The index of the expenditure to delete.
     */
    public static void deleteExpenditure(int index) {
        if (index < 1 || index > expenditureList.size() ) {
            System.out.println("Invalid number, please enter a valid number\n");
            return;
        }
        Expenditure expenditure = expenditureList.get(index - 1);
        System.out.println("deleted: " + expenditure.getDescription() +
                " | " + expenditure.getType() +
                " | Cost: $" + expenditure.getAmount() +
                " | date: " + expenditure.getDate());
        expenditureList.remove(index - 1);
        expenditureCount--;
    }

    /**
     * Clears the expenditure list.
     */
    public static void clearlist() {
        if (expenditureList.isEmpty()) {
            System.out.println("The list is already empty!");
        } else {

            while (!expenditureList.isEmpty()) {
                expenditureList.remove(expenditureList.size() - 1); // Remove the last element
                expenditureCount--; // Decrement the count of expenditures
            }
            assert expenditureList.isEmpty();
            System.out.println("I have cleared the whole list!");
        }
    }

    /**
     * Lists all expenditures.
     */
    public static void listExpenses() {
        if (expenditureList.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        DecimalFormat df = new DecimalFormat("#.00"); // Define the decimal format
        Float totalExpenses = 0.0F;
        System.out.println("Current Expenses:");
        assert !expenditureList.isEmpty();
        for (int i = 0; i < expenditureList.size(); i++) {

            Expenditure expenditure = expenditureList.get(i);
            System.out.println((i + 1) + ". " + expenditure.getDescription() +
                    " | " + expenditure.getType() +
                    " | Cost: $" + df.format(expenditure.getAmount()) +
                    " | date: " + expenditure.getDate());
            totalExpenses += expenditure.getAmount();
        }
        String formattedTotal = df.format(totalExpenses); // Format the total expenses
        System.out.println("Total expenses: $" + formattedTotal);
    }

    /**
     * Retrieves an expenditure from the list.
     *
     * @param index The index of the expenditure to retrieve.
     * @return The expenditure at the specified index.
     */
    public Expenditure getExpenditure(int index) {
        return expenditureList.get(index);
    }

    public static boolean isValidDate(String date) {
        if (date.length() != 10) {
            System.out.println("Invalid date format. Please use the format dd.MM.yyyy");
            return false;
        }

        try{
            LocalDate dateFormat = LocalDate.parse(date, DateTimeFormatter
                    .ofPattern("dd.MM.uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
            LocalDate currentDate = LocalDate.now();
            if (dateFormat.isAfter(currentDate)) {
                throw new InvalidInputFormatException("Future dates are not allowed");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Please ensure the date is correct and " +
                    "matches the format dd.MM.yyyy");
            return false;
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Checks if a month is valid.
     *
     * @param input The month to check.
     * @return true if the month is valid, false otherwise.
     */
    private static boolean isValidMonth(String input) {
        int month = Integer.parseInt(input);
        if (month > 12 ||  month < 1) {
            System.out.println("Month must be between 1 and 12");
            return false;
        }
        return true;
    }

    /**
     * Checks if an amount is valid.
     *
     * @param amt The amount to check.
     * @return true if the amount is valid, false otherwise.
     */
    private static boolean isValidAmount(float amt) {
        String amtStr = String.valueOf(amt);
        String[] parts = amtStr.split("\\.");

        if (amt <= MINIMUM_AMOUNT) {
            System.out.println("Please enter a positive amount");
            return false;
        }

        if (amt > MAX_AMOUNT) {
            System.out.println("Please enter an amount less than or equal to 9999999.99.");
            return false;
        }

        return is2DecimalPlaces(parts);
    }

    /**
     * Checks if a number has at most two decimal places.
     *
     * @param parts The number to check, split into integer and decimal parts.
     * @return true if the number has at most two decimal places, false otherwise.
     */
    private static boolean is2DecimalPlaces(String[] parts) {
        if (parts.length < 2) {
            return true;
        }
        if (parts[1].length() > 2) {
            System.out.println("Invalid amount format! Please ensure the amount has " +
                    "at most two decimal places.");
            return false;
        }
        return true;
    }

    /**
     * Checks if a type is valid.
     *
     * @param type The type to check.
     * @return true if the type is valid, false otherwise.
     */
    private static boolean isValidType(String type) {
        if (type.length() > MAX_TYPE_LENGTH) {
            System.out.println("Type should be one word of maximum 20 characters.");
            return false;
        }
        return true;
    }

    /**
     * Checks if a description is valid.
     *
     * @param description The description to check.
     * @return true if the description is valid, false otherwise.
     */
    private static boolean isValidDescription(String description) {
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            System.out.println("Description of expenditure should not be longer than 100 characters.");
            return false;
        }
        return true;
    }

}

