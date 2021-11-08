package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public static final String MESSAGE_EMPTY_CANDIDATE_INDEXES = "You must enter at least one candidate index";
    public static final String MESSAGE_EMPTY_INTERVIEW_INDEXES = "You must enter an interview index";
    public static final String MESSAGE_INVALID_INDEX =
            "Index should be a non-zero unsigned integer, and less than the maximum value (2147483647).\n";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.equals("")) {
            throw new ParseException("");
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a string of keywords as List delimited by space.
     *
     * @param keywords Input String.
     * @return List of keywords.
     */
    public static List<String> parseKeywords(String keywords) {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();

        List<String> output = new ArrayList<String>(Arrays.asList(trimmedKeywords.split("\\s+")));

        output.removeAll(Arrays.asList(""));

        return output;
    }

    /**
     * Parses a string of time keyword delimited by spaces.
     *
     * @param keywords Input String.
     * @return List of LocalTimes.
     * @throws ParseException Input string is not valid time format HHMM.
     */
    public static List<LocalTime> parseTimeKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();

        List<String> timeStrings = new ArrayList<String>(Arrays.asList(trimmedKeywords.split("\\s+")));

        timeStrings.removeAll(Arrays.asList(""));

        List<LocalTime> output = new ArrayList<>();

        String timeFormat = "^[0-9]{4}$";


        for (String time : timeStrings) {
            Pattern p = Pattern.compile(timeFormat);
            Matcher m = p.matcher(time);
            if (m.find()) {
                int hour = Integer.parseInt(time.substring(0, 2));
                int min = Integer.parseInt(time.substring(2));
                try {
                    output.add(LocalTime.of(hour, min));
                } catch (DateTimeException e) {
                    throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
                }
            } else {
                throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
            }

        }

        return output;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name Input String.
     * @return Name of a candidate.
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
     * @param tag Input String.
     * @return Tag of a candidate.
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
     * @param phone Input String.
     * @return Phone Number of a candidate.
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
     * @param address Input String.
     * @return Address of a candidate.
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
     * @param email Input String.
     * @return Email of a candidate.
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
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param status Input String.
     * @return Status of a candidate.
     * @throws ParseException If the given {@code status} is invalid.
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
     *
     * @param tags Input Strings.
     * @return Tags of a candidate.
     * @throws ParseException If the given {@code tags} is invalid.
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
     * @param title Input String.
     * @return Title of a Job Position.
     * @throws ParseException If the given {@code title} is invalid.
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
     * Parses a {@code String position} into a {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param position Input String.
     * @return A Job Position.
     * @throws ParseException If the given {@code position} is invalid.
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
     *
     * @param positions Input Strings.
     * @return A Set of Job Positions.
     * @throws ParseException If the given {@code positions} is invalid.
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
     *
     * @param positionStatus Input String.
     * @return Status of a Job Position.
     * @throws ParseException If the given {@code positionStatus} is invalid
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
     *
     * @param date Input String.
     * @return LocalDate of an Interview.
     * @throws ParseException If the given {@code date} is invalid
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
     *
     * @param time Input String.
     * @return LocalTime of an Interview.
     * @throws ParseException If the given {@code time} is invalid
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
     *
     * @param duration Input String.
     * @return Duration of an Interview.
     * @throws ParseException If the given {@code duration} is invalid
     */
    public static Duration parseDuration(String duration) throws ParseException {
        try {
            long actualDuration = Long.parseLong(duration);
            //capped at strictly less than 24 hours or 1440 minutes
            if (actualDuration <= 0 || actualDuration >= 1440) {
                throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER);
            }
            return Duration.ofMinutes(actualDuration);
        } catch (NumberFormatException e) {
            throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS_NOT_A_NUMBER);
        }
    }

    /**
     * Parses a {@code String status} into a {@code InterviewStatus}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param status Input String.
     * @return InterviewStatus of an Interview.
     * @throws ParseException If the given {@code status} is invalid
     */
    public static InterviewStatus parseInterviewStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim().toUpperCase();
        if (!InterviewStatus.isValidInterviewStatus(trimmedStatus)) {
            throw new ParseException(InterviewStatus.MESSAGE_CONSTRAINTS);
        }

        // Assumes trimmedStatus is valid. trimmedStatus can only be "pending" or "completed".
        if (trimmedStatus.equals("PENDING")) {
            return InterviewStatus.PENDING;
        } else {
            return InterviewStatus.COMPLETED;
        }
    }

    /**
     * Parses {@code Collection<String> indexes} into a {@code Set<Index>}.
     *
     * @param indexes Input Strings.
     * @return A Set of Indexes.
     * @throws ParseException If the given {@code indexes} is invalid
     */
    public static Set<Index> parseIndexes(Collection<String> indexes) throws ParseException {
        requireNonNull(indexes);
        final Set<Index> indexSet = new HashSet<>();
        for (String index : indexes) {
            indexSet.add(parseIndex(index));
        }
        return indexSet;
    }

    /**
     * Parses {@code String indexes} into a {@code Set<Index>}.
     *
     * @param indexes Input Strings.
     * @return A Set of Indexes for Candidates.
     * @throws ParseException If the given {@code indexes} is invalid
     */
    public static Set<Index> parseCandidateIndexes(String indexes) throws ParseException {
        requireNonNull(indexes);
        String trimmedKeywords = indexes.trim();
        Set<Index> characterIndexes = new HashSet<>();

        List<String> temp = new ArrayList<>(Arrays.asList(trimmedKeywords.split("\\s+")));

        temp.removeAll(Arrays.asList(""));

        List<String> tempWithoutDuplicates = temp.stream()
                .distinct()
                .collect(Collectors.toList());

        for (String index : tempWithoutDuplicates) {
            characterIndexes.add(parseIndex(index));
        }

        if (characterIndexes.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_CANDIDATE_INDEXES);
        }

        return characterIndexes;
    }
}
