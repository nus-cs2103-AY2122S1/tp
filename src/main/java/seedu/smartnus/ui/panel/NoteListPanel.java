package seedu.smartnus.ui.panel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.logic.Logic;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.ui.StatusBarFooter;
import seedu.smartnus.ui.UiPart;
import seedu.smartnus.ui.card.NoteCard;

/**
 * Panel containing the list of notes.
 */
public class NoteListPanel extends UiPart<Region> implements Panel {
    public static final String NOTE_PANEL = "note";

    private static final String FXML = "NoteListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<Note> noteListView;

    private StackPane noteListPanelPlaceholder;
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code NoteListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(StackPane noteListPanelPlaceholder, StackPane status) {
        super(FXML);
        this.noteListPanelPlaceholder = noteListPanelPlaceholder;
        statusbarPlaceholder = status;
    }

    private NoteListPanel(ObservableList<Note> noteList) {
        super(FXML);
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    @Override
    public Panel render(Logic logic) {
        NoteListPanel noteListPanel = new NoteListPanel(logic.getFilteredNoteList());
        noteListPanelPlaceholder.setVisible(true);
        noteListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());

        StatusBarFooter status = new StatusBarFooter("Notes");
        statusbarPlaceholder.getChildren().add(status.getRoot());

        return noteListPanel;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Question} using a {@code QuestionCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NoteListPanel;
    }

}
