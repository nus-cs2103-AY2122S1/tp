package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.assessment.Score.INVALID_ACTUAL_SCORE;
import static seedu.address.model.assessment.Score.INVALID_TOTAL_SCORE;
import static seedu.address.model.assessment.Score.SCORE_DELIMITER;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.group.Description;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.TelegramHandle;

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
     * Parses a {@code String telegramHandle} into a {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegramHandle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String telegramHandle) throws ParseException {
        requireNonNull(telegramHandle);
        String trimmedTelegramHandle = telegramHandle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegramHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegramHandle);
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
     * Parses a {@code String groupName} into an {@code GroupName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupName} is invalid.
     */
    public static GroupName parseGroupName(String groupName) throws ParseException {
        requireNonNull(groupName);
        String trimmedGroupName = groupName.trim();
        if (!GroupName.isValidName(trimmedGroupName)) {
            throw new ParseException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(trimmedGroupName);
    }

    /**
     * Parses a {@code String description} into a {@Code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if given description is not valid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Note parseNote(String note) {
        requireNonNull(note);
        String trimmedNote = note.trim();
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String assessmentName} into a {@code AssessmentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assessmentName} is invalid.
     */
    public static AssessmentName parseAssessmentName(String assessmentName) throws ParseException {
        requireNonNull(assessmentName);
        String trimmedAssessmentName = assessmentName.trim();
        if (!AssessmentName.isValidAssessmentName(trimmedAssessmentName)) {
            throw new ParseException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        return new AssessmentName(trimmedAssessmentName);
    }

    /**
     * Parses a {@code String score} into a {@code Score}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code score} is invalid.
     */
    public static Score parseScore(String score) throws ParseException {
        requireNonNull(score);
        String trimmedAssessmentName = score.trim();

        if (!Score.isValidScore(trimmedAssessmentName)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }

        String[] scores = score.split(SCORE_DELIMITER);
        String actualScoreString = scores[0];
        String totalScoreString = scores[1];

        if (!StringUtil.isNonNegativeUnsignedInteger(actualScoreString)) {
            throw new ParseException(INVALID_ACTUAL_SCORE);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(totalScoreString)) {
            throw new ParseException(INVALID_TOTAL_SCORE);
        }

        int actualScore = Integer.parseInt(actualScoreString);
        int totalScore = Integer.parseInt(totalScoreString);

        if (!Score.isValidScore(actualScore, totalScore)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }

        return new Score(actualScore, totalScore);
    }
}
