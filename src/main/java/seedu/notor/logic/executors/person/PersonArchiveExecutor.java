package seedu.notor.logic.executors.person;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;

public class PersonArchiveExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Archived Person: %1$s";

    public PersonArchiveExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        if (!model.isPersonList() || model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_PERSON_ARCHIVE_NOT_LISTED);
        }
        Person toBeArchivedPerson = super.getPerson();
        model.archivePerson(toBeArchivedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeArchivedPerson));
    }
}
