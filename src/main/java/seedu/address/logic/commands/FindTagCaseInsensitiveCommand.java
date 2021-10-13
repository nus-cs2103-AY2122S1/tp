package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonTagsContainsCaseInsensitiveTagsPredicate;
import seedu.address.model.person.PersonTagsContainsTagsPredicate;


/**
 * Finds and lists all persons in address book whose tags contains all of the argument tags.
 * Tag matching is case sensitive or insensitive based on command word.
 */
public class FindTagCaseInsensitiveCommand extends FindTagCommand {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified tags (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " friend NUS";

    public FindTagCaseInsensitiveCommand(PersonTagsContainsCaseInsensitiveTagsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof FindTagCaseInsensitiveCommand) {
            FindTagCaseInsensitiveCommand otherFindTagCaseInsensitiveCommand = (FindTagCaseInsensitiveCommand) other;
            return predicate.equals(otherFindTagCaseInsensitiveCommand.predicate);
        } else {
            return false;
        }
    }
}
