package seedu.address.model;

public class BookKeeping implements ReadOnlyBookKeeping {

    public static final double LIMIT = 999999999;

    private Double revenue;
    private Double cost;

    /**
     * Constructor for BookKeeping.
     *
     * @param revenue Current revenue.
     * @param cost Current cost.
     */
    public BookKeeping(Double revenue, Double cost) {
        this.revenue = revenue;
        this.cost = cost;
    }

    /**
     * Constructor for BookKeeping.
     *
     * @param bookKeeping a ReadOnlyBookKeeping version of BookKeeping
     */
    public BookKeeping(ReadOnlyBookKeeping bookKeeping) {
        this.revenue = bookKeeping.getRevenue();
        this.cost = bookKeeping.getCost();
    }

    public BookKeeping() {
        this(0.0, 0.0);
    }

    public Double getRevenue() {
        return revenue;
    }

    public Double getCost() {
        return cost;
    }

    public Double getProfit() {
        return this.revenue - this.cost;
    }

    /**
     * Add cost to bookKeeping. Total cost is capped at $999,999,999.
     *
     * @param cost cost of buying the item.
     * @param amount of item to add.
     */
    public void addCost(Double cost, Integer amount) {
        Double amountToAdd = cost * amount;

        this.cost += amountToAdd;
        this.cost = Math.min(this.cost, LIMIT);
    }

    /**
     * Add revenue to bookKeeping. Total revenue is capped at $999,999,999.
     *
     * @param revenue revenue of selling the item.
     * @param amount of item to add.
     */
    public void addRevenue(Double revenue, Integer amount) {
        Double amountToAdd = revenue * amount;

        this.revenue += amountToAdd;
        this.revenue = Math.min(this.revenue, LIMIT);
    }

    /**
     * Reinitialise bookKeeping.
     */
    public void initialise() {
        this.revenue = 0.0;
        this.cost = 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof BookKeeping)) {
            return false;
        }

        // state check
        BookKeeping other = (BookKeeping) obj;
        return revenue.equals(other.revenue)
                && cost.equals(other.cost);
    }
}
