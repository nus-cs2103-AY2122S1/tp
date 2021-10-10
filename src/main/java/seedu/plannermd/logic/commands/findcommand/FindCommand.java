package seedu.plannermd.logic.commands.findcommand;

import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;

/**
 * Represents a Find command with hidden internal logic and the ability to be executed.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    protected final NameContainsKeywordsPredicate predicate;

    protected FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
}
