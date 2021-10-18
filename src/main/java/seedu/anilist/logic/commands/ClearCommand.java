package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.anilist.model.Model;

/**
 * Clears the anime list.
 */
public class ClearCommand extends Command {
    private boolean isIntermediate = true;
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_CONFIRMATION = "Are you sure you want to clear the displayed " +
            "anime(s)?\nThis action is irreversible. [y/n]";

    @Override
    public boolean requiresConfirmation() {
        return this.isIntermediate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_CLEAR_CONFIRMATION);
    }
}
