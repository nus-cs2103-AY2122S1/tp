package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_HELP_COMMAND_FORMAT;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.HelpParseException;
import seedu.fast.logic.parser.ParserUtil;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        return new HelpCommand(args); //Since none of the inputs are valid, we can return the arg.
    }

    /**
     * Extracts the arguments from a help command.
     *
     * @param commandText The input text.
     * @return The args of the help command, or "" if there is no or invalid args.
     * @throws HelpParseException if help is not followed by a valid arg
     */
    public static String getArgs (String commandText) throws HelpParseException{

        // if there are no args
        if (commandText.split(" ").length == 1) {
            return "";
        }

        String arg = commandText.substring(HelpCommand.COMMAND_WORD.length());
        String trimmedArgs = arg.trim();
        String capitalisedArg = ParserUtil.capitaliseFirstLetters(trimmedArgs);

            if (!ParserUtil.parseHelp(capitalisedArg).equals("")) {
                return capitalisedArg;

            } else { // if the arg does not match a given command, throw exception
                throw new HelpParseException(
                        String.format(MESSAGE_INVALID_HELP_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            }

    }

}
