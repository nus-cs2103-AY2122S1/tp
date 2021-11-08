package nurseybook.logic.parser;

import static java.util.Objects.requireNonNull;
import static nurseybook.commons.util.CollectionUtil.requireAllNonNull;
import static nurseybook.model.task.DateTime.MESSAGE_DATE_CONSTRAINTS;
import static nurseybook.model.task.DateTime.MESSAGE_TIME_CONSTRAINTS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import nurseybook.commons.core.index.Index;
import nurseybook.commons.util.StringUtil;
import nurseybook.logic.parser.exceptions.ParseException;
import nurseybook.model.person.Address;
import nurseybook.model.person.Age;
import nurseybook.model.person.Email;
import nurseybook.model.person.Gender;
import nurseybook.model.person.Name;
import nurseybook.model.person.Phone;
import nurseybook.model.person.Relationship;
import nurseybook.model.person.RoomNumber;
import nurseybook.model.tag.Tag;
import nurseybook.model.task.DateTime;
import nurseybook.model.task.Description;
import nurseybook.model.task.Recurrence;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index must be greater than zero.";
    public static final String MESSAGE_UNKNOWN_INDEX = "Index entered is unknown.";
    public static final String MESSAGE_INDEX_TOO_EXTREME = "Index entered must be less than 10 characters."
            + "NurseyBook only supports up to 999999999 entries.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer) or contains 10
     * characters or more.
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (!trimmedIndex.matches("[0-9,-]+")) {
            throw new ParseException(MESSAGE_UNKNOWN_INDEX);
        }
        // number with 10 digits might overflow
        if (trimmedIndex.length() > 9) {
            throw new ParseException(MESSAGE_INDEX_TOO_EXTREME);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            if (StringUtil.isInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            throw new ParseException(MESSAGE_UNKNOWN_INDEX);
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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseNokName(String nokName) throws ParseException {
        requireNonNull(nokName);
        String trimmedNokName = nokName.trim();
        if (!Name.isValidName(trimmedNokName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedNokName);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Elderly>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            nameSet.add(parseName(name));
        }
        return nameSet;
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
     * Parses a {@code String age} into a {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        String trimmedZeroAge = trimmedAge.replaceFirst("^0+(?!$)", "");
        if (!Age.isValidAge(trimmedZeroAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedZeroAge);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim().toUpperCase();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String roomNumber} into a {@code RoomNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static RoomNumber parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        String trimmedZeroRoomNumber = trimmedRoomNumber.replaceFirst("^0+(?!$)", "");
        if (!RoomNumber.isValidRoomNumber(trimmedZeroRoomNumber)) {
            throw new ParseException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        return new RoomNumber(trimmedZeroRoomNumber);
    }

    /**
     * Parses a {@code String relationship} into a {@code Relationship}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code relationship} is invalid.
     */
    public static Relationship parseRelationship(String relationship) throws ParseException {
        requireNonNull(relationship);
        String trimmedRelationship = relationship.trim();
        if (!Relationship.isValidRelationship(trimmedRelationship)) {
            throw new ParseException(Relationship.MESSAGE_CONSTRAINTS);
        }
        return new Relationship(trimmedRelationship);
    }

    /**
     * Parses a {@code String nursey} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nursey} is invalid.
     */
    public static Address parseAddress(String nursey) throws ParseException {
        requireNonNull(nursey);
        String trimmedAddress = nursey.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            // Should never get called
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
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDesc(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDesc = description.trim();
        if (!Description.isValidDescription(trimmedDesc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDesc);
    }

    /**
     * Parses a {@code String date} and {@code String time} into an {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} and {@code time} is invalid.
     */
    public static DateTime parseDateTime(String date, String time) throws ParseException {
        requireAllNonNull(date, time);
        String trimmedDate = date.trim();
        String trimmedTime = time.trim();
        if (!DateTime.isValidDate(trimmedDate)) {
            throw new ParseException(MESSAGE_DATE_CONSTRAINTS);
        }
        if (!DateTime.isValidTime(trimmedTime)) {
            throw new ParseException(MESSAGE_TIME_CONSTRAINTS);
        }
        return new DateTime(trimmedDate, trimmedTime);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!DateTime.isValidDate(trimmedDate)) {
            throw new ParseException(MESSAGE_DATE_CONSTRAINTS);
        }
        try {
            return LocalDate.parse(trimmedDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_DATE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String time} into an {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!DateTime.isValidTime(trimmedTime)) {
            throw new ParseException(MESSAGE_TIME_CONSTRAINTS);
        }
        try {
            return LocalTime.parse(trimmedTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_TIME_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String recurrenceType} into an {@code Recurrence}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code recurrenceType} is invalid.
     */
    public static Recurrence parseRecurrence(String recurrenceType) throws ParseException {
        requireNonNull(recurrenceType);
        String trimmedRecurrenceType = recurrenceType.trim().toUpperCase();
        if (!Recurrence.isValidRecurrence(trimmedRecurrenceType)) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }
        return new Recurrence(trimmedRecurrenceType);
    }
}
