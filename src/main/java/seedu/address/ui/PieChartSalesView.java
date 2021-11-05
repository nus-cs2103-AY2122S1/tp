package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.order.Order;

public class PieChartSalesView extends UiPart<Region> implements SecondPanel {
    private static final String fxml = "PieChartSales.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Constructor for the {@code PieChartSalesView}
     */
    public PieChartSalesView(ObservableList<Client> clients) {
        super(fxml);

        // data to be changed
        HashMap<String, Integer> table = new HashMap<>();
        ArrayList<String> toBeDeleted = new ArrayList<>();
        ArrayList<String> toBeAddedKey = new ArrayList<>();
        ArrayList<Integer> toBeAddedValue = new ArrayList<>();

        for (Client client : clients) {
            Set<Order> currOrders = client.getOrders();
            for (Order order : currOrders) {
                String productName = order.getProductName().toString();
                int quantity = Integer.parseInt(order.getQuantity().value);
                table.put(productName, table.getOrDefault(productName, 0) + quantity);
            }
        }
        Map<String, Integer> topFive =
                table.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> product : topFive.entrySet()) {
            if (product.getKey().length() > 14) {
                toBeDeleted.add(product.getKey());
                String newString = product.getKey().substring(0, 11) + "...";
                int newValue = product.getValue();
                toBeAddedKey.add(newString);
                toBeAddedValue.add(newValue);
            }
        }

        for (int i = 0; i < toBeDeleted.size(); i++) {
            topFive.remove(toBeDeleted.get(i));
            topFive.put(toBeAddedKey.get(i), toBeAddedValue.get(i));
        }

        for (Map.Entry<String, Integer> product : topFive.entrySet()) {
            pieChartData.add(new PieChart.Data(
                    "Product: " + product.getKey().toString() + "\n"
                            + " Sold: " + product.getValue().toString(),
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
