package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FLAGS;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_INVALID_PASSWORD;
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
                PREFIX_OLD_PASSWORD, PREFIX_NEW_PASSWORD
        );

        List<String> allOld = argMultimap.getAllValues(PREFIX_OLD_PASSWORD);
        List<String> allNew = argMultimap.getAllValues(PREFIX_NEW_PASSWORD);

        // repeated flags
        if (allOld.size() > 1 || allNew.size() > 1) {
            throw new ParseException(MESSAGE_TOO_MANY_FLAGS);
        }

        Optional<String> oldInput = argMultimap.getValue(PREFIX_OLD_PASSWORD);
        Optional<String> newInput = argMultimap.getValue(PREFIX_NEW_PASSWORD);

        // one of the passwords is empty
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

    /**
     * Checks if the given password satisfies the requirements.
     * Returns true if the password meets the requirements, false otherwise.
     *
     * @param password the input password from user.
     * @return Boolean value of the check result.
     */
    public static boolean isValidPassword(String password) {
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Pattern slash = Pattern.compile("/");

        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        Matcher hasSlash = slash.matcher(password);

        return password.length() >= PasswordCommand.MIN_PASSWORD_LENGTH
                && password.length() <= PasswordCommand.MAX_PASSWORD_LENGTH
                && hasLetter.find() && hasDigit.find()
                && hasSpecial.find() && !hasSlash.find();
    }
}
