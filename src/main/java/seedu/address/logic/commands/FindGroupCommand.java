package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroupContainsKeywordsPredicate;

/**
 * Finds and lists all tutorial groups in ClassMATE whose class code and tutorial group contains any of the argument
 * keywords.
 * Keyword matching is case-insensitive.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "findc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutorial groups whose class code and"
            + "tutorial group contain any of the specified keywords (case-insensitive) and displays them as a list"
            + "with index numbers.\n"
            + "Parameters: "
            + PREFIX_CLASSCODE + "KEYWORD"
            + "[" + PREFIX_TYPE + "KEYWORD\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    private final TutorialGroupContainsKeywordsPredicate predicate;

    public FindGroupCommand(TutorialGroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialGroupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORIAL_GROUPS_LISTED_OVERVIEW,
                        model.getFilteredTutorialGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate)); // state check
    }
}
