package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.facility.Facility;

public class FacilityListCard extends UiPart<Region> {

    private static final String FXML = "FacilityListCard.fxml";

    public final Facility facility;

    @FXML
    private HBox cardPane;

    @FXML
    private Label name;

    @FXML
    private Label loc;

    @FXML
    private Label time;

    @FXML
    private Label capacity;

    @FXML
    private Label listIndex;

    public FacilityListCard(Facility facility, int index) {
        super(FXML);
        this.facility = facility;
        listIndex.setText(index + ".");
        name.setText(facility.getName().toString());
        loc.setText(facility.getLocation().toString());
        time.setText(facility.getTime().toString());
        capacity.setText(facility.getCapacity().toString());
    }

}
