package seedu.notor.ui.note;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Notable;
import seedu.notor.model.Notor;
import seedu.notor.model.common.Note;
import seedu.notor.ui.ConfirmationWindow;
import seedu.notor.ui.GeneralNote;
import seedu.notor.ui.ResultDisplay;


public class GeneralNoteWindow extends NoteWindow {

    private static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved General Note.";
    private static final String MESSAGE_EXIT_NOTE_SUCCESS = "Exited General Note.";
    private static final String WINDOW_NAME = "General Note";

    private final Notor notor;

    private StackPane notePane;

    /**
     * Creates a new NoteWindow.
     */
    public GeneralNoteWindow(Notor notor, Logic logic, ResultDisplay resultDisplay, StackPane notePane) {
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
        this.notePane = notePane;
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    @Override
    public String generateSuccessMessage(String message) {
        return message;
    }


    /**
     * Saves the file.
     */
    @FXML
    @Override
    public void handleSave() throws CommandException {
        String paragraph = noteTextArea.getText();
        Note editedNote = Note.of(paragraph, noteLastModified());
        notor.setNote(editedNote);
        logic.executeSaveNote(notor);
        GeneralNote viewPane = new GeneralNote(logic.getNotor().getNote());
        notePane.getChildren().add(viewPane.getRoot());
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_SAVE_NOTE_SUCCESS));
        logger.info(MESSAGE_SAVE_NOTE_SUCCESS);
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

        GeneralNoteWindow otherGeneralNoteWindow = (GeneralNoteWindow) other;
        return otherGeneralNoteWindow.notor.equals(this.notor);
    }

    @Override
    public boolean belongsTo(Notable notable) {
        if (notable instanceof Notor) {
            Notor otherNotor = (Notor) notable;
            return otherNotor.equals(notor);
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
        logger.info(MESSAGE_EXIT_NOTE_SUCCESS);
    }
}
