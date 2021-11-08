package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_TEMPLATE;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabResult;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args The String arguments as given by the user.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;
        Index index;
        EditStudentDescriptor editstudentDescriptor;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_EMAIL,
                            PREFIX_LAB_NUM, PREFIX_LAB_RESULT);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), EditCommand.MESSAGE_USAGE));
        }

        try {
            // ParseException will be thrown from the parseIndex method if index from Preamble cannot be parsed
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            editstudentDescriptor = new EditStudentDescriptor();

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
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editstudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (argMultimap.getValue(PREFIX_LAB_NUM).isPresent()
                    && argMultimap.getValue(PREFIX_LAB_RESULT).isPresent()) {
                LabNum labNum = new LabNum(ParserUtil.parseLabNum(
                        argMultimap.getValue(PREFIX_LAB_NUM).orElse(null)));
                LabResult result = new LabResult(ParserUtil.parseResult(
                        argMultimap.getValue(PREFIX_LAB_RESULT).orElse(null)));
                Lab labResult = new Lab(labNum);
                editstudentDescriptor.setLab(labResult, result);
            } else if (argMultimap.getValue(PREFIX_LAB_NUM).isPresent()
                    || argMultimap.getValue(PREFIX_LAB_RESULT).isPresent()) {
                //if only one of the required two arguments provided, we will throw an exception
                throw new ParseException(Lab.MESSAGE_LAB_SCORE_AND_LAB_NUMBER_REQUIREMENT);
            }

            if (!editstudentDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_TEMPLATE, pe.getMessage(), EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editstudentDescriptor);
    }

}
