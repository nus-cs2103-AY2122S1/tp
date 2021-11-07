package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class FavoriteCommand extends Command {
    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favorites the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVORITE_PERSON_SUCCESS = " has been favorited successfully!";

    public static final String MESSAGE_ALREADY_FAVORITE_PERSON = " has already been favorited.";

    private final Index targetIndex;

    public FavoriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavorite = lastShownList.get(targetIndex.getZeroBased());
        if (personToFavorite.isFavorite()) {
            String errorMessage = personToFavorite.getName().toString()
                    + MESSAGE_ALREADY_FAVORITE_PERSON;
            throw new CommandException(errorMessage);
        }
        model.favoritePerson(personToFavorite);
        if (model.getPersonListControl() != null) {
            model.setSelectedIndex(model.getFilteredPersonList().indexOf(personToFavorite));
            model.getPersonListControl().refreshPersonListUI();
        }
        String successMessage = personToFavorite.getName().toString()
                + MESSAGE_FAVORITE_PERSON_SUCCESS;
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavoriteCommand // instanceof handles nulls
                && targetIndex.equals(((FavoriteCommand) other).targetIndex)); // state check
    }
}
