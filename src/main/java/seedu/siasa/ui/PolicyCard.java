package seedu.siasa.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.siasa.model.policy.Policy;

/**
 * An UI component that displays information of a {@code Policy}.
 */
public class PolicyCard extends UiPart<Region> {
    private static final String FXML = "PolicyListCard.fxml";

    public final Policy policy;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label expiryDate;
    @FXML
    private Label commission;
    @FXML
    private Label owner;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PolicyCard} with the given {@code Policy} and index to display.
     */
    public PolicyCard(Policy policy, int displayedIndex) {
        super(FXML);
        this.policy = policy;
        id.setText(displayedIndex + ". ");
        title.setText(policy.getTitle().value);
        price.setText(String.valueOf(policy.getPaymentStructure()));
        expiryDate.setText(policy.getCoverageExpiryDate().map(date -> "Coverage Expiry: " + date)
                        .orElse(Policy.MESSAGE_NO_EXPIRY));
        commission.setText(String.valueOf(policy.getCommission()));
        owner.setText(String.valueOf(policy.getOwner().getName()));
        policy.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyCard)) {
            return false;
        }

        // state check
        PolicyCard card = (PolicyCard) other;
        return id.getText().equals(card.id.getText())
                && policy.equals(card.policy);
    }
}
