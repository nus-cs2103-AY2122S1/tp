package seedu.address.model;

public interface ReadOnlyBookKeeping {
    /**
     * Returns the revenue
     */
    Double getRevenue();

    /**
     * Returns the cost
     */
    Double getCost();

    /**
     * Returns the profit
     */
    Double getProfit();
}
