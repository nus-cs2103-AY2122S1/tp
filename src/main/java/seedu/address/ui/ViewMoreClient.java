package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.order.Order;

/**
 * Second panel containing the details of client/product.
 */
public class ViewMoreClient extends UiPart<Region> implements SecondPanel {
    private static final String fxml = "ViewMoreClient.fxml";

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private Label orders;

    /**
     * Constructor for the ViewMore
     */
    public ViewMoreClient() {
        super(fxml);
    }

    public void setClientDetails(Client client) {
        id.setText("ID: " + client.getId().toString());
        name.setText("Name: " + client.getName().toString());
        phoneNumber.setText("Phone Number: " + client.getPhoneNumber().toString());

        if (client.getEmail() != null) {
            email.setText("Email: " + client.getEmail().toString());
        }

        if (client.getAddress() != null) {
            address.setText("Address: " + client.getAddress().toString());
        }

        if (!client.getOrders().isEmpty()) {
            StringBuilder orderString = new StringBuilder();
            for (Order order : client.getOrders()) {
                orderString.append("\t").append(order.toString()).append("\n");
            }
            orders.setText("Orders:\n" + orderString);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewMoreClient)) {
            return false;
        }

        ViewMoreClient view = (ViewMoreClient) other;
        return id.equals(view.id)
                && name.equals(view.name)
                && phoneNumber.equals(view.phoneNumber)
                && email.equals(view.email)
                && address.equals(view.address)
                && orders.equals(view.orders);
    }
}
