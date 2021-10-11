package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.commons.util.StringUtil;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Attendance;
import seedu.academydirectory.model.student.Email;
import seedu.academydirectory.model.student.Grade;
import seedu.academydirectory.model.student.Name;
import seedu.academydirectory.model.student.Participation;
import seedu.academydirectory.model.student.Phone;
import seedu.academydirectory.model.student.StudioRecord;
import seedu.academydirectory.model.student.Telegram;
import seedu.academydirectory.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ATTENDANCE_STATUS = "Attendance status is not 0 or 1.";
    public static final String MESSAGE_INVALID_PARTICIPATION_STATUS =
            "Change in Participation must be between -100 and 100.";

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
     * Parses {@code attendance} into an {@code boolean} and returns it.
     * @throws ParseException if the specified input is invalid (not one or zero).
     */
    public static boolean parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (Attendance.isValidAttendance(trimmedAttendance)) {
            return trimmedAttendance.equals("1");
        } else {
            throw new ParseException(MESSAGE_INVALID_ATTENDANCE_STATUS);
        }
    }

    /**
     * Parses a {@code String participation} into a {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Integer} is invalid.
     */
    public static Integer parseParticipation(String participation) throws ParseException {
        requireNonNull(participation);
        String trimmedParticipation = participation.trim();
        if (Participation.isValidParticipation(trimmedParticipation)) {
            return Integer.parseInt(trimmedParticipation);
        } else {
            throw new ParseException(MESSAGE_INVALID_PARTICIPATION_STATUS);
        }
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
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
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
     * Parses a {@code String assessment} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String} is invalid.
     */
    public static String parseAssessment(String assessment) throws ParseException {
        requireNonNull(assessment);
        String trimmedAssessment = assessment.trim();
        if (!Assessment.isValidAssessment(trimmedAssessment)) {
            throw new ParseException(Assessment.MESSAGE_CONSTRAINTS);
        }
        return trimmedAssessment;
    }

    /**
     * Parses a {@code String grade} into a {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Integer} is invalid.
     */
    public static Integer parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return Integer.parseInt(trimmedGrade);
    }

    /**
     * Parses a {@code String studioRecord} into a {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studioRecord} is invalid.
     */
    public static Integer parseStudioRecord(String studioRecord) throws ParseException {
        requireNonNull(studioRecord);
        String trimmedStudioRecord = studioRecord.trim();
        if (!StudioRecord.isValidStudioRecord(trimmedStudioRecord)) {
            throw new ParseException(StudioRecord.MESSAGE_CONSTRAINTS);
        }
        return Integer.parseInt(trimmedStudioRecord);
    }

}
