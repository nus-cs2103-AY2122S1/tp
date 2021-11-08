package seedu.gamebook.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.gamebook.commons.core.LogsCenter;
import seedu.gamebook.logic.commands.Command;

/**
 * Panel containing the list of command notes.
 */
public class CommandNoteListPanel extends UiPart<Region> {
    private static final String FXML = "CommandNoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommandNoteListPanel.class);

    @FXML
    private ListView<Command> commandNoteListView;

    /**
     * Creates a {@code CommandNoteListPanel}.
     */
    public CommandNoteListPanel() {
        super(FXML);
        commandNoteListView.setItems(Command.COMMAND_OBSERVABLE_LIST);
        commandNoteListView.setCellFactory(listView -> new CommandNoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code GameEntry} using a {@code GameEntryCard}.
     */
    private class CommandNoteListViewCell extends ListCell<Command> {
        @Override
        protected void updateItem(Command command, boolean empty) {
            super.updateItem(command, empty);

            if (empty || command == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CommandNoteCard(command).getRoot());
            }
        }
    }

}
