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
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Product;

public class PieChartSalesView extends UiPart<Region> implements SecondPanel {
    private static String fxml = "PieChartSales.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Constructor for the piechart
     */
    public PieChartSalesView(ObservableList<Client> clients, ObservableList<Product> products) {
        super(fxml);
        // data to be changed
        HashMap<Name, Integer> table = new HashMap<Name, Integer>();
        for (Client client : clients) {
            Set<Order> currOrders = client.getOrders();
            for (Order order : currOrders) {
                int id = order.id.getId();
                Name productName = products.get(id - 1).getName();
                int quantity = Integer.parseInt(order.quantity.value);
                if (table.containsKey(productName)) {
                    table.put(productName, table.get(productName) + quantity);
                } else {
                    table.put(productName, quantity);
                }
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<Name, Integer> product : table.entrySet()) {
            pieChartData.add(new PieChart.Data("Product: "
                                                        + product.getKey().toString() + "\n"
                                                        + " Sold: "
                                                        + product.getValue().toString(),
                    product.getValue()));
        }
        pieChart.getData().addAll(pieChartData);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PieChartSalesView)) {
            return false;
        }
        PieChartSalesView view = (PieChartSalesView) other;
        return pieChart.equals(view.pieChart);
    }
}
