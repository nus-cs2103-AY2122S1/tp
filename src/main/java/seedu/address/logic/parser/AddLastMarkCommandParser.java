package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddLastMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentMark;

/**
 * Parses input arguments and creates a new AddLastMarkCommand object
 */
public class AddLastMarkCommandParser implements Parser<AddLastMarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLastMarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MARK);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                            AddLastMarkCommand.MESSAGE_USAGE), pe);
        }

        StudentMark newMark = null;

        if (argMultimap.getValue(PREFIX_MARK).isPresent()) {
            newMark = ParserUtil.parseMark(argMultimap.getValue(PREFIX_MARK).get());
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLastMarkCommand.MESSAGE_NOT_EDITED)
            );
        }

        return new AddLastMarkCommand(index, newMark);
    }

}
