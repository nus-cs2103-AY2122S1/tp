package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details contain every "
            + "specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "[" + PREFIX_NAME + "NAME] " + "[" + PREFIX_NAME + "MORE_NAMES] "
            + "[" + PREFIX_GENDER + "GENDER] " + "[" + PREFIX_GENDER + "MORE_GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] " + "[" + PREFIX_PHONE + "MORE_PHONES] "
            + "[" + PREFIX_EMAIL + "EMAIL] " + "[" + PREFIX_EMAIL + "MORE_EMAILS] "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY] " + "[" + PREFIX_NATIONALITY + "MORE_NATIONALITY] "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP] " + "[" + PREFIX_TUTORIAL_GROUP + "MORE_TUTORIAL_GROUPS] "
            + "[" + PREFIX_SOCIAL_HANDLE + "SOCIAL_HANDLE] " + "[" + PREFIX_SOCIAL_HANDLE + "MORE_SOCIAL_HANDLE] "
            + "[" + PREFIX_REMARK + "REMARK] " + "[" + PREFIX_REMARK + "MORE_REMARKS] "
            + "[" + PREFIX_TAG + "TAG] " + "[" + PREFIX_TAG + "MORE_TAGS]...\n"
            + "Note: \n"
            + " - Keywords are case-insensitive."
            + "Example: " + COMMAND_WORD + " n/alice g/f p/91234567 tg/19";

    private Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
