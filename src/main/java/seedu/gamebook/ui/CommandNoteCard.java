package seedu.gamebook.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.gamebook.logic.commands.Command;

/**
 * An UI component that displays information of a {@code GameEntry}.
 */
public class CommandNoteCard extends UiPart<Region> {

    private static final String FXML = "CommandNoteCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Command command;

    @FXML
    private HBox commandNoteCardPane;
    @FXML
    private Label commandWord;
    @FXML
    private Label commandUsage;

    /**
     * Creates a {@code GameEntryCode} with the given {@code GameEntry} and index to display.
     */
    public CommandNoteCard(Command command) {
        super(FXML);
        this.command = command;
        commandWord.setText(command.getCommandWord());
        commandUsage.setText(command.getCommandSummary());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandNoteCard)) {
            return false;
        }

        // state check
        CommandNoteCard card = (CommandNoteCard) other;
        return commandWord.getText().equals(card.commandWord.getText());
    }
}
