package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_INDEX;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.AssignInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AssignInterviewCommandParser implements Parser<AssignInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignInterviewCommand
     * and returns an AssignInterviewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AssignInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CANDIDATE_INDEX, PREFIX_INTERVIEW_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_CANDIDATE_INDEX, PREFIX_INTERVIEW_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignInterviewCommand.MESSAGE_USAGE));
        }

        Index interviewIndex;
        try {
            interviewIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INTERVIEW_INDEX).orElse(""));
        } catch (ParseException pe) {
            throw new ParseException(ParserUtil.MESSAGE_EMPTY_INTERVIEW_INDEXES);
        }

        Set<Index> candidateIndexes;
        String candidateIndexInput = argMultimap.getValue(PREFIX_CANDIDATE_INDEX).orElse("");

        candidateIndexes = ParserUtil.parseCandidateIndexes(candidateIndexInput);
        return new AssignInterviewCommand(interviewIndex, candidateIndexes);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
