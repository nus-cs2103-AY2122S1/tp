package seedu.address.model;

public class BookKeeping {
    private Double revenue;
    private Double cost;
    private Double profit;

    /**
     * Constructor for BookKeeping.
     *
     * @param revenue Current revenue.
     * @param cost Current cost.
     * @param profit Current profit.
     */
    public BookKeeping(Double revenue, Double cost, Double profit) {
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
    }

    public Double getRevenue() {
        return revenue;
    }

    public Double getCost() {
        return cost;
    }

    public Double getProfit() {
        return profit;
    }

    /**
     * Add cost to bookKeeping.
     *
     * @param cost cost to add.
     */
    public void addCost(Double cost) {
        this.cost += cost;
        this.profit -= cost;
    }

    /**
     * Add revenue to bookKeeping.
     *
     * @param revenue revenue to add.
     */
    public void addRevenue(Double revenue) {
        this.revenue += revenue;
        this.profit += revenue;
    }
}
