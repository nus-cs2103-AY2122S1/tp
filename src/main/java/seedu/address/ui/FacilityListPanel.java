package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.facility.Facility;

/**
 * Panel containing list of Facilities.
 */
public class FacilityListPanel extends UiPart<Region> {
    private static final String FXML = "FacilityListPanel.fxml";

    @FXML
    private ListView<Facility> facilityListView;

    /**
     * Creates a FacilityListPanel object with the specified list.
     *
     * @param facilityList ObservableList of Facility.
     */
    public FacilityListPanel(ObservableList<Facility> facilityList) {
        super(FXML);
        facilityListView.setItems(facilityList);
        facilityListView.setCellFactory(listView -> new FacilityListViewCell());
    }

    /**
     * Represents a custom ListCell that displays graphics of Facility
     * using a FacilityCard.
     */
    class FacilityListViewCell extends ListCell<Facility> {
        @Override
        protected void updateItem(Facility facility, boolean isEmpty) {
            super.updateItem(facility, isEmpty);

            if (isEmpty || facility == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FacilityCard(facility, getIndex() + 1).getRoot());
            }

        }
    }
}
