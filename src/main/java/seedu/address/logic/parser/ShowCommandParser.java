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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeWithoutPreamble(trimmedArgs, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                        PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION,
                        PREFIX_EXPERIENCE, PREFIX_TAG, PREFIX_INTERVIEW);

        // If show command has no prefix, it is invalid
        if (argMultimap.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        ShowDescriptor showDescriptor = new ShowDescriptor(argMultimap);

        return new ShowCommand(showDescriptor.getPrefix());
    }

    public static class ShowDescriptor {
        private Prefix prefix;

        ShowDescriptor(ArgumentMultimap argMultimap) {

            assert !argMultimap.isEmpty() : "ShowDescriptor should not be created with empty ArgumentMultimap";

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                prefix = PREFIX_NAME;
            }

            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                prefix = PREFIX_PHONE;
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                prefix = PREFIX_EMAIL;
            }

            if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
                prefix = PREFIX_ROLE;
            }

            if (argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()) {
                prefix = PREFIX_EMPLOYMENT_TYPE;
            }

            if (argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()) {
                prefix = PREFIX_EXPECTED_SALARY;
            }

            if (argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).isPresent()) {
                prefix = PREFIX_LEVEL_OF_EDUCATION;
            }

            if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
                prefix = PREFIX_EXPERIENCE;
            }

            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                prefix = PREFIX_TAG;
            }

            if (argMultimap.getValue(PREFIX_INTERVIEW).isPresent()) {
                prefix = PREFIX_INTERVIEW;
            }

        }

        public Prefix getPrefix() {
            return prefix;
        }

    }
}
