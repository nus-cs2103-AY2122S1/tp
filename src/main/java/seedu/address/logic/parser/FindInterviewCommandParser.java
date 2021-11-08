package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.interview.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.FindInterviewCommandPredicate;





/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindInterviewCommandParser implements Parser<FindInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CANDIDATE_INDEX, PREFIX_DATE, PREFIX_INTERVIEW_STATUS,
                PREFIX_POSITION, PREFIX_TIME);


        FindInterviewCommandPredicate findInterviewCommandPredicate = new FindInterviewCommandPredicate();

        if (argMultimap.getValue(PREFIX_CANDIDATE_INDEX).isPresent()) {
            findInterviewCommandPredicate.setCandidateKeywords(ParserUtil.parseKeywords(argMultimap
                    .getValue(PREFIX_CANDIDATE_INDEX)
                    .get()));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            findInterviewCommandPredicate.setDateKeywords(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_INTERVIEW_STATUS).isPresent()) {
            findInterviewCommandPredicate.setInterviewStatusKeywords(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_INTERVIEW_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            findInterviewCommandPredicate.setPositionKeywords(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_POSITION).get()));
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            findInterviewCommandPredicate.setTimeKeywords(
                    ParserUtil.parseTimeKeywords(argMultimap.getValue(PREFIX_TIME).get()));
        }


        if (!findInterviewCommandPredicate.isAnyField()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));
        }

        return new FindInterviewCommand(findInterviewCommandPredicate);
    }

}
