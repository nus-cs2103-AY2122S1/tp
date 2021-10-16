package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     * @return
     */
    public ShowCommand parse(String args) throws ParseException {

        // One whitespace required before first prefix.
        String trimmedArgs = " " + args;
        System.out.println(trimmedArgs);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeWithoutPreamble(trimmedArgs, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                        PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION,
                        PREFIX_EXPERIENCE, PREFIX_TAG);

        // If find command has no prefix, it is invalid
        if (argMultimap.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        ShowDescriptor showDescriptor = new ShowDescriptor(argMultimap);

        return new ShowCommand(showDescriptor.getPrefix());
    }

    public static class ShowDescriptor {
        private Prefix prefix;


        ShowDescriptor(ArgumentMultimap argMultimap)  {

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                prefix = PREFIX_NAME;
            } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                prefix = PREFIX_PHONE;
            } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                prefix = PREFIX_EMAIL;
            } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
                prefix = PREFIX_ROLE;
            } else if (argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()) {
                prefix = PREFIX_EMPLOYMENT_TYPE;
            }


        }


        public Prefix getPrefix() {
            return prefix;
        }

        private static String[] splitByWhiteSpace(String arg) {
            return arg.split("\\s+");
        }

    }
}
