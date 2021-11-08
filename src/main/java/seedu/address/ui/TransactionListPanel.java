package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.Transaction;

/**
 * Represents for Panel containing the list of transactions.
 */
public class TransactionListPanel extends UiPart<Region> {

    /**
     * Uses FXML to identify TransactionListPanel.
     */
    private static final String FXML = "TransactionListPanel.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Constructs a TransactionListPanel with input transactionList of a specific member.
     *
     * @param transactionList refers to all transactions of a specific member.
     */
    public TransactionListPanel(ObservableList<Transaction> transactionList) {
        super(FXML);
        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
    }

    /**
     * Customs {@code ListCell} that displays the graphics of a {@code Transaction} using a {@code TransactionCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(transaction).getRoot());
            }
        }
    }

}
