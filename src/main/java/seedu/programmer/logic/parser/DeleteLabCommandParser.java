package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.programmer.logic.commands.DeleteLabCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;


/**
 * Parses input arguments and creates a new DeleteLabCommand object.
 */
public class DeleteLabCommandParser implements Parser<DeleteLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLabCommand
     * and returns an DeleteLabCommand object for execution.
     *
     * @param args The String arguments as given by the user.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteLabCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LAB_NUM);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), DeleteLabCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_LAB_NUM)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENT, DeleteLabCommand.MESSAGE_USAGE));
        }

        LabNum labNum = new LabNum(ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse("")));
        Lab labResult = new Lab(labNum);
        return new DeleteLabCommand(labResult);
    }
}
