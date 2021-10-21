package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {

        // One whitespace required before first prefix.
        String trimmedArgs = " " + args;

        List<Prefix> prefixList = ArgumentTokenizer.findAllPrefixSorted(trimmedArgs, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION,
                PREFIX_EXPERIENCE, PREFIX_INTERVIEW, PREFIX_TAG);

        // If show command has no prefix, it is invalid
        if (prefixList.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        // create ShowCommand with the first prefix input by user
        return new ShowCommand(prefixList.get(0));
    }

}
