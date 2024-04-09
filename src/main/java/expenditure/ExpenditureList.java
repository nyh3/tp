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
    private static ArrayList<Expenditure> expenditureList = new ArrayList<>();

    public ExpenditureList() {
        expenditureList = new ArrayList<>();
        expenditureCount = 0;
    }

    public static List<Expenditure> listExpensesByMonth(String monthYear) {
        assert monthYear.length() == 7;
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


    public static void addExpenditure(String expenditure, Boolean userAdded) {
        try {
            String[] parts = expenditure.split("d/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for description.");
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
                throw new InvalidInputFormatException("Invalid input format for amount.");
            }
            if (type.isEmpty()) {
                type = parts[0].trim().toUpperCase();
            } else {
                description = parts[0].trim();
            }
            String amountAndDate = parts[1].trim();

            parts = amountAndDate.split(" date/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for date.");
            }

            String amount = parts[0].trim();
            String date = parts[1].trim();
            float amountValue = Float.parseFloat(amount);

            if (isValidDate(date) && isValidAmount(amountValue)) {
                expenditureList.add(new Expenditure(description, type, amountValue, date));
                expenditureCount += 1;
                userAddedMessage(userAdded);
            }
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format!");
        }
    }

    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Expenditure added successfully.");
        }
    }

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

    public static void listExpenses() {
        if (expenditureList.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        Float totalExpenses = 0.0F;
        System.out.println("Current Expenses:");
        assert !expenditureList.isEmpty();
        for (int i = 0; i < expenditureList.size(); i++) {
            Expenditure expenditure = expenditureList.get(i);
            System.out.println((i + 1) + ". " + expenditure.getDescription() +
                    " | " + expenditure.getType() +
                    " | Cost: $" + expenditure.getAmount() +
                    " | date: " + expenditure.getDate());
            totalExpenses += expenditure.getAmount();
        }
        DecimalFormat df = new DecimalFormat("#.00"); // Define the decimal format
        String formattedTotal = df.format(totalExpenses); // Format the total expenses
        System.out.println("Total expenses: $" + formattedTotal);
    }

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

    private static boolean isValidMonth(String input) {
        int month = Integer.parseInt(input);
        if (month > 12 ||  month < 1) {
            System.out.println("Month must be between 1 and 12");
            return false;
        }
        return true;
    }

    private static boolean isValidAmount(float amt) {
        String amtStr = String.valueOf(amt);
        String[] parts = amtStr.split("\\.");

        if (amt <= 0 ) {
            System.out.println("Please enter a positive amount");
            return false;
        }
        return is2DecimalPlaces(parts);
    }

    private static boolean is2DecimalPlaces(String[] parts) {
        if (parts[1].length() > 2) {
            System.out.println("Invalid amount format! Please ensure the amount has at most two decimal places.");
            return false;
        }
        return true;
    }

}

