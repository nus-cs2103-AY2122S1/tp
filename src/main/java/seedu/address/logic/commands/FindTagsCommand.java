package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonTagsContainsTagsPredicate;


/**
 * Finds and lists all persons in address book whose tags contains all of the argument tags.
 * Tag matching is case insensitive.
 */
public class FindTagsCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified tags (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " friend NUS";

    private final PersonTagsContainsTagsPredicate predicate;

    public FindTagsCommand(PersonTagsContainsTagsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof FindTagsCommand) {
            FindTagsCommand otherFindTagsCommand = (FindTagsCommand) other;
            return predicate.equals(otherFindTagsCommand.predicate);
        } else {
            return false;
        }
    }
}
