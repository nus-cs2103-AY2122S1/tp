package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.StatusEqualsPredicate;

/**
 * Filters the anime by its status and lists those anime with the specified status to the user.
 */
public class TabCommand extends Command {

    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all animes whose status matches the provided "
            + "status and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_STATUS + "STATUS\n"
            + "Example: " + PREFIX_STATUS + "watching";

    private final StatusEqualsPredicate predicate;

    /**
     * @param predicate containing the status the user has specified
     */
    public TabCommand(StatusEqualsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ANIME_LISTED_OVERVIEW, model.getFilteredAnimeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TabCommand// instanceof handles nulls
                && predicate.equals(((TabCommand) other).predicate)); // state check
    }
}
