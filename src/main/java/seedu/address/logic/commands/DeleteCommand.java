package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the index number(s) used in the displayed person list.\n"
            + "Parameters: INDEX [MORE_INDICES] (must be positive integers and in ascending order)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person(s):";

    private final ArrayList<Index> targetIndices;

    public DeleteCommand(ArrayList<Index> targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String commandResult = MESSAGE_DELETE_PERSON_SUCCESS;

        for (Index targetIndex : targetIndices) {
            // shift index by the number of entries that were deleted prior to it
            int shiftedIndex = targetIndex.getZeroBased() - targetIndices.indexOf(targetIndex);
            targetIndex = Index.fromZeroBased(shiftedIndex);

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            commandResult += personToDelete + ", ";
        }
        // remove unnecessary final ", " in the commandResult
        commandResult = commandResult.substring(0, commandResult.length() - 2);

        return new CommandResult(commandResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndices.equals(((DeleteCommand) other).targetIndices)); // state check
    }
}
