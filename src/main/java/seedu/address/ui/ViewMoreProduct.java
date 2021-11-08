package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.product.Product;

/**
 * Second panel containing the details of client/product.
 */
public class ViewMoreProduct extends UiPart<Region> implements SecondPanel {
    private static final String fxml = "ViewMoreProduct.fxml";

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label unitPrice;

    @FXML
    private Label quantity;

    /**
     * Constructor for the ViewMore
     */
    public ViewMoreProduct() {
        super(fxml);
    }

    public void setProductDetails(Product product) {
        id.setText("ID: " + product.getId().toString());
        name.setText("Name: " + product.getName().toString());
        unitPrice.setText("Unit Price: " + product.getUnitPrice().toString());

        if (product.getQuantity() != null) {
            quantity.setText("Quantity: " + product.getQuantity().toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewMoreProduct)) {
            return false;
        }

        ViewMoreProduct view = (ViewMoreProduct) other;
        return id.equals(view.id)
                && name.equals(view.name)
                && unitPrice.equals(view.unitPrice)
                && quantity.equals(view.quantity);
    }
}
