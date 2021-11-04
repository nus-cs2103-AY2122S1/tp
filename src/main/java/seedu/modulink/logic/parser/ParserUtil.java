package seedu.modulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.commons.core.index.Index;
import seedu.modulink.commons.util.StringUtil;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (trimmedName.contains("/")) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (trimmedPhone.contains("/")) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
        }
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (trimmedEmail.contains("/")) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
        }
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String gitHubUsername} into a {@code GitHubUsername}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gitHubUsername} is invalid.
     */
    public static GitHubUsername parseGithubUsername(String gitHubUsername) throws ParseException {
        if (gitHubUsername != null) {
            String trimmedUsername = gitHubUsername.trim();
            if (trimmedUsername.contains("/")) {
                throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
            }
            if (!GitHubUsername.isValidUsername(gitHubUsername)) {
                throw new ParseException(GitHubUsername.MESSAGE_CONSTRAINTS);
            }
            return new GitHubUsername(trimmedUsername);
        } else {
            return new GitHubUsername(null);
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Mod parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (trimmedTag.contains("/")) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
        }
        if (!Mod.isValidTagName(trimmedTag)) {
            throw new ParseException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Mod> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Mod> modSet = new HashSet<>();
        for (String tagName : tags) {
            modSet.add(parseTag(tagName));
        }
        return modSet;
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code StudentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedId = studentId.trim();
        if (trimmedId.contains("/")) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
        }
        if (!StudentId.isValidId(studentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedId);
    }

    /**
     * Parses a {@code String telegramHandle} into a {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegramHandle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String telegramHandle) throws ParseException {
        if (telegramHandle != null) {
            String trimmedHandle = telegramHandle.trim();

            if (trimmedHandle.startsWith("@")) {
                trimmedHandle = trimmedHandle.substring(1);
            }
            if (trimmedHandle.contains("/")) {
                throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, ""));
            }
            if (!TelegramHandle.isValidHandle(telegramHandle)) {
                throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
            }
            return new TelegramHandle(trimmedHandle);
        } else {
            return new TelegramHandle(null);
        }
    }

    /**
     * Checks how many valid prefixes are present in args.
     *
     * @param argMultimap tokenized list of arguments.
     * @return number of provided prefixes.
     */
    public static int numberOfValidPrefixes(ArgumentMultimap argMultimap) {
        int i = 0;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_GITHUB_USERNAME).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            i++;
        }
        return i;
    }

    /**
     * Returns if any prefixes that are duplicates.
     * {@code ArgumentMultimap}.
     */
    public static boolean isDuplicatePrefix(String args, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            String prefixAsString = " " + prefix.getPrefix();
            if (StringUtil.countMatch(args, prefixAsString) > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns any prefixes that are duplicates.
     * {@code ArgumentMultimap}.
     */
    public static StringBuilder findDuplicatePrefixes(String args, Prefix... prefixes) {
        StringBuilder duplicatePrefixesList = new StringBuilder();
        for (Prefix prefix : prefixes) {
            String prefixAsString = " " + prefix.getPrefix();
            if (StringUtil.countMatch(args, prefixAsString) > 1) {
                duplicatePrefixesList.append(prefix).append(" ");
            }
        }
        return duplicatePrefixesList;
    }

    static void checkDuplicate(String args, ArgumentMultimap argMultimap, boolean duplicatePrefix)
            throws ParseException {
        if (ParserUtil.numberOfValidPrefixes(argMultimap) != StringUtil.countMatch(args, '/')) {
            if (duplicatePrefix) {
                throw new ParseException("error");
            }
        }
    }
}
