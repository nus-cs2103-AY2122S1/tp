package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String DATETIME_PARSE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_DISPLAY_FORMAT = "dd LLL yyyy HH:mm";

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE = "Date is invalid.";
    public static final String MESSAGE_INVALID_LAST_VISIT_DATE = "Last visit date should be in the past.";
    public static final String MESSAGE_INVALID_VISIT_DATE = "Visit date should be in the future.";
    public static final String MESSAGE_INVALID_FREQUENCY = "Frequency can only be daily, weekly, "
            + "biweekly, monthly or quarterly.";
    public static final String MESSAGE_INVALID_OCCURRENCE = "Occurrence should be a positive number.";

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
     * Parses a {@code String language} into an {@code Language}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code language} is invalid.
     */
    public static Language parseLanguage(String language) throws ParseException {
        requireNonNull(language);
        String trimmedLanguage = language.trim();
        if (!Language.isValidLanguage(trimmedLanguage)) {
            throw new ParseException(Language.MESSAGE_CONSTRAINTS);
        }
        return new Language(trimmedLanguage);
    }

    /**
     * Parses a {@code String lastVisit} into an {@code LastVisit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lastVisit} is invalid.
     */
    public static Optional<LastVisit> parseLastVisit(String lastVisit) throws ParseException {
        requireNonNull(lastVisit);
        String trimmedLastVisit = lastVisit.trim();
        if (lastVisit.isEmpty()) {
            return Optional.ofNullable(new LastVisit(trimmedLastVisit));
        }

        if (!LastVisit.isValidLastVisit(trimmedLastVisit)) {
            throw new ParseException(LastVisit.MESSAGE_CONSTRAINTS);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_FORMAT);
        LocalDateTime parsedLastVisit;
        try {
            parsedLastVisit = LocalDateTime.parse(trimmedLastVisit, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }

        if (DateTimeUtil.isFuture(parsedLastVisit)) {
            throw new ParseException(MESSAGE_INVALID_LAST_VISIT_DATE);
        }

        return Optional.ofNullable(new LastVisit(trimmedLastVisit));
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
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String Visit} into an {@code Visit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Visit} is invalid.
     */
    public static Optional<Visit> parseVisit(String visit) throws ParseException {
        requireNonNull(visit);
        String trimmedVisit = visit.trim();

        if (!Visit.isValidVisit(trimmedVisit)) {
            throw new ParseException(Visit.MESSAGE_CONSTRAINTS);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_FORMAT);
        LocalDateTime parsedVisit;
        try {
            parsedVisit = LocalDateTime.parse(trimmedVisit, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }

        if (DateTimeUtil.isPast(parsedVisit)) {
            throw new ParseException(MESSAGE_INVALID_VISIT_DATE);
        }

        return Optional.ofNullable(new Visit(trimmedVisit));
    }

    /**
     * Parses a {@code String Visit} into an {@code Visit} for the add command.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Visit} is invalid.
     */
    public static Optional<Visit> parseVisitForAdd(String visit) throws ParseException {
        requireNonNull(visit);
        String trimmedVisit = visit.trim();

        if (trimmedVisit.isEmpty()) {
            return Optional.ofNullable(new Visit(""));
        }

        if (!Visit.isValidVisit(trimmedVisit)) {
            throw new ParseException(Visit.MESSAGE_CONSTRAINTS);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_FORMAT);
        LocalDateTime parsedVisit;
        try {
            parsedVisit = LocalDateTime.parse(trimmedVisit, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }

        if (DateTimeUtil.isPast(parsedVisit)) {
            throw new ParseException(MESSAGE_INVALID_VISIT_DATE);
        }

        return Optional.ofNullable(new Visit(trimmedVisit));
    }

    /**
     * Parses {@code frequency} into a {@code frequency} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified frequency is invalid.
     */
    public static Optional<Frequency> parseFrequency(String frequency) throws ParseException {
        String trimmedFrequency = frequency.trim();
        if (!Frequency.isValidFrequency(trimmedFrequency)) {
            throw new ParseException(MESSAGE_INVALID_FREQUENCY);
        }
        return Optional.ofNullable(Frequency.find(trimmedFrequency));
    }


    /**
     * Parses {@code occurrence} into a {@code occurrence} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified occurrence is invalid.
     */
    public static Optional<Occurrence> parseOccurrence(String occurrence) throws ParseException {
        requireNonNull(occurrence);
        String trimmedOccurrence = occurrence.trim();

        if (!Occurrence.isValidOccurrence(trimmedOccurrence)) {
            throw new ParseException(MESSAGE_INVALID_OCCURRENCE);
        }

        int convertedOccurrence = Integer.parseInt(trimmedOccurrence);
        return Optional.ofNullable(new Occurrence(convertedOccurrence));
    }

    /**
     * Format {@code storedDateString} into a {@code displayedDateString} to be displayed on Person Card.
     *
     * @param storedDateString datetime string of pattern dd yyyy-MM-dd HH:mm
     * @return displayedDateString formatted datetime string of pattern dd LLL yyyy HH:mm
     */
    public static String parseDisplayedDatetime(String storedDateString) {
        String displayedDateString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_FORMAT);
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern(DATETIME_DISPLAY_FORMAT);

        try {
            LocalDateTime date = LocalDateTime.parse(storedDateString, formatter);
            displayedDateString = displayFormat.format(date);
        } catch (DateTimeParseException ive) {
            // TODO: handle exception to display
            return storedDateString;
        }
        return displayedDateString;
    }

}
