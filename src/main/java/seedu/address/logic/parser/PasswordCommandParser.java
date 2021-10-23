package seedu.address.logic.parser;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class PasswordCommandParser implements Parser<PasswordCommand> {
    private static final String DEFAULT_EMPTY_STRING = "";

    @Override
    public PasswordCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(ParserUtil.mapPrefixesToShortForm(args),
                PREFIX_OLD_PASSWORD,
                PREFIX_NEW_PASSWORD
        );

        String oldPassword = argMultimap.getValue(PREFIX_OLD_PASSWORD).orElse("");
        String newPassword = argMultimap.getValue(PREFIX_NEW_PASSWORD).orElse("");

        if (oldPassword == "" || newPassword == "") {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE));
        }
        return new PasswordCommand(oldPassword, newPassword);
    }
}
