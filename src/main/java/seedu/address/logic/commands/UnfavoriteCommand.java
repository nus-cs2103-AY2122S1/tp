package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class UnfavoriteCommand extends Command {
    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unfavorites the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNFAVORITE_PERSON_SUCCESS = " has been unfavorited successfully!";

    public static final String MESSAGE_ALREADY_UNFAVORITE_PERSON = " has already been unfavorited.";

    private final Index targetIndex;

    /**
     * Creates an Unfavorite Command containing the
     * index of person to be unfavorited.
     *
     * @param targetIndex Index of target person.
     */
    public UnfavoriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * This method attempts to unfavorite an existing contact.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnfavorite = lastShownList.get(targetIndex.getZeroBased());
        if (!personToUnfavorite.isFavorite()) {
            String errorMessage = personToUnfavorite.getName().toString()
                    + MESSAGE_ALREADY_UNFAVORITE_PERSON;
            throw new CommandException(errorMessage);
        }
        model.unfavoritePerson(personToUnfavorite);
        if (model.getPersonListControl() != null) {
            model.setSelectedIndex(model.getFilteredPersonList().indexOf(personToUnfavorite));
            model.getPersonListControl().refreshPersonListUI();
        }
        String successMessage = personToUnfavorite.getName().toString()
                + MESSAGE_UNFAVORITE_PERSON_SUCCESS;
        return new CommandResult(successMessage);
    }

    /**
     * Method to compare two UnfavoriteCommand objects.
     *
     * @param other is the object that is going to be compared
     *              to the UnfavoriteCommand object that called this method.
     * @return boolean representation of whether the UnfavoriteCommand
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnfavoriteCommand // instanceof handles nulls
                && targetIndex.equals(((UnfavoriteCommand) other).targetIndex)); // state check
    }
}
