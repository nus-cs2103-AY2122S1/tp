package seedu.notor.logic.executors.person;

import java.util.List;
import java.util.Objects;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;

public abstract class PersonExecutor extends Executor {
    protected final Index index;

    public PersonExecutor(Index index) {
        this.index = index;
    }

    protected Person getPerson() throws ExecuteException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new ExecuteException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }

    protected void checkPersonList() throws ExecuteException {
        if (!model.isPersonList() && !model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_PERSONS_NOT_LISTED);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonExecutor)) {
            return false;
        }

        PersonExecutor e = (PersonExecutor) other;

        if (index == null) {
            return e.index == null;
        }

        // state check
        return index.equals(e.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
