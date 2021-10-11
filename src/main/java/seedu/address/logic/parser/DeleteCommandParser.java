package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String userInput) throws ParseException {
        //todo: parse command using basic command format to figure out which type

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final String commandWord = "delete " + matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case DeleteModuleCommand.COMMAND_WORD:
                return new DeleteModuleCommandParser().parse(arguments);
            case DeleteTaskCommand.COMMAND_WORD:
                return null; //to be implemented
            case DeleteStudentCommand.COMMAND_WORD:
                return null; //to be implemented
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

//        try {
//
//
//
//        } catch (ParseException pe) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
//        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
