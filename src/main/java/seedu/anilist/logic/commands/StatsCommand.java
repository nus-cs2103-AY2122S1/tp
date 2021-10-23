package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import seedu.anilist.model.Model;
import seedu.anilist.ui.TabOption;

/**
 * Displays the statistics of animes in the anime list.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Here are you statistics.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCurrentTab(TabOption.TabOptions.STATS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}