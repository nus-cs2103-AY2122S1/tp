package seedu.notor.ui.note;

import javafx.fxml.FXML;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Notor;
import seedu.notor.model.common.Note;
import seedu.notor.ui.ConfirmationWindow;
import seedu.notor.ui.ResultDisplay;

public class GeneralNoteWindow extends NoteWindow {

    private static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved general note.";
    private static final String MESSAGE_EXIT_NOTE_SUCCESS = "Exited General Note";
    private static final String WINDOW_NAME = "General Note";

    private final Notor notor;

    /**
     * Creates a new NoteWindow.
     */
    public GeneralNoteWindow(Notor notor, Logic logic, ResultDisplay resultDisplay) {
        super(logic, resultDisplay);
        noteTextArea.setText(notor.getNote().value);
        this.notor = notor;
        confirmationWindow = new ConfirmationWindow(WINDOW_NAME, this);
        getRoot().setTitle(WINDOW_NAME);
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
        return MESSAGE_SAVE_NOTE_SUCCESS;
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
            notor.setNote(editedNote);
        }
        logic.executeSaveNote(notor);
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_SAVE_NOTE_SUCCESS));
    }


    /**
     * Checks if current Note is saved.
     */
    @Override
    public boolean isSave() {
        return notor.getNote().value.equals(noteTextArea.getText());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GeneralNoteWindow)) {
            return false;
        }

        GeneralNoteWindow otherPerson = (GeneralNoteWindow) other;
        return otherPerson.notor.equals(this.notor);
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
