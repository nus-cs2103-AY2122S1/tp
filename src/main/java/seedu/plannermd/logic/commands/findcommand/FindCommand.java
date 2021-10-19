package seedu.plannermd.logic.commands.findcommand;

import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;

/**
 * Represents a Find command with hidden internal logic and the ability to be executed.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    protected final NameContainsKeywordsPredicate predicate;

    protected FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
}
