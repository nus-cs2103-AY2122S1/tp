package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.vendor.Vendor;

public class VendorListPanel extends UiPart<Region> {
    public static final String TAB_NAME = "Vendors";
    private static final String FXML = "VendorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VendorListPanel.class);

    @FXML
    private ListView<Vendor> vendorListView;

    /**
     * Creates a {@code VendorListPanel} with the given {@code ObservableList}.
     */
    public VendorListPanel(ObservableList<Vendor> vendorList) {
        super(FXML);
        vendorListView.setItems(vendorList);
        vendorListView.setCellFactory(listView -> new VendorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class VendorListViewCell extends ListCell<Vendor> {
        @Override
        protected void updateItem(Vendor vendor, boolean empty) {
            super.updateItem(vendor, empty);

            if (empty || vendor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VendorCard(vendor).getRoot());
            }
        }
    }

}
