package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Finds all persons whose details contain every "
            + "specified keywords and displays them as a list with index numbers.\n\n"
            + "Parameters: FIELD_PREFIX/KEYWORD [FIELD_PREFIX/KEYWORD]...\n"
            + "[FIELD_PREFIX/KEYWORD] can be any of the following:\n"
            + "[" + PREFIX_NAME + "NAME], "
            + "[" + PREFIX_GENDER + "GENDER], "
            + "[" + PREFIX_PHONE + "PHONE], "
            + "[" + PREFIX_EMAIL + "EMAIL], "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY], "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP], "
            + "[" + PREFIX_REMARK + "REMARK], "
            + "[" + PREFIX_SOCIAL_HANDLE + "SOCIAL_HANDLE_USERID], "
            + "[" + PREFIX_TAG + "TAG]\n\n"
            + "Note: \n"
            + " - Keywords are case-insensitive. \n\n"
            + "Example: " + COMMAND_WORD + " n/alice g/f p/91234567 tg/19";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_VALUE = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "A empty %s was entered. \n%1$s");
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_PREDICATE = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No valid keyword entered. \n%1$s");

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
