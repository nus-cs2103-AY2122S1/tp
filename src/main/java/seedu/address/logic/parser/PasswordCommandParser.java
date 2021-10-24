package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (allOld.size() > 1 || allNew.size() > 1) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_FLAGS, PasswordCommand.MESSAGE_USAGE));
        }

        Optional<String> oldInput = argMultimap.getValue(PREFIX_OLD_PASSWORD);
        Optional<String> newInput = argMultimap.getValue(PREFIX_NEW_PASSWORD);

        if (oldInput.isEmpty() || newInput.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PASSWORD, PasswordCommand.MESSAGE_USAGE));
        }
        String oldPassword = oldInput.get();
        String newPassword = newInput.get();

        if (validatePassword(oldPassword) && validatePassword(newPassword)) {
            return new PasswordCommand(oldPassword, newPassword);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_PASSWORD + PasswordCommand.CORRECT_PASSWORD_FORMAT
                , PasswordCommand.MESSAGE_USAGE));
    }

    private boolean validatePassword(String password) {
        return passwordValidation(password);
    }

    public static boolean passwordValidation(String password) {
        if(password.length()>=PasswordCommand.MIN_PASSWORD_LENGTH) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find()
                    && hasSpecial.find();
        } else {
            return false;
        }
    }
}
