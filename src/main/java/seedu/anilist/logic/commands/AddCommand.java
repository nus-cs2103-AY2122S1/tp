package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

/**
 * Adds an anime to the anime list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an anime to the anime list. "
            + "Parameters: "
            + PREFIX_NAME + "ANIME "
            + "[" + PREFIX_GENRE + "GENRE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Naruto "
            + PREFIX_GENRE + "Action "
            + PREFIX_GENRE + "Fantasy";

    public static final String MESSAGE_SUCCESS = "New anime added: %1$s";
    public static final String MESSAGE_DUPLICATE_ANIME = "This anime already exists in the anime list";

    private final Anime toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Anime}
     */
    public AddCommand(Anime anime) {
        requireNonNull(anime);
        toAdd = anime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAnime(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIME);
        }

        model.addAnime(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
