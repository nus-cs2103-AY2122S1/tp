package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUS_NETWORK_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.List;
import java.util.Arrays;

/**
 * Parses input arguments and creates a SortCommand object
 */

public class SortCommandParser implements Parser {

    private static List<Prefix> prefixList = Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_TAG, PREFIX_GITHUB_ID, PREFIX_STUDENT_ID, PREFIX_TYPE, PREFIX_TUTORIAL_ID, PREFIX_NUS_NETWORK_ID);

    public SortCommand parse(String args) throws ParseException {
        String argsTrimmed = args.trim();
        if (argsTrimmed.contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        Prefix prefix;
        if (argsTrimmed.isEmpty()) {
            prefix = new Prefix("n/");
        } else {
            prefix = new Prefix(argsTrimmed);
        }
        if (!prefixList.contains(prefix)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(prefix);
    }
}
