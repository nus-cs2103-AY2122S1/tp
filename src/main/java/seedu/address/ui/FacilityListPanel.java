package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.facility.Facility;

public class FacilityListPanel extends UiPart<Region> {
    private static final String FXML = "FacilityListPanel.fxml";

    @FXML
    private ListView<Facility> facilityListView;

    public FacilityListPanel(ObservableList<Facility> facilityList) {
        super(FXML);
        facilityListView.setItems(facilityList);
        facilityListView.setCellFactory(listView -> new FacilityListViewCell());
    }

    class FacilityListViewCell extends ListCell<Facility> {
        @Override
        protected void updateItem(Facility facility, boolean isEmpty) {
            super.updateItem(facility, isEmpty);

            if (isEmpty || facility == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FacilityListCard(facility, getIndex() + 1).getRoot());
            }

        }
    }
}
