package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_DEADLINE_OF_APPLICATION, PREFIX_REQUIREMENT, PREFIX_INTERVIEW_DATE_AND_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                PREFIX_DEADLINE_OF_APPLICATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).get());
        Completion completion = new Completion("Uncompleted");
        Status status = new Status("Pending");
        Priority priority = new Priority("Medium");
        Set<Requirement> requirementList = ParserUtil.parseRequirements(argMultimap.getAllValues(PREFIX_REQUIREMENT));
        Set<InterviewDateAndTime> interviewDateAndTimeList = ParserUtil.parseInterviewDateAndTimes(
                argMultimap.getAllValues(PREFIX_INTERVIEW_DATE_AND_TIME));

        Application application = new Application(company, position, deadline, completion, status, priority,
                requirementList, interviewDateAndTimeList);

        return new AddCommand(application);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
