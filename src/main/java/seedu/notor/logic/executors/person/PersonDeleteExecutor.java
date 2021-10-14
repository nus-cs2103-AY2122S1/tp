package seedu.notor.logic.executors.person;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;

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
