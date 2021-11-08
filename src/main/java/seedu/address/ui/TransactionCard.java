package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.transaction.Transaction;

/**
 * A UI component that displays information of a {@code Transaction}.
 */
public class TransactionCard extends UiPart<Region> {

    /**
     * Uses FXML to identify TransactionListCard.
     */
    private static final String FXML = "TransactionListCard.fxml";

    /**
     * Stands for current displaying transaction.
     */
    public final Transaction transaction;

    /**
     * Stands for components to be used in FXML.
     *
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label billing;


    /**
     * Constructs a TransactionCard with input transaction object.
     *
     * @param transaction including transaction details to be displayed.
     */
    public TransactionCard(Transaction transaction) {
        super(FXML);
        this.transaction = transaction;
        id.setText(transaction.getId().value);
        dateTime.setText(DateTimeUtil.timestampToDate(Long.parseLong(transaction.getTimestamp().value)).toString());
        billing.setText(transaction.getBilling().value);
    }

    /**
     * Overrides equals by comparing transaction objects.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionCard)) {
            return false;
        }

        // state check
        TransactionCard card = (TransactionCard) other;
        return transaction.equals(card.transaction);
    }
}
