package seedu.anilist.logic.commands;

import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AbortClearCommand extends Command {
    public static final String COMMAND_WORD = "n";
    public static final String MESSAGE_ABORTED = "Clear command aborted.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_ABORTED);
    }
}
