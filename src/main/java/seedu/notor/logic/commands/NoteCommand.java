package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.model.Model;


/**
 * Shows general note.
 */
public class NoteCommand implements Command {
    public static final String COMMAND_WORD = "note";
    public static final List<String> COMMAND_WORDS = Arrays.asList("note", "n");

    public static final String MESSAGE_SUCCESS = "Opened General Note.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, true, false, model.getNotor());
    }

}
