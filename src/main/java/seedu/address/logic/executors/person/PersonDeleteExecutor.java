package seedu.address.logic.executors.person;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.model.person.Person;

public class PersonDeleteExecutor extends PersonExecutor {
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public PersonDeleteExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Person deletedPerson = super.getPerson();
        model.deletePerson(deletedPerson);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson));
    }
}
