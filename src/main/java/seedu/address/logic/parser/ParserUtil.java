package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;
import seedu.address.model.tag.Tag;

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
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
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
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
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
     * Parses a {@code String status} into a {@code Status}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code status} is invalid
     */
    public static Status parseStatus(String status) throws ParseException {
        String trimmedStatus = status.trim().toUpperCase();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }

        return Status.parseStatus(trimmedStatus);
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
     * Parses {@code String title} into a {@code Title}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(title);
    }

    /**
     * Parses a {@code String Position} into a {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Title.isValidTitle(trimmedPosition)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }

        Title title = new Title(trimmedPosition);
        return new Position(title);
    }


    /**
     * Parses {@code Collection<String> position} into a {@code Set<Position>}.
     */
    public static Set<Position> parsePositions(Collection<String> positions) throws ParseException {
        requireNonNull(positions);
        final Set<Position> positionSet = new HashSet<>();
        for (String positionName : positions) {
            positionSet.add(parsePosition(positionName));
        }
        return positionSet;
    }

    /**
     * Parses {@code String positionStatus} into a {@code PositionStatus}.
     */
    public static PositionStatus parsePositionStatus(String positionStatus) throws ParseException {
        requireNonNull(positionStatus);
        String trimmedStatus = positionStatus.trim();
        if (!PositionStatus.isValidPositionStatus(trimmedStatus)) {
            throw new ParseException(PositionStatus.MESSAGE_CONSTRAINTS);
        }

        // Assumes trimmedStatus is valid. trimmedStatus can only be "open" or "closed".
        if (trimmedStatus.equals("open")) {
            return PositionStatus.OPEN;
        } else {
            return PositionStatus.CLOSED;
        }
    }

    /**
     * Parses {@code String date} into a {@code LocalDate}.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        String dateFormat = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}$";
        Pattern p = Pattern.compile(dateFormat);
        Matcher m = p.matcher(date);
        if (m.find()) {
            String[] foundDate = m.group().split("/");
            int year = Integer.parseInt(foundDate[2]);
            int month = Integer.parseInt(foundDate[1]);
            int day = Integer.parseInt(foundDate[0]);
            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                throw new ParseException(Interview.MESSAGE_DATE_CONSTRAINTS);
            }
        }
        throw new ParseException(Interview.MESSAGE_DATE_CONSTRAINTS);
    }

    /**
     * Parses {@code String time} into a {@code LocalTime}.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        String timeFormat = "^[0-9]{4}$";
        Pattern p = Pattern.compile(timeFormat);
        Matcher m = p.matcher(time);
        if (m.find()) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2));
            try {
                return LocalTime.of(hour, min);
            } catch (DateTimeException e) {
                throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
            }
        }
        throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
    }

    /**
     * Parses {@code String duration} into a {@code Duration}.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        try {
            Long actualDuration = Long.parseLong(duration);
            if (actualDuration < 0) {
                throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS);
            }
            return Duration.ofMinutes(actualDuration);
        } catch (NumberFormatException e) {
            throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String status} into a {@code InterviewStatus}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code status} is invalid
     */
    public static InterviewStatus parseInterviewStatus(String status) throws ParseException {
        String trimmedStatus = status.trim().toUpperCase();
        if (!InterviewStatus.isValidInterviewStatus(trimmedStatus)) {
            throw new ParseException(InterviewStatus.MESSAGE_CONSTRAINTS);
        }
        return InterviewStatus.parseStatus(status);
    }
}
