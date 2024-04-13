package expenditure;

public class Expenditure {
    protected String description;

    protected String type;

    protected Float amount;
    protected String date;

    /**
     * Constructor for Expenditure.
     *
     * @param description The description of the expenditure.
     * @param type The type of the expenditure.
     * @param amount The amount of the expenditure.
     * @param date The date of the expenditure.
     */
    public Expenditure(String description, String type, Float amount, String date) {
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Retrieves the description of the expenditure.
     *
     * @return The description of the expenditure.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the type of the expenditure.
     *
     * @return The type of the expenditure.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the amount of the expenditure.
     *
     * @return The amount of the expenditure.
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * Retrieves the date of the expenditure.
     *
     * @return The date of the expenditure.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns a string representation of the expenditure.
     *
     * @return A string representation of the expenditure.
     */
    @Override
    public String toString() {
        return description + " | " + type + " | Cost: $" + amount + " | date: " + date;
    }

    /**
     * Returns a string representation of the expenditure for storage.
     *
     * @return A string representation of the expenditure for storage.
     */
    public String toStringStorage() {
        return description + " | " + type + " | " + amount + " | " + date;
    }
}
