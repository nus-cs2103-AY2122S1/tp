package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.PasswordUtil.isValidPassword;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_INVALID_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class PasswordCommandParser implements Parser<PasswordCommand> {


    @Override
    public PasswordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(ParserUtil.mapPrefixesToShortForm(args),
                PREFIX_OLD_PASSWORD,
                PREFIX_NEW_PASSWORD
        );

        List<String> allOld = argMultimap.getAllValues(PREFIX_OLD_PASSWORD);
        List<String> allNew = argMultimap.getAllValues(PREFIX_NEW_PASSWORD);

        // repeated flags
        if (allOld.size() > 1 || allNew.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE));
        }

        Optional<String> oldInput = argMultimap.getValue(PREFIX_OLD_PASSWORD);
        Optional<String> newInput = argMultimap.getValue(PREFIX_NEW_PASSWORD);

        // missing flags
        if (oldInput.isEmpty() || newInput.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE));
        }

        String oldPassword = oldInput.get();
        String newPassword = newInput.get();

        // new password invalid format
        if (!isValidPassword(newPassword)) {
            throw new ParseException(MESSAGE_INVALID_PASSWORD + System.lineSeparator()
                    + "NEW " + PasswordCommand.CORRECT_PASSWORD_FORMAT);
        }

        // valid old and new password format
        return new PasswordCommand(oldPassword, newPassword);
    }

}
