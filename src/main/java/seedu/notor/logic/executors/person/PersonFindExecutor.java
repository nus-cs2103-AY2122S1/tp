package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.PersonContainsPredicate;

/**
 * Executor for a PersonFindCommand.
 */
public class PersonFindExecutor extends PersonExecutor {

    private final PersonContainsPredicate predicate;

    /**
     * Constructor for a PersonFindExecutor instance.
     */
    public PersonFindExecutor(PersonContainsPredicate predicate) {
        super(null);
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
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
        if (!(other instanceof PersonFindExecutor)) {
            return false;
        }

        PersonFindExecutor e = (PersonFindExecutor) other;

        // state check
        return super.equals(other) && predicate.equals(e.predicate);
    }
}
