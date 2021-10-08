package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_ACTION = "Find Student";

    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_PARAMETERS = "KEYWORD [MORE_KEYWORDS]...";

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    private NameContainsKeywordsPredicate predicate;

    public FindCommand() {
    }

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Returns the description of what the command does.
     *
     * @return Description of what the command does.
     */
    public String getAction() {
        return COMMAND_ACTION;
    }

    /**
     * Returns the format of the valid command with command word and parameters.
     *
     * @return The format of the valid command.
     */
    public String getFormat() {
        return COMMAND_WORD + " " + COMMAND_PARAMETERS;
    }

    /**
     * Returns an example usage of the command.
     *
     * @return Example usage of the command.
     */
    public String getExample() {
        return COMMAND_EXAMPLE;
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
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
