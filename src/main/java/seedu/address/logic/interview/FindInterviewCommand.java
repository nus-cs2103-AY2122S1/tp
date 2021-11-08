package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.interview.FindInterviewCommandPredicate;


/**
 * Finds and lists all persons in HR Manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindInterviewCommand extends Command {

    public static final String COMMAND_WORD = "find_i";

    public static final String MESSAGE_SUCCESS = "Listed all found interviews";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all interviews that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_POSITION + "TITLE]... "
            + "[" + PREFIX_INTERVIEW_STATUS + "INTERVIEW STATUS]... "
            + "[" + PREFIX_TIME + "TIME]... "
            + "[" + PREFIX_DATE + "DATE]... "
            + "[" + PREFIX_CANDIDATE_INDEX + "CANDIDATE NAME]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_POSITION + "accountant" + " " + PREFIX_DATE + "21/09/2021";
    private final FindInterviewCommandPredicate predicate;



    public FindInterviewCommand(FindInterviewCommandPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERVIEW_LISTED_OVERVIEW, model.getFilteredInterviewList().size()),
            CommandResult.CommandType.FIND_I);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInterviewCommand // instanceof handles nulls
                && predicate.equals(((FindInterviewCommand) other).predicate)); // state check
    }

}
