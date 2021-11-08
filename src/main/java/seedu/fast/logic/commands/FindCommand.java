package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.core.Messages;
import seedu.fast.model.Model;
import seedu.fast.model.person.NameContainsQueriesPredicate;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.RemarkContainsKeywordPredicate;
import seedu.fast.model.person.TagMatchesKeywordPredicate;
import seedu.fast.model.tag.PriorityTag;


/**
 * Finds and lists all persons in FAST whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String TAG_PREFIX = "t/";
    public static final String REMARK_PREFIX = "r/";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all client whose names contain any of "
            + "the specified keywords (case-insensitive) or priority tags, \n"
            + "tags or remarks and displays them as a list with index numbers.\n\n"
            + "Parameters: \nQUERY [MORE_QUERIES]...\n"
            + " OR \n" + PREFIX_PRIORITY_TAG
            + "PRIORITY [MORE_PRIORITIES]...\n"
            + " OR \n" + FindCommand.TAG_PREFIX
            + "TAG [MORE_TAGS]...\n"
            + " OR \n" + FindCommand.REMARK_PREFIX
            + "REMARK [MORE_REMARKS]...\n\n"
            + "Examples: \n" + COMMAND_WORD + " alice bob charlie\n"
            + COMMAND_WORD + " "
            + PREFIX_PRIORITY_TAG
            + PriorityTag.LowPriority.TERM + " " + PriorityTag.MediumPriority.TERM + "\n"
            + COMMAND_WORD + " "
            + FindCommand.TAG_PREFIX + "friends\n"
            + COMMAND_WORD + " "
            + FindCommand.REMARK_PREFIX + "aardvark\n";

    private final Predicate<Person> predicate;

    private final Logger logger = LogsCenter.getLogger(getClass());

    public FindCommand(NameContainsQueriesPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(PriorityPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(TagMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(RemarkContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        logger.info("-----Find Command: Success-----");

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
