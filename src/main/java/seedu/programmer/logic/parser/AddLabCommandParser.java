package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;
import static seedu.programmer.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.programmer.logic.commands.AddLabCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabTotal;


/**
 * Parses input arguments and creates a new AddLabCommand object.
 */
public class AddLabCommandParser implements Parser<AddLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLabCommand
     * and returns an AddLabCommand object for execution.
     *
     * @param args The String arguments as given by the user.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddLabCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LAB_NUM, PREFIX_LAB_TOTAL);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), AddLabCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_LAB_NUM, PREFIX_LAB_TOTAL)) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENT, AddLabCommand.MESSAGE_USAGE));
        }

        LabNum labNum = new LabNum(ParserUtil.parseLabNum(
                argMultimap.getValue(PREFIX_LAB_NUM).orElse(null)));
        LabTotal total = new LabTotal(ParserUtil.parseTotal(
                argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null)));
        Lab labResult = new Lab(labNum, total);
        return new AddLabCommand(labResult);
    }
}
