package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String DATETIME_DISPLAY_FORMAT = "dd LLL yyyy HH:mm";

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE = "Date is invalid.";
    public static final String MESSAGE_INVALID_FREQUENCY = "Frequency can only be daily, weekly, "
            + "biweekly, monthly or quarterly.";
    public static final String MESSAGE_INVALID_OCCURRENCE =
            "Occurrence should be positive integer and less than or equals to 999.";

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
     * Parses a {@code String healthCondition} into a {@code HealthCondition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code healthCondition} is invalid.
     */
    public static HealthCondition parseHealthCondition(String healthCondition) throws ParseException {
        requireNonNull(healthCondition);
        String trimmedHealthCondition = healthCondition.trim();
        if (!HealthCondition.isValidHealthCondition(trimmedHealthCondition)) {
            throw new ParseException(HealthCondition.MESSAGE_CONSTRAINTS);
        }
        return new HealthCondition(trimmedHealthCondition);
    }

    /**
     * Parses {@code Collection<String> healthConditions} into a {@code Set<HealthCondition>}.
     */
    public static Set<HealthCondition> parseHealthConditions(Collection<String> healthConditions)
            throws ParseException {
        requireNonNull(healthConditions);
        final Set<HealthCondition> healthConditionSet = new HashSet<>();
        for (String healthConditionName : healthConditions) {
            healthConditionSet.add(parseHealthCondition(healthConditionName));
        }
        return healthConditionSet;
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

        LocalDateTime parsedLastVisit;
        try {
            parsedLastVisit = LocalDateTime.parse(trimmedLastVisit, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }

        return Optional.ofNullable(new LastVisit(trimmedLastVisit));
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

        LocalDateTime parsedVisit;
        try {
            parsedVisit = LocalDateTime.parse(trimmedVisit, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
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

        LocalDateTime parsedVisit;
        try {
            parsedVisit = LocalDateTime.parse(trimmedVisit, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
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

        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern(DATETIME_DISPLAY_FORMAT);

        try {
            LocalDateTime date = LocalDateTime.parse(storedDateString, DateTimeUtil.FORMATTER);
            displayedDateString = displayFormat.format(date);
        } catch (DateTimeParseException ive) {
            // TODO: handle exception to display
            return storedDateString;
        }
        return displayedDateString;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if the prefix is present and contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean isPrefixPresentAndEmpty(ArgumentMultimap argumentMultimap, Prefix prefix) {
        Optional<String> val = argumentMultimap.getValue(prefix);
        if (val.isPresent() && val.get().isEmpty()) {
            return true;
        }
        return false;
    }
}
