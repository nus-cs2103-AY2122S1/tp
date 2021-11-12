package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;

/**
 * Executor for a PersonListCommand.
 */
public class PersonListExecutor extends PersonExecutor {

    public PersonListExecutor() {
        super(null);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        // TODO: Should we create a new method lol in model.
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.displayPersons();
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonListExecutor)) {
            return false;
        }

        // state check
        return super.equals(other);
    }
}
