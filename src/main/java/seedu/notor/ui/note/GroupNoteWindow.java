package seedu.notor.ui.note;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Notable;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
import seedu.notor.ui.ConfirmationWindow;
import seedu.notor.ui.ResultDisplay;
import seedu.notor.ui.listpanel.GroupListPanel;
import seedu.notor.ui.listpanel.SubgroupListPanel;

public class GroupNoteWindow extends NoteWindow {

    private static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved Note to Group: %1$s.";
    private static final String MESSAGE_EXIT_NOTE_SUCCESS = "Exited Note of Group: %1$s.";

    private final Group group;
    private final StackPane listPanelPlaceholder;

    /**
     * Creates a new NoteWindow.
     */
    public GroupNoteWindow(Group group, Logic logic, ResultDisplay resultDisplay, StackPane listPanelPlaceholder) {
        super(logic, resultDisplay);
        noteTextArea.setText(group.getNote().value);
        this.group = group;
        this.listPanelPlaceholder = listPanelPlaceholder;
        confirmationWindow = new ConfirmationWindow(group.getName(), this);
        getRoot().setTitle(group.getName());
        noteTextArea.setWrapText(true);
        getRoot().setOnCloseRequest(e -> {
            e.consume();
            handleExit();
        });
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    @Override
    public String generateSuccessMessage(String message) {
        return String.format(message, group);
    }

    /**
     * Saves the file.
     */
    @FXML
    @Override
    public void handleSave() throws CommandException {
        if (!logic.isPersonList() && !logic.isArchiveList()) {
            if (logic.isSuperGroupList()) {
                GroupListPanel listPanel = new GroupListPanel(logic.getFilteredSuperGroupList());
                listPanelPlaceholder.getChildren().add(listPanel.getRoot());
            } else {
                SubgroupListPanel listPanel = new SubgroupListPanel(logic.getFilteredSubGroupList());
                listPanelPlaceholder.getChildren().add(listPanel.getRoot());
            }
        }
        String paragraph = noteTextArea.getText();
        Note editedNote = Note.of(paragraph, noteLastModified());
        group.setNote(editedNote);
        logic.executeSaveNote();
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_SAVE_NOTE_SUCCESS));
        logger.info(String.format(MESSAGE_SAVE_NOTE_SUCCESS, group));
    }


    /**
     * Checks if current Note is saved.
     */
    @Override
    public boolean isSave() {
        return group.getNote().value.equals(noteTextArea.getText());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroupNoteWindow)) {
            return false;
        }

        GroupNoteWindow otherGroup = (GroupNoteWindow) other;
        return otherGroup.group.equals(this.group);
    }

    @Override
    public boolean belongsTo(Notable notable) {
        if (notable instanceof Group) {
            Group otherGroup = (Group) notable;
            return otherGroup.equals(group);
        }
        return false;
    }

    /**
     * Exits the note Window.
     */
    @Override
    public void exit() {
        getRoot().close();
        OPENED_NOTE_WINDOWS.remove(this);
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_EXIT_NOTE_SUCCESS));
        logger.info(generateSuccessMessage(MESSAGE_EXIT_NOTE_SUCCESS));
    }
}
