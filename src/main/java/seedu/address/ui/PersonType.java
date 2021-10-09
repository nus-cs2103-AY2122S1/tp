package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class PersonType extends UiPart<Region> {

    private static final String FXML = "PersonType.fxml";

    @FXML
    private Label personType;

    /**
     * Creates a {@code PersonType} with the given {@code personType}.
     */
    public PersonType(String personType) {
        super(FXML);
        this.personType.setText(personType);
    }
}
