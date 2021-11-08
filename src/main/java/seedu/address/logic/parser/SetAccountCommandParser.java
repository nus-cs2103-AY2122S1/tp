package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASS;

import seedu.address.logic.commands.SetAccountCommand;
import seedu.address.logic.commands.SetAccountCommand.EditAccountDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAccountCommand object.
 */
public class SetAccountCommandParser implements Parser<SetAccountCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAccountCommand
     * and returns an EditAccountCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SetAccountCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PASS);

        if (argMultimap.getValue(PREFIX_PASS).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetAccountCommand.MESSAGE_USAGE));
        }

        EditAccountDescriptor editAccountDescriptor = new EditAccountDescriptor();
        editAccountDescriptor.setPassword(ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASS).get()));

        return new SetAccountCommand(editAccountDescriptor);
    }
}
