package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.order.Order;
import seedu.address.model.product.Product;

public class PieChartView extends UiPart<Region> implements SecondPanel {
    private static String fxml = "PieChart.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Constructor for the piechart
     */
    public PieChartView(ObservableList<Client> clients, ObservableList<Product> products) {
        super(fxml);
        // data to be changed
        HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
        for (Client client : clients) {
            Set<Order> currOrders = client.getOrders();
            for (Order order : currOrders) {
                int year = order.time.getYear();
                int quantity = Integer.parseInt(order.quantity.value);
                int unitPrice = Integer.parseInt(products.get(order.id.getId()).getUnitPrice().value);
                int sale = quantity * unitPrice;
                if (table.containsKey(year)) {
                    table.put(year, table.get(year) + sale);
                } else {
                    table.put(year, sale);
                }
            }
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Integer> profits : table.entrySet()) {
            pieChartData.add(new PieChart.Data("Profits for "
                    + profits.getKey().toString()
                    + ": $"
                    + profits.getValue().toString(),
                    profits.getValue()));
        }
        pieChart.getData().addAll(pieChartData);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PieChartView)) {
            return false;
        }
        PieChartView view = (PieChartView) other;
        return pieChart.equals(view.pieChart);
    }
}
