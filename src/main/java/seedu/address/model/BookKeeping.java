package seedu.address.model;

public class BookKeeping {
    Double revenue;
    Double cost;
    Double profit;

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

    public void addCost(Double cost) {
        this.cost += cost;
        this.profit -= cost;
    }

    public void addRevenue(Double revenue) {
        this.revenue += revenue;
        this.profit += revenue;
    }
}
