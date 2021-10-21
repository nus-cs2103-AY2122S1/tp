package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n1. Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "2. Finds all persons whose tag(s) (if present) matches the specified tag(s) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters : t/TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " t/friends family"
            + "\n3. Finds all persons whose telegram handles(s) (if present) matches the specified telegram "
            + "handle(s) and displays them as a list with index numbers.\n"
            + "Parameters : te/TELEGRAM_HANDLE [MORE_TELEGRAM_HANDLES]...\n"
            + "Example: " + COMMAND_WORD + " te/alex_1 yuBernice"
            + "\n4. Finds all persons whose github username(s) (if present) matches the specified github "
            + "username(s) and displays them as a list with index numbers.\n"
            + "Parameters : g/GITHUB_USERNAME [MORE_GITHUB_USERNAMES]...\n"
            + "Example: " + COMMAND_WORD + " g/alex-coder bernice";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getPersonListControl() != null) {
            model.setTabIndex(0);
        }
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
