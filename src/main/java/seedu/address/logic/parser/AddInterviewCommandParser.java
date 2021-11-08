package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.position.Position;

public class AddInterviewCommandParser implements Parser<AddInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewCommand
     * and returns an AddInterviewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_CANDIDATE_INDEX, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_DURATION, PREFIX_INTERVIEW_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_POSITION, PREFIX_DATE, PREFIX_TIME,
                PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
        }

        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        LocalTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        InterviewStatus interviewStatus = ParserUtil.parseInterviewStatus(argMultimap
                .getValue(PREFIX_INTERVIEW_STATUS).orElse(""));

        Set<Index> indexes;
        String candidateIndexes = argMultimap.getValue(PREFIX_CANDIDATE_INDEX).orElse("");
        if (candidateIndexes.equals("")) {
            indexes = new HashSet<>();
        } else {
            indexes = ParserUtil.parseCandidateIndexes(candidateIndexes);
        }

        Interview interview = new Interview(position, new HashSet<>(), date, time, duration, interviewStatus);

        return new AddInterviewCommand(interview, indexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
