package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.EditStudentCommand.EditStudentDescriptor;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditStudentCommand;
import tutoraid.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditStudentCommand object
 */
public class EditStudentCommandParser implements Parser<EditStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStudentCommand
     * and returns an EditStudentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE,
                        PREFIX_PARENT_NAME, PREFIX_PARENT_PHONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_STUDENT_NAME).isPresent()) {
            editStudentDescriptor.setStudentName(
                    ParserUtil.parseStudentName(argMultimap.getValue(PREFIX_STUDENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_PHONE).isPresent()) {
            editStudentDescriptor.setStudentPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_STUDENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_PARENT_NAME).isPresent()) {
            editStudentDescriptor.setParentName(
                    ParserUtil.parseParentName(argMultimap.getValue(PREFIX_PARENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PARENT_PHONE).isPresent()) {
            editStudentDescriptor.setParentPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PARENT_PHONE).get()));
        }
        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStudentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStudentCommand(index, editStudentDescriptor);
    }
}
