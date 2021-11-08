package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.DescriptionType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.FormClass;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String involvement} into an {@code Involvement}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Involvement} is invalid.
     */
    public static Involvement parseInvolvement(String involvement) throws ParseException {
        requireNonNull(involvement);
        String trimmedInvolvement = involvement.trim();
        if (!Involvement.isValidInvolvement(trimmedInvolvement)) {
            throw new ParseException(Involvement.MESSAGE_CONSTRAINTS);
        }
        return new Involvement(trimmedInvolvement);
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
     * Parses a {@code String formClass} into an {@code FormClass}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code FormClass} is invalid.
     */
    public static FormClass parseFormClass(String formClass) throws ParseException {
        requireNonNull(formClass);
        String trimmedFormClass = formClass.trim();
        if (!FormClass.isValidFormClass(trimmedFormClass)) {
            throw new ParseException(FormClass.MESSAGE_CONSTRAINTS);
        }
        return new FormClass(trimmedFormClass);
    }

    /**
     * Parses a {@code String Gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String genderUpperCase = gender.toUpperCase();
        String trimmedGender = genderUpperCase.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String tableNumber} into a {@code OfficeTable}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tableNumber} is invalid.
     */
    public static OfficeTable parseOfficeTable(String tableNumber) throws ParseException {
        requireNonNull(tableNumber);
        String trimmedTableNumber = tableNumber.trim();
        if (!OfficeTable.isValidTable(trimmedTableNumber)) {
            throw new ParseException(OfficeTable.MESSAGE_CONSTRAINTS);
        }
        return new OfficeTable(tableNumber);
    }

    /**
     * Parses a {@code String title} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Description parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Description.isValidDescription(trimmedTitle)) {
            throw new ParseException(Description.getMessageConstraints(DescriptionType.TITLE));
        }
        return new Description(title);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(dateTime);
    }

    /**
     * Parses a {@code String venue} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Description parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!Description.isValidDescription(trimmedVenue)) {
            throw new ParseException(Description.getMessageConstraints(DescriptionType.VENUE));
        }
        return new Description(venue);
    }

    /**
     * Parses a {@code String attendee} into a {@code seedu.address.model.meeting.Attendee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendee} is invalid.
     */
    public static Attendee parseAttendee(String attendee) throws ParseException {
        requireNonNull(attendee);
        String trimmedAttendee = attendee.trim();
        if (!Attendee.isValidAttendee(trimmedAttendee)) {
            throw new ParseException(Attendee.MESSAGE_CONSTRAINTS);
        }
        return new Attendee(attendee);
    }
}
