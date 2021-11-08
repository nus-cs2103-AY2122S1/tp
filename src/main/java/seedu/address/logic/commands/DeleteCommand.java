package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes multiple/all persons in the address book, or a person identified using it's
 * displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used, "
            + "or all persons, in the \ndisplayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) / -a / -f\n"
            + "Examples: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " -f";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_ALL_PERSONS_SUCCESS = "Deleted all persons in address book";
    public static final String MESSAGE_DELETE_ALL_SHOWN_PERSONS_SUCCESS = "Deleted %d Persons:";

    private enum Quantity { ONE, ALL, SHOWN }

    private final Index targetIndex;

    private final Quantity quantity;

    public DeleteCommand(Index targetIndex) {
        this(targetIndex, Quantity.ONE);
    }

    private DeleteCommand(Index targetIndex, Quantity quantity) {
        this.targetIndex = targetIndex;
        this.quantity = quantity;
    }

    public static DeleteCommand all() {
        return new DeleteCommand(null, Quantity.ALL);
    }

    public static DeleteCommand allShown() {
        return new DeleteCommand(null, Quantity.SHOWN);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (this.quantity) {
        case ONE: return deletePerson(model);
        case ALL: return deleteAll(model);
        case SHOWN: default: return deleteAllShown(model);
        }
    }

    private CommandResult deletePerson(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        requireNonNull(targetIndex);
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    private CommandResult deleteAll(Model model) {
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_DELETE_ALL_PERSONS_SUCCESS);
    }

    private CommandResult deleteAllShown(Model model) {
        List<Person> personsToDelete = new ArrayList<>(model.getFilteredPersonList());

        model.deletePersons(personsToDelete);

        StringBuilder resultSb = new StringBuilder(MESSAGE_DELETE_ALL_SHOWN_PERSONS_SUCCESS);
        for (Person person : personsToDelete) {
            resultSb.append("\n");
            resultSb.append(person);
        }

        return new CommandResult(String.format(resultSb.toString(), personsToDelete.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && this.equals((DeleteCommand) other)); // state check
    }

    private boolean equals(DeleteCommand other) {
        if (this.quantity != other.quantity) {
            return false;
        } else if (this.quantity == Quantity.ONE) {
            return this.targetIndex.equals(other.targetIndex);
        } else {
            return true;
        }
    }
}
