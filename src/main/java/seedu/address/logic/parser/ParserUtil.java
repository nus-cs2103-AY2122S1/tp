package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.LONG_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.LONG_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.LONG_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.LONG_PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.LONG_PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String REGEX_SURROUNDING_DOUBLE_QUOTE = "^\"|\"$";
    // Matches word NOT surrounded by "double quotes"
    public static final String TEMPLATE_REGEX_REPLACEMENT_PATTERN = "(?<![\"'])((?i)%s)(?![\"'])";

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
     * Leading and trailing whitespaces, and surrounding double quote marks will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String sanitizedName = name.trim().replaceAll(REGEX_SURROUNDING_DOUBLE_QUOTE, "");
        if (!Name.isValidName(sanitizedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(sanitizedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces, and surrounding double quote marks will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String sanitizedPhone = phone.trim().replaceAll(REGEX_SURROUNDING_DOUBLE_QUOTE, "");
        if (!Phone.isValidPhone(sanitizedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(sanitizedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces, and surrounding double quote marks will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String sanitizedAddress = address.trim().replaceAll(REGEX_SURROUNDING_DOUBLE_QUOTE, "");
        if (!Address.isValidAddress(sanitizedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(sanitizedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces, and surrounding double quote marks will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String sanitizedEmail = email.trim().replaceAll(REGEX_SURROUNDING_DOUBLE_QUOTE, "");
        if (!Email.isValidEmail(sanitizedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(sanitizedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagSequence : tags) { // {"tag1", "tag2,tag3"}
            String[] tokenizedTags = tagSequence.trim().replaceAll(REGEX_SURROUNDING_DOUBLE_QUOTE, "").split(",");
            for (String tag : tokenizedTags) {
                tagSet.add(parseTag(tag));
            }
        }
        return tagSet;
    }

    /**
     * Replaces the long form prefixes in the argument with their respective short forms.
     */
    public static String longToShortFormPrefixConverter(String args) {
        return args.replaceAll(replacePrefixRegexGenerator(LONG_PREFIX_NAME), PREFIX_NAME.getPrefix())
                .replaceAll(replacePrefixRegexGenerator(LONG_PREFIX_ADDRESS), PREFIX_ADDRESS.getPrefix())
                .replaceAll(replacePrefixRegexGenerator(LONG_PREFIX_EMAIL), PREFIX_EMAIL.getPrefix())
                .replaceAll(replacePrefixRegexGenerator(LONG_PREFIX_PHONE), PREFIX_PHONE.getPrefix())
                .replaceAll(replacePrefixRegexGenerator(LONG_PREFIX_TAG), PREFIX_TAG.getPrefix());
    }

    /**
     * Given a long prefix, generates a regex pattern that matches the prefix in a string.
     */
    private static String replacePrefixRegexGenerator(Prefix longPrefix) {
        return String.format(TEMPLATE_REGEX_REPLACEMENT_PATTERN, longPrefix.getPrefix());
    }
}
