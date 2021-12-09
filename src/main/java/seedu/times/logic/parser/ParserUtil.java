package seedu.times.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.times.model.tag.Tag.MAX_TAG_LENGTH;
import static seedu.times.model.tag.Tag.MAX_TAG_NUMBER;
import static seedu.times.model.tag.Tag.MESSAGE_CONSTRAINTS_TOO_LONG;
import static seedu.times.model.tag.Tag.MESSAGE_CONSTRAINTS_TOO_MANY;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.commons.util.StringUtil;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Phone;
import seedu.times.model.tag.Tag;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String FIND_REGEX_WITH_COMMA_DELIMITER = ",\\s*";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String classTiming} into an {@code ClassTiming}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code classTiming} is invalid.
     */
    public static ClassTiming parseClassTiming(String classTiming) throws ParseException {
        requireNonNull(classTiming);
        String trimmedClassTiming = classTiming.trim();
        if (!ClassTiming.isValidClassTiming(trimmedClassTiming)) {
            throw new ParseException(ClassTiming.MESSAGE_CONSTRAINTS);
        }
        return new ClassTiming(trimmedClassTiming);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedAddress = location.trim();
        if (!Location.isValidLocation(trimmedAddress)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedAddress);
    }

    /**
     * Parses a {@code String nok} into an {@code Nok}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nok} is invalid.
     */
    public static Address parseNok(String nok) throws ParseException {
        requireNonNull(nok);
        String trimmedAddress = nok.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
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
     * Parses a {@code String rate} into a {@code Rate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rate} is invalid.
     */
    public static Rate parseRate(String rate) throws ParseException {
        requireNonNull(rate);
        String trimmedRate = rate.trim();
        if (!Rate.isValidRate(trimmedRate)) {
            throw new ParseException(Rate.MESSAGE_CONSTRAINTS);
        }
        return new Rate(trimmedRate);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        checkTagsAreValid(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Checks whether the tags are within the constraints.
     *
     * @param tags The tags to check.
     * @throws ParseException if the given tags are invalid.
     */
    public static void checkTagsAreValid(Collection<String> tags) throws ParseException {
        if (tags.size() > MAX_TAG_NUMBER) {
            throw new ParseException(MESSAGE_CONSTRAINTS_TOO_MANY);
        }
        for (String tagName : tags) {
            if (tagName.length() > MAX_TAG_LENGTH) {
                throw new ParseException(MESSAGE_CONSTRAINTS_TOO_LONG);
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses a {@code String className} into a {@code ClassName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code className} is invalid.
     */
    public static ClassName parseClassName(String className) throws ParseException {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        if (!ClassName.isValidClassName(trimmedClassName)) {
            throw new ParseException(ClassName.MESSAGE_CONSTRAINTS);
        }
        return new ClassName(trimmedClassName);
    }

    /**
     * Returns a list of search terms to be used for filtering student or class list.
     *
     * @throws ParseException if given {@code trimmedArgs} does not have any search term
     */
    public static List<String> getSearchTermList(String trimmedArgs, String regex) throws ParseException {
        String[] searchTerms = trimmedArgs.split(regex);
        if (searchTerms.length == 0) {
            throw new ParseException(Messages.MESSAGE_NO_SEARCH_TERMS_ENTERED);
        }
        return Arrays.asList(searchTerms);
    }
}
