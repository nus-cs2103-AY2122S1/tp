package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUS_NETWORK_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SortCommand object
 */

public class SortCommandParser implements Parser {

    private static List<Prefix> prefixList = Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_TAG, PREFIX_GITHUB_ID, PREFIX_STUDENT_ID, PREFIX_TYPE, PREFIX_TUTORIAL_ID, PREFIX_NUS_NETWORK_ID);

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        boolean reverse;
        List<String> argList = Arrays.asList(args.trim().split("\\s+"));
        if (argList.size() == 2 && argList.get(1).equals("-r")) {
            reverse = true;
        } else if (argList.size() == 1 && argList.get(0).equals("-r")) {
            reverse = true;
        } else {
            reverse = false;
        }
        String argsTrimmed = argList.get(0);
        Prefix prefix;
        if (argsTrimmed.isEmpty() || argsTrimmed.equals("-r")) {
            prefix = new Prefix("n/");
        } else {
            prefix = new Prefix(argsTrimmed);
        }
        if (!prefixList.contains(prefix)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        System.out.print(argsTrimmed + " " + reverse + "\n");
        return new SortCommand(prefix, reverse);
    }
}
