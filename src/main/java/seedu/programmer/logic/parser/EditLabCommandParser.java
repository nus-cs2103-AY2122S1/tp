package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_TEMPLATE;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.commands.EditLabCommand.MESSAGE_ARGUMENT_SHOULD_BE_SPECIFIED;
import static seedu.programmer.logic.commands.EditLabCommand.MESSAGE_MISSING_LAB_TO_BE_EDITED;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NEW_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;
import static seedu.programmer.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.programmer.logic.commands.EditLabCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabTotal;


/**
 * Parses input arguments and creates a new EditLabCommand object.
 */
public class EditLabCommandParser implements Parser<EditLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditLabCommand
     * and returns an EditLabCommand object for execution.
     *
     * @param args The String arguments as given by the user.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditLabCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap;
        try {
            // this line may throw InvalidArgFlagsException while trying to tokenize the user input
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_LAB_NUM, PREFIX_LAB_NEW_LAB_NUM, PREFIX_LAB_TOTAL);

            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE));
            }

            if (!arePrefixesPresent(argMultimap, PREFIX_LAB_NUM)) {
                throw new ParseException(String.format(MESSAGE_MISSING_LAB_TO_BE_EDITED, EditLabCommand.MESSAGE_USAGE));
            }
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), EditLabCommand.MESSAGE_USAGE));
        }

        boolean isNewLabNum = argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).isPresent();
        boolean isNewTotal = argMultimap.getValue(PREFIX_LAB_TOTAL).isPresent();
        if (!isNewLabNum && !isNewTotal) {
            throw new ParseException(MESSAGE_ARGUMENT_SHOULD_BE_SPECIFIED);
        }

        try {
            return createEditLabCommand(argMultimap, isNewLabNum, isNewTotal);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_TEMPLATE, pe.getMessage(), EditLabCommand.MESSAGE_USAGE));
        }
    }

    private EditLabCommand createEditLabCommand(ArgumentMultimap argMultimap, boolean isNewLabNum, boolean isTotal)
            throws ParseException {
        LabNum labNum = new LabNum(ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null)));
        Lab labResult = new Lab(labNum);
        LabNum newLabNum = null;
        LabTotal total = null;

        if (isNewLabNum) {
            newLabNum = new LabNum(ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).orElse(null)));
        }

        if (isTotal) {
            total = new LabTotal(ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null)));
        }

        // At least one of new lab number and total score must be provided.
        assert newLabNum != null || total != null;
        if (isNewLabNum && isTotal) {
            return new EditLabCommand(labResult, newLabNum, total);
        } else if (isNewLabNum) {
            return new EditLabCommand(labResult, newLabNum);
        } else {
            return new EditLabCommand(labResult, total);
        }
    }
}
