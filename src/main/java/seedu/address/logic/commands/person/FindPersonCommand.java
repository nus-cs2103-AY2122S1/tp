package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in contHACKS whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String MESSAGE_USAGE = "find: Finds all persons whose names contain "
            + "any of the specified keywords (case-insensitive).\n"
            + "You can choose one of two ways to search:\n"
            + "1) search by name(s) using the prefix 'n/': " + "find " + PREFIX_NAME + "alice bob charlie\n"
            + "2) search by module code(s) using the prefix 'm/': " + "find " + PREFIX_MODULE_CODE + "CS2030S CS2100\n";
    public static final String MESSAGE_SINGLE_PREFIX_SEARCH = "You can only search with a single prefix.";

    private final Predicate<Person> predicate;

    public FindPersonCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindPersonCommand(ModuleCodesContainsKeywordsPredicate predicate) {
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
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
