package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.organisation.Organisation;

public class OrganisationListPanel extends UiPart<Region> {
    private static final String FXML = "OrganisationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrganisationListPanel.class);

    @FXML
    private ListView<Organisation> organisationListView;

    /**
     * Creates a {@code OrganisationListPanel} with the given {@code ObservableList}.
     */
    public OrganisationListPanel(ObservableList<Organisation> organisationList) {
        super(FXML);
        organisationListView.setItems(organisationList);
        organisationListView.setCellFactory(listView -> new OrganisationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Organisation} using a {@code OrganisationCard}.
     */
    class OrganisationListViewCell extends ListCell<Organisation> {
        @Override
        protected void updateItem(Organisation organisation, boolean empty) {
            super.updateItem(organisation, empty);

            if (empty || organisation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrganisationCard(organisation, getIndex() + 1).getRoot());
            }
        }
    }
}
