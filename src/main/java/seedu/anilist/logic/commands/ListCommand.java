package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.function.Predicate;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

/**
 * Lists all animes or those anime with a matching status in the anime list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all animes. An optional status parameter can be "
            + "provided and only animes with that status will be listed.\n"
            + "Parameters: [" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + "watching\n";
    private final Predicate<Anime> predicate;

    /**
     * @param predicate containing the status the user has specified, or a predicate to show all anime
     */
    public ListCommand(Predicate<Anime> predicate) {
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
                || (other instanceof ListCommand// instanceof handles nulls
                && predicate.equals(((ListCommand) other).predicate)); // state check
    }
}
