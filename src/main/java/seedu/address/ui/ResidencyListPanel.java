package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.residency.Residency;

public class ResidencyListPanel extends UiPart<Region> {
    private static final String FXML = "ResidencyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ResidencyListPanel.class);

    @FXML
    private ListView<Residency> residencyListView;

    /**
     * Creates a {@code ResidencyListPanel} with the given {@code ObservableList}.
     */
    public ResidencyListPanel(ObservableList<Residency> residencyList) {
        super(FXML);
        residencyListView.setItems(residencyList);
        residencyListView.setCellFactory(listView -> new ResidencyListPanel.ResidencyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Residency} using a {@code ResidencyCard}.
     */
    class ResidencyListViewCell extends ListCell<Residency> {

        @Override
        protected void updateItem(Residency residency, boolean empty) {
            super.updateItem(residency, empty);

            if (empty || residency == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ResidencyCard(residency, getIndex() + 1).getRoot());
            }
        }
    }
}
