package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.anilist.model.Model;

/**
 * Displays the statistics of animes in the anime list.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Displayed user statistics.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateUserStats();
        return new CommandResult(MESSAGE_SUCCESS, true, false);
    }
}
