package seedu.notor.ui.note;

import javafx.fxml.FXML;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
import seedu.notor.ui.ConfirmationWindow;
import seedu.notor.ui.ResultDisplay;

public class GroupNoteWindow extends NoteWindow {

    private static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved Note to Group: %1$s";
    private static final String MESSAGE_EXIT_NOTE_SUCCESS = "Exited Note of Group: %1$s";

    private final Group group;

    /**
     * Creates a new NoteWindow.
     */
    public GroupNoteWindow(Group group, Logic logic, ResultDisplay resultDisplay) {
        super(logic, resultDisplay);
        noteTextArea.setText(group.getNote().value);
        this.group = group;
        confirmationWindow = new ConfirmationWindow(group.getName().toString(), this);
        getRoot().setTitle(group.getName().toString());
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
     * Saves the file
     */
    @FXML
    @Override
    public void handleSave() throws CommandException {
        String paragraph = noteTextArea.getText();
        if (!paragraph.isEmpty()) {
            Note editedNote = new Note(paragraph, noteLastModified());
            group.setNote(editedNote);
        } else {
            group.setNote(Note.EMPTY_NOTE);
        }
        logic.executeSaveNote();
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_SAVE_NOTE_SUCCESS));
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

    /**
     * Exits the note Window.
     */
    @Override
    public void exit() {
        getRoot().close();
        OPENED_NOTE_WINDOWS.remove(this);
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_EXIT_NOTE_SUCCESS));
    }
}
