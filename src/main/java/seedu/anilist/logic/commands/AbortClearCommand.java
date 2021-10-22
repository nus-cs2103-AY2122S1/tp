package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.anilist.model.Model;

public class AbortClearCommand extends Command {
    public static final String MESSAGE_ABORTED = "Clear command aborted.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_ABORTED);
    }
}
