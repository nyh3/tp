package expenditure;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ExpenditureListTest {

    @Test
    public void testValidDate() {
        new ExpenditureList();
        assertTrue(ExpenditureList.isValidDate("01.01.2022"));
        assertTrue(ExpenditureList.isValidDate("31.12.2022"));
        assertTrue(ExpenditureList.isValidDate("15.06.2023"));
    }

    @Test
    public void testInvalidDate() {
        new ExpenditureList();
        assertFalse(ExpenditureList.isValidDate("40.01.2022"));
        assertFalse(ExpenditureList.isValidDate("31.32.2022"));
        assertFalse(ExpenditureList.isValidDate("-1.06.2023"));
        assertFalse(ExpenditureList.isValidDate("01.01.405070"));
    }

    @Test
    public void testAddExpenditureValidInput() {
        int initialCount = ExpenditureList.expenditureCount;
        ExpenditureList.addExpenditure("d/ Grocery Shopping t/ Food amt/ " +
                "45.75 date/ 15.04.2024", true);
        assertEquals(initialCount+1, ExpenditureList.expenditureCount);
    }

    @Test
    public void testAddExpenditureInvalidInput() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExpenditureList.addExpenditure("d movie", true);
        assertEquals("Invalid input format. Please ensure spaces " +
                "or `/` is used or refer to the help list by using `help`.", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.addExpenditure("d/ movie", true);
        assertEquals("Invalid input format. Please ensure spaces " +
                "or `/` is used or refer to the help list by using `help`.", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.addExpenditure("d/ movie amt/ 5", true);
        assertEquals("Invalid input format. Please ensure spaces " +
                "or `/` is used or refer to the help list by using `help`.", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.addExpenditure("d/ movie amt/ abc date/ 11.11.2011", true);
        assertEquals("Invalid amount format!", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.addExpenditure("d/ movie amt/ 100.1234 date/ 11.11.2011", true);
        assertEquals("Invalid amount format! Please ensure the amount has at most two decimal places.",
                outContent.toString().trim());

        outContent.reset();

        ExpenditureList.addExpenditure("d/ movie amt/ -100 date/ 11.11.2011", true);
        assertEquals("Please enter a positive amount", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testDeleteExpenditureValidInput() {
        int initialCount = ExpenditureList.expenditureCount;
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.02.2022", true);
        assertEquals(initialCount + 1, ExpenditureList.expenditureCount);
        ExpenditureList.deleteExpenditure(1);
        assertEquals(initialCount, ExpenditureList.expenditureCount);
    }

    @Test
    public void testDeleteExpenditureInvalidInput() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExpenditureList.deleteExpenditure(100);
        assertEquals("Invalid number, please enter a valid number", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.deleteExpenditure(-10);
        assertEquals("Invalid number, please enter a valid number", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testClearList() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExpenditureList.clearlist();
        assertEquals("The list is already empty!", outContent.toString().trim());

        ExpenditureList.addExpenditure("d/Expense 1 amt/100 date/01.01.2022", true);
        ExpenditureList.addExpenditure("d/Expense 2 amt/200 date/02.01.2022", true);

        outContent.reset();
        ExpenditureList.clearlist();

        assertEquals("I have cleared the whole list!", outContent.toString().trim());

        // Verify that the count of expenditures is zero
        assertEquals(0, ExpenditureList.expenditureCount);

        System.setOut(System.out);
    }

    @Test
    public void testListExpenses() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExpenditureList.listExpenses();
        assertEquals("No expenses to display.", outContent.toString().trim());

        ExpenditureList.addExpenditure("d/Expense 1 amt/100 date/01.01.2022", true);
        ExpenditureList.addExpenditure("d/Expense 2 amt/200 date/02.01.2022", true);

        outContent.reset();
        ExpenditureList.listExpenses();
        String expectedOutput = "Current Expenses:" + System.lineSeparator() +
                "1. Expense 1 | NA | Cost: $100.00 | date: 01.01.2022" + System.lineSeparator() +
                "2. Expense 2 | NA | Cost: $200.00 | date: 02.01.2022" + System.lineSeparator() +
                "Total expenses: $300.00";
        String printedOutput = outContent.toString().trim();
        assertEquals(expectedOutput, printedOutput);

        System.setOut(System.out);
    }

    @Test
    public void testListExpensesByYearValidYear() {
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.02.2023", true);
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.04.2022", true);
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.03.2022", true);
        List<Expenditure> expensesByYear = ExpenditureList.listExpensesByYear("2022");
        assertEquals(2, expensesByYear.size());
    }

    @Test
    public void testListExpensesByYearValidMonth() {
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.02.2022", true);
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.04.2022", true);
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.03.2022", true);
        List<Expenditure> expensesByMonth = ExpenditureList.listExpensesByMonth("02.2022");
        assert expensesByMonth != null;
        assertEquals(1, expensesByMonth.size());
    }

    @Test
    public void testListExpensesByType() {
        ExpenditureList.addExpenditure("d/ Lunch t/ food amt/10.12 date/02.02.2023", true);
        ExpenditureList.addExpenditure("d/ Lunch amt/10.12 date/02.04.2022", true);
        ExpenditureList.addExpenditure("d/ Lunch t/ book amt/10.12 date/02.03.2022", true);
        List<Expenditure> expensesByType = ExpenditureList.listExpensesByType("FOOD");
        assertEquals(1, expensesByType.size());
    }

    @Test
    public void testInvalidExpensesInput() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();

        ExpenditureList.listExpensesByMonth("2011.11");
        assertEquals("Month and year format incorrect! Please use MM.yyyy format.",
                outContent.toString().trim());

        outContent.reset();

        ExpenditureList.listExpensesByYear("11");
        assertEquals("Year format incorrect. Please use yyyy format.", outContent.toString().trim());


        System.setOut(System.out);
    }

    @Test
    public void testListExpensesEmpty() {
        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExpenditureList.listExpensesByMonth("11.2011");
        assertEquals("No expenses found for 11.2011", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.listExpensesByYear("2011");
        assertEquals("No expenses found for year 2011", outContent.toString().trim());

        outContent.reset();

        ExpenditureList.listExpensesByType("BOOK");
        assertEquals("No expenses found for type: BOOK", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testAddExpenditureExtremeTestCases() {

        new ExpenditureList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "e/ add/ d/ my fav book t/food amt/ 9999999.0 date/ 15.04.2024e/ add/ " +
                "d/ my fav book t/food amt/ 9999999.0 date/ 15.04.2024";
        ExpenditureList.addExpenditure(input, false);
        assertEquals("Invalid date format. Please use the format dd.MM.yyyy",outContent.toString().
                trim());

        outContent.reset();

        input = "d/ Grocery Shopping t/ Food amt/" + "9".repeat(2000) + " date/ 15.04.2022";
        ExpenditureList.addExpenditure(input, false);
        assertEquals("Please enter an amount less than or equal to 9999999.99."
                ,outContent.toString().trim());

        outContent.reset();

        input = "d/ Grocery Shopping t/" + "a".repeat(2000) + " amt/ 45.75 date/ 15.04.2022";
        ExpenditureList.addExpenditure(input, false);
        assertEquals("Type should be one word of maximum 20 characters."
                ,outContent.toString().trim());

        outContent.reset();

        input = "d/" + "G".repeat(2000) + "t/ food amt/ 45.75 date/ 15.04.2022";
        ExpenditureList.addExpenditure(input, false);
        assertEquals("Description of expenditure should not be longer than 100 characters.",
                outContent.toString().trim());

        outContent.reset();

        input = "d/ Grocery Shopping t/ food amt/ 45.75 date/ 15.04." + "2".repeat(2000);
        ExpenditureList.addExpenditure(input, false);
        assertEquals("Invalid date format. Please use the format dd.MM.yyyy",
                outContent.toString().trim());

        System.setOut(System.out);

    }



}
