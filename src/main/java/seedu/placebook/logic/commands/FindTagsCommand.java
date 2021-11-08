package seedu.placebook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.placebook.commons.core.Messages;
import seedu.placebook.model.Model;
import seedu.placebook.model.person.PersonHasTagsPredicate;
import seedu.placebook.ui.Ui;

/**
 * Finds and lists all persons in Placebook whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagsCommand extends Command {

    public static final String COMMAND_WORD = "findTags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CompanyA HR Married";

    private final PersonHasTagsPredicate predicate;

    public FindTagsCommand(PersonHasTagsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.updateState(COMMAND_WORD);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagsCommand // instanceof handles nulls
                && predicate.equals(((FindTagsCommand) other).predicate)); // state check
    }
}
