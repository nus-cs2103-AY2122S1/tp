package seedu.notor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import seedu.notor.model.common.Note;

public class NotePane extends UiPart<Region> {

    private static final String FXML = "NotePane.fxml";

    @FXML
    private VBox notePane;
    @FXML
    private Label note;

    NotePane() {
        super(FXML);

    }



}
