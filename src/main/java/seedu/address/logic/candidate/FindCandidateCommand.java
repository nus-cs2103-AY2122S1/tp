package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.FindCandidateCommandPredicate;


/**
 * Finds and lists all persons in HR Manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCandidateCommand extends Command {

    public static final String COMMAND_WORD = "find_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters:" + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_ADDRESS + "ADDRESS]... "
            + "[" + PREFIX_EMAIL + "EMAIL]... "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_POSITION + "POSITION]... "
            + "[" + PREFIX_STATUS + "STATUS]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie";
    public static final String MESSAGE_SUCCESS = "Listed all found candidates";

    private final FindCandidateCommandPredicate predicate;

    public FindCandidateCommand(FindCandidateCommandPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                CommandResult.CommandType.FIND_C);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCandidateCommand // instanceof handles nulls
                && predicate.equals(((FindCandidateCommand) other).predicate)); // state check
    }

}
