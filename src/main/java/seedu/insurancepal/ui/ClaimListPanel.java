package seedu.insurancepal.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.model.person.Person;

/**
 * Panel containing the list of Claims and Names.
 */
public class ClaimListPanel extends UiPart<Region> {
    private static final String FXML = "ClaimListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClaimListPanel.class);

    @FXML
    private ListView<Person> claimListView;

    /**
     * Creates a {@code ClaimsListPanel} with the given {@code ObservableList}.
     */
    public ClaimListPanel(ObservableList<Person> personList) {
        super(FXML);
        claimListView.setItems(personList);
        claimListView.setCellFactory(listView -> new ClaimListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair<Claim, Name>} using a {@code ClaimCard}.
     */
    class ClaimListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null || person.getClaims().isEmpty()) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClaimCard(
                        person.getClaims().stream().findFirst().get(),
                        person.getName()).getRoot());
            }
        }
    }

}
