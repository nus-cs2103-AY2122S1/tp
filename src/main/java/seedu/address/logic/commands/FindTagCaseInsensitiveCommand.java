package seedu.address.logic.commands;

import seedu.address.model.person.PersonTagsContainsCaseInsensitiveTagsPredicate;

/**
 * Finds and lists all persons in address book whose tags contains all of the argument tags.
 * Tag matching is case insensitive based on command word.
 */
public class FindTagCaseInsensitiveCommand extends FindTagCommand {

    public static final String COMMAND_WORD = "findTag";
    public static final String COMMAND_DESCRIPTION = "Finds all persons whose names contain any of "
            + "the specified tags (case-insensitive) and displays them as a list with index numbers.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " friend NUS";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

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
            return getPredicate().equals(otherFindTagCaseInsensitiveCommand.getPredicate());
        } else {
            return false;
        }
    }

    @Override
    public PersonTagsContainsCaseInsensitiveTagsPredicate getPredicate() {
        return (PersonTagsContainsCaseInsensitiveTagsPredicate) super.getPredicate();
    }
}
