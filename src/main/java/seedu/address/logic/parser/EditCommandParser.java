package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

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

        EditStudentDescriptor editstudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editstudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editstudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap
                    .getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_ID).isPresent()) {
            editstudentDescriptor.setClassId(ParserUtil.parseClassId(argMultimap.getValue(PREFIX_CLASS_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editstudentDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        if (!editstudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editstudentDescriptor);
    }

}
