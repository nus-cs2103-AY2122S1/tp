package seedu.notor.logic.executors.person;

import java.util.List;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;

public class PersonUnarchiveExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Archived Person: %1$s";

    public PersonUnarchiveExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        if (!model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_PERSONS_NOT_LISTED);
        }
        Person toBeUnarchivedPerson = super.getPerson();
        model.unarchivePerson(toBeUnarchivedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeUnarchivedPerson));
    }

    @Override
    protected Person getPerson() throws ExecuteException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new ExecuteException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }
}
