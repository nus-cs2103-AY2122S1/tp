package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

/**
 * Finds all anime(s) whose have matches to at least one specified keyword of
 * each category (case-insensitive) and displays them as a list with index numbers
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all anime(s) whose have matches to at "
        + "least one specified keyword of each category (case-insensitive) and "
        + "displays them as a list with index numbers.\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "NAME KEYWORD]... "
        + "[" + PREFIX_GENRE + "GENRE KEYWORD]...\n"
        + "Example: " + COMMAND_WORD + " n/boku no n/hero g/comedy";

    private final Predicate<Anime> predicate;


    public FindCommand(Predicate<Anime> predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
