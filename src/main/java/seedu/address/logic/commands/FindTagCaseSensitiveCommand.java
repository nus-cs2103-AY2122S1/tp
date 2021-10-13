package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonTagsContainsCaseSensitiveTagsPredicate;
import seedu.address.model.person.PersonTagsContainsTagsPredicate;

import static java.util.Objects.requireNonNull;


/**
 * Finds and lists all persons in address book whose tags contains all of the argument tags.
 * Tag matching is case sensitive or insensitive based on command word.
 */
public class FindTagCaseSensitiveCommand extends FindTagCommand {

    public static final String COMMAND_WORD = "findTagC";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified tags (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " friend NUS";

    public FindTagCaseSensitiveCommand(PersonTagsContainsCaseSensitiveTagsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof FindTagCaseSensitiveCommand) {
            FindTagCaseSensitiveCommand otherFindTagCaseInsensitiveCommand = (FindTagCaseSensitiveCommand) other;
            return predicate.equals(otherFindTagCaseInsensitiveCommand.predicate);
        } else {
            return false;
        }
    }
}
