package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.guest.Guest;

/**
 * Panel containing the list of persons.
 */
public class GuestListPanel extends UiPart<Region> {
    public static final String TAB_NAME = "Guests";
    private static final String FXML = "GuestListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(GuestListPanel.class);

    @FXML
    private ListView<Guest> guestListView;

    /**
     * Creates a {@code GuestListPanel} with the given {@code ObservableList}.
     */
    public GuestListPanel(ObservableList<Guest> guestList) {
        super(FXML);
        guestListView.setItems(guestList);
        guestListView.setCellFactory(listView -> new GuestListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Guest} using a {@code GuestCard}.
     */
    class GuestListViewCell extends ListCell<Guest> {
        @Override
        protected void updateItem(Guest guest, boolean empty) {
            super.updateItem(guest, empty);

            if (empty || guest == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GuestCard(guest).getRoot());
            }
        }
    }

}
