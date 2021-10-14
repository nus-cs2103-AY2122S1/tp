package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

/**
 * Sets the availability of an existing member in the member list.
 */
public class SetMemberAvailabilityCommand extends Command {

    public static final String COMMAND_WORD = "setm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the availability of the members identified "
            + "by the indices used in the last member listing. "
            + "Existing availability will be overwritten by the input.\n"
            + "Parameters: INDEX1 INDEX2 INDEX3 (must be positive integers) "
            + "d/[AVAILABLE DAYS (case insensitive)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/mon Thu fri";

    public static final String MESSAGE_SET_AVAILABILITY_SUCCESS = "Set availability of members";

    private final List<Index> indices;
    private final Availability availability;

    /**
     * @param indices of the person in the filtered person list to edit the availability
     * @param availability of the person to be updated to
     */
    public SetMemberAvailabilityCommand(List<Index> indices, Availability availability) {
        requireAllNonNull(indices, availability);

        this.indices = indices;
        this.availability = availability;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Index i : indices) {
            if (i.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownList.get(i.getZeroBased());
            Person editedPerson = new Person(
                    personToEdit.getName(), personToEdit.getPhone(), availability);

            model.setPerson(personToEdit, editedPerson);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SET_AVAILABILITY_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetMemberAvailabilityCommand)) {
            return false;
        }

        // state check
        SetMemberAvailabilityCommand e = (SetMemberAvailabilityCommand) other;
        return indices.equals(e.indices)
                && availability.equals(e.availability);
    }
}
