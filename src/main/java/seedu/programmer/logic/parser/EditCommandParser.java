package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.programmer.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_GRADE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editPersonDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_ID).isPresent()) {
            editPersonDescriptor.setClassId(ParserUtil.parseClassId(argMultimap.getValue(PREFIX_CLASS_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editPersonDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

}
