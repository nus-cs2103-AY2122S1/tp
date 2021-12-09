package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.times.commons.core.Messages;
import seedu.times.model.Model;
import seedu.times.model.person.predicates.TagsContainsKeywordsPredicate;
import seedu.times.ui.TabName;

/**
 * Finds and lists all persons in Timestable whose list of tags contains any of the search terms.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose list of tags contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [, [KEYWORD]...]\n"
            + "Example: " + COMMAND_WORD + " Maths, Physics";

    private final TagsContainsKeywordsPredicate predicate;

    public FindTagCommand(TagsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        updateView(TabName.STUDENTS);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
