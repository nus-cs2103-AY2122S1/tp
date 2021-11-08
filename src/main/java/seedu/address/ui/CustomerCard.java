package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.customer.Customer;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerCard extends UiPart<Region> {

    private static final String FXML = "CustomerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Rhrh level 4</a>
     */

    public final Customer customer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label loyaltyPoints;
    @FXML
    private FlowPane specialRequests;
    @FXML
    private FlowPane allergies;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CustomerCard(Customer customer, int displayedIndex) {
        super(FXML);
        this.customer = customer;
        id.setText(displayedIndex + ". ");
        name.setText(customer.getName().fullName);
        phone.setText("Phone: " + customer.getPhone().value);
        address.setText("Address: " + customer.getAddress().value);
        email.setText("Email: " + customer.getEmail().value);
        loyaltyPoints.setText("Points: " + customer.getLoyaltyPoints().value + " points");
        customer.getAllergies().stream()
                .sorted(Comparator.comparing(allergy -> allergy.allergyName))
                .forEach(allergy -> allergies.getChildren().add(new Label(allergy.allergyName)));
        customer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        customer.getSpecialRequests().stream()
                .sorted(Comparator.comparing(tag -> tag.specialRequestName))
                .forEach(specialRequest -> specialRequests.getChildren()
                        .add(new Label(specialRequest.specialRequestName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerCard)) {
            return false;
        }

        // state check
        CustomerCard card = (CustomerCard) other;
        return id.getText().equals(card.id.getText())
                && card.equals(card.id);
    }
}
