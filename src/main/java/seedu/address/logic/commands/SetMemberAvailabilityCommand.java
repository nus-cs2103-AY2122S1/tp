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
            + ": Sets the availability of the member identified "
            + "by the index number used in the last member listing. "
            + "Existing availability will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/[AVAILABLE DAYS (case insensitive)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/mon,Thu,fri";

    public static final String MESSAGE_SET_AVAILABILITY_SUCCESS = "Set availability of member: %1$s";

    private final Index index;
    private final Availability availability;

    /**
     * @param index of the person in the filtered person list to edit the availability
     * @param availability of the person to be updated to
     */
    public SetMemberAvailabilityCommand(Index index, Availability availability) {
        requireAllNonNull(index, availability);

        this.index = index;
        this.availability = availability;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), availability, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SET_AVAILABILITY_SUCCESS, editedPerson));
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
        return index.equals(e.index)
                && availability.equals(e.availability);
    }
}
