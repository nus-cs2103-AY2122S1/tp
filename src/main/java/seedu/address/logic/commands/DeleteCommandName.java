package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.summary.Summary;

/**
 * Deletes a person identified using its displayed index from the address book.
 */
public class DeleteCommandName extends DeleteCommand {

    private final Name targetName;

    public DeleteCommandName(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToDelete = findPerson(model);
        model.deletePerson(personToDelete);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), summary);
    }

    /**
     * This method finds the person object for Delete Command to delete
     * @param model the current address book model
     * @return Returns the person to be deleted (if any)
     * @throws CommandException if the person does not exist
     */
    private Person findPerson(Model model) throws CommandException {
        List<Person> personList = model.getFilteredPersonList();

        for (Person person: personList) {
            String fullName = person.getName().fullName;
            if (fullName.equals(targetName.fullName.trim())) {
                return person;
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommandName // instanceof handles nulls
                && targetName.equals(((DeleteCommandName) other).targetName)); // state check
    }
}
