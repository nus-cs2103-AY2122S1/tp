package seedu.address.logic.parser;

import seedu.address.logic.commands.AddAssignmentToAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Status;
import seedu.address.model.person.Module;

import java.util.Locale;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddAssignmentCommand object
 */
public class AddAssignmentToAllCommandParser implements Parser<AddAssignmentToAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddAssignmentToAllCommand
     * and returns an AddAssignmentToAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssignmentToAllCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_DESCRIPTION, PREFIX_DUEDATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_DESCRIPTION, PREFIX_DUEDATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentToAllCommand.MESSAGE_USAGE));
        }

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get().toUpperCase(Locale.ROOT));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        DueDate dueDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DUEDATE).get());

        Assignment assignment = new Assignment(description, dueDate, Status.createPendingStatus());

        return new AddAssignmentToAllCommand(module, assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
