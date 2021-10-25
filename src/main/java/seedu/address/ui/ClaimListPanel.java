package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.claim.Claim;
import seedu.address.model.person.Name;

/**
 * Panel containing the list of Claims and Names.
 */
public class ClaimListPanel extends UiPart<Region> {
    private static final String FXML = "ClaimListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClaimListPanel.class);

    @FXML
    private ListView<Pair<Claim, Name>> claimListView;

    /**
     * Creates a {@code ClaimsListPanel} with the given {@code ObservableList}.
     */
    public ClaimListPanel(ObservableList<Pair<Claim, Name>> claimAndNameList) {
        super(FXML);
        claimListView.setItems(claimAndNameList);
        claimListView.setCellFactory(listView -> new ClaimListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair<Claim, Name>} using a {@code ClaimCard}.
     */
    class ClaimListViewCell extends ListCell<Pair<Claim, Name>> {
        @Override
        protected void updateItem(Pair<Claim, Name> claimAndName, boolean empty) {
            super.updateItem(claimAndName, empty);

            if (empty || claimAndName == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClaimCard(claimAndName.getKey(), claimAndName.getValue()).getRoot());
            }
        }
    }

}
