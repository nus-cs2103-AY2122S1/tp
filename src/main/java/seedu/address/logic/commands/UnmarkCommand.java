package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unmarks an applicant to "Not Done" identified using it's displayed index from the address book.
 */
public class UnmarkCommand extends MarkingCommand {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the applicants identified by the index numbers used in the displayed "
            + "person list to Not Done.\n" + "Parameters: INDEX...\n"
            + "Example: " + COMMAND_WORD + " 1 4 7";

    public static final String MESSAGE_UNMARKED_PERSON_SUCCESS = "Unmarked Person(s) to Not Done: \n%1$s";
    public static final String MESSAGE_UNMARKING_UNMARKED_PERSON =
            "A person that is already unmarked as Not Done cannot be unmarked again";

    private final Index[] targetIndexes;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param targetIndexes Array of indexes corresponding to the applicants to unmark.
     */
    public UnmarkCommand(Index[] targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        StringBuilder result = new StringBuilder();

        boolean hasBeenUnmark;
        for (Index checkIndex : targetIndexes) {
            if (checkIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToCheck = lastShownList.get(checkIndex.getZeroBased());
            hasBeenUnmark = model.checkForUnmarkedPerson(personToCheck);
            if (hasBeenUnmark) {
                throw new CommandException(MESSAGE_UNMARKING_UNMARKED_PERSON);
            }
        }

        for (Index targetIndex : targetIndexes) {
            Person personToUnmark = lastShownList.get(targetIndex.getZeroBased());
            model.unmarkPerson(personToUnmark);
            result.append(personToUnmark);
        }

        return new CommandResult(String.format(MESSAGE_UNMARKED_PERSON_SUCCESS, result.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((UnmarkCommand) other).targetIndexes)); // state check
    }
}
