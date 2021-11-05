package seedu.notor.logic.commands;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Model;
import seedu.notor.model.common.Note;
import seedu.notor.ui.WarningWindow;
import seedu.notor.ui.note.NoteWindow;

/**
 * Clears general note.
 */
public class ClearNoteCommand implements Command {
    // Keep this around for command description usage.
    public static final String COMMAND_WORD = "clearnote";
    public static final List<String> COMMAND_WORDS = Arrays.asList("clearnote", "cn");
    public static final String MESSAGE_CLEAR_NOTE_SUCCESS = "Cleared general note.";
    public static final String MESSAGE_CLEAR_NOTE_CANCEL = "Clearing of general note has been cancelled.";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with clearing general note?";
    public static final String MESSAGE_CANNOT_CLEAR_NOTE =
            "Unable to clear general note as general note window is currently opened.";

    @Override
    public CommandResult execute(Model model)throws ExecuteException {
        if (NoteWindow.contains(model.getNotor())) {
            throw new ExecuteException(MESSAGE_CANNOT_CLEAR_NOTE);
        }
        WarningWindow warningWindow = new WarningWindow(CONFIRMATION_MESSAGE);
        warningWindow.show();
        if (warningWindow.canContinue()) {
            model.clearNotorNote();
            assert model.getNotor().getNote() == Note.EMPTY_NOTE;
            return new CommandResult(MESSAGE_CLEAR_NOTE_SUCCESS);
        }
        return new CommandResult(MESSAGE_CLEAR_NOTE_CANCEL);
    }

}
