package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.model.Model;
import seedu.modulink.model.person.IsFavouritePredicate;

/**
 * Finds and lists all persons in ModuLink whose Student ID contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListFavCommand extends Command {

    public static final String COMMAND_WORD = "listFav";

    public static final String MESSAGE_SUCCESS = "Listed all favourites.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all favourites of the current user "
            + "and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new IsFavouritePredicate());
        int result = model.getFilteredPersonList().size();
        if (result <= 0) {
            return new CommandResult(Messages.MESSAGE_NO_FAVOURITES);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
