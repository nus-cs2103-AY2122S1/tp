package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pin;
import seedu.address.model.tag.Tag;

/**
 * Unpins a contact identified using it's displayed index from the contact list.
 */
public class UnpinCommand extends Command {

    public static final String COMMAND_WORD = "unpin";

    public static final String COMMAND_DESCRIPTION = "Unpins the contact identified by the index number "
            + "used in the displayed contact list.\n";

    public static final String COMMAND_EXAMPLE =
            "Parameters: INDEX (must be a positive integer)\n"
                    + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    public static final String MESSAGE_UNPINNED_PERSON_SUCCESS = "Unpinned contact: %1$s";
    public static final String MESSAGE_PERSON_NOT_PINNED_FAILURE = "Contact is not pinned!";

    private final Index targetIndex;

    /**
     * Creates a {@code UnpinCommand} that unpins contact at {@code targetIndex}.
     *
     * @param targetIndex the index of the contact that is to be unpinned.
     */
    public UnpinCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the {@code UnpinCommand} which unpins the contact at the {@code targetIndex}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code UnpinCommand}.
     * @throws CommandException If index is invalid or contact is not pinned.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToPin = lastShownList.get(targetIndex.getZeroBased());
        if (!personToPin.isPinned()) {
            throw new CommandException(MESSAGE_PERSON_NOT_PINNED_FAILURE);
        }

        Person pinnedPerson = createUnpinnedPerson(personToPin);
        model.setPerson(personToPin, pinnedPerson);
        return new CommandResult(String.format(MESSAGE_UNPINNED_PERSON_SUCCESS, personToPin));
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpinCommand // instanceof handles nulls
                && targetIndex.equals(((UnpinCommand) other).targetIndex)); // state check
    }

    private Person createUnpinnedPerson(Person personToPin) {
        assert personToPin != null;
        assert personToPin.isPinned() : "Person is not pinned!";

        Name updatedName = personToPin.getName();
        Phone updatedPhone = personToPin.getPhone();
        Email updatedEmail = personToPin.getEmail();
        Address updatedAddress = personToPin.getAddress();
        Set<Tag> updatedTags = personToPin.getTags();
        Birthday updatedBirthday = personToPin.getBirthday().orElse(null);
        Pin updatedPin = personToPin.getPin().togglePin();

        return new Person(updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedTags, updatedBirthday, updatedPin);
    }
}
