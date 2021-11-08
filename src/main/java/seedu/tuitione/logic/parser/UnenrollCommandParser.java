package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.StringUtil;
import seedu.tuitione.logic.commands.UnenrollCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnenrollCommand object.
 */
public class UnenrollCommandParser implements Parser<UnenrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnenrollCommand
     * and returns a UnenrollCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UnenrollCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON);

        String indexStudentString = argMultimap.getPreamble();
        String indexLessonString = argMultimap.getValue(PREFIX_LESSON).orElseThrow(() ->
                new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnenrollCommand.MESSAGE_USAGE)));

        if (!StringUtil.isAStringedNumber(indexStudentString)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnenrollCommand.MESSAGE_USAGE));
        }
        Index indexStudent = ParserUtil.parseIndex(indexStudentString);

        if (!StringUtil.isAStringedNumber(indexLessonString)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnenrollCommand.MESSAGE_USAGE));
        }
        Index indexLesson = ParserUtil.parseIndex(indexLessonString);

        return new UnenrollCommand(indexStudent, indexLesson);
    }
}
