package seedu.address.ui;

//import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.product.Product;

/**
 * An UI component that displays information of a {@code Product}.
 */
public class ProductCard extends UiPart<Region> {

    private static final String FXML = "ProductListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Product product;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label unitPrice;
    @FXML
    private Label quantity;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ProductCode} with the given {@code Product} and index to display.
     */
    public ProductCard(Product product, int displayedIndex) {
        super(FXML);
        this.product = product;
        id.setText(displayedIndex + ". ");
        name.setText(product.getName().fullName);
        if (product.getUnitPrice() != null) {
            unitPrice.setText("Price: $" + product.getUnitPrice().unitPrice);
        }
//        if (product.getQuantity() != null) {
//            quantity.setText("Quantity: " + product.getQuantity().quantity);
//        }
        //        person.getTags().stream()
        //                .sorted(Comparator.comparing(tag -> tag.tagName))
        //                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductCard)) {
            return false;
        }

        // state check
        ProductCard card = (ProductCard) other;
        return id.getText().equals(card.id.getText())
                       && product.equals(card.product);
    }
}
