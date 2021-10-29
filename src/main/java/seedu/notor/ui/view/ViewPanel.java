package seedu.notor.ui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.notor.model.common.Note;
import seedu.notor.ui.UiPart;

/**
 * Panel containing the list of T.
 */
public class ViewPanel extends UiPart<Region> {
    private static final String FXML = "GeneralNoteViewPanel.fxml";

    @FXML
    protected TextArea generalNote;

    /**
     * Creates a {@code ListPanel}.
     * @param note
     */
    public ViewPanel(Note note) {
        super(FXML);
        generalNote.setText(note.value);
        generalNote.setEditable(false);
    }

    public void setNote(Note note) {
        generalNote.setText(note.value);
        generalNote.setEditable(false);
    }

}
