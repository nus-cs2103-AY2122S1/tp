package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public final class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DAYS = "Days is not a non-negative unsigned integer.";

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
     * Parses {@code zeroBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseDays(String zeroBasedIndex) throws ParseException {
        String trimmedIndex = zeroBasedIndex.trim();
        if (!StringUtil.isNonNegativeInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_DAYS);
        }
        return Index.fromZeroBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompanyName = company.trim();
        if (!Company.isValidCompanyName(trimmedCompanyName)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompanyName);
    }

    /**
     * Parses a {@code String position} into an {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code position} is invalid.
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Position.isValidPosition(trimmedPosition)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
        return new Position(trimmedPosition);
    }

    /**
     * Parses a {@code String deadline} into an {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        } //only checks if the input string is in YYYY-MM-DD format
        if (!Deadline.isValidDate(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_INVALIDDATE);
        } //given that the string is in YYYY-MM-DD format, make sure the day-month combination is actually valid
        //only if both checks passes can the deadline be considered as valid
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String completion} into an {@code Completion}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code completion} is invalid.
     */
    public static Completion parseCompletion(String completion) throws ParseException {
        requireNonNull(completion);
        String trimmedCompletion = completion.trim();
        if (!Completion.isValidCompletion(trimmedCompletion)) {
            throw new ParseException(Completion.MESSAGE_CONSTRAINTS);
        }
        return new Completion(trimmedCompletion);
    }

    /**
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String priority} into an {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Requirement parseRequirement(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedRequirement = tag.trim();
        if (!Requirement.isValidRequirement(trimmedRequirement)) {
            throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(trimmedRequirement);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Requirement>}.
     */
    public static Set<Requirement> parseRequirements(Collection<String> requirements) throws ParseException {
        requireNonNull(requirements);
        final Set<Requirement> requirementSet = new HashSet<>();
        for (String requirement : requirements) {
            requirementSet.add(parseRequirement(requirement));
        }
        return requirementSet;
    }

    /**
     * Parses a {@code String dt} into a {@code InterviewDateAndTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dt} is invalid.
     */
    public static InterviewDateAndTime parseInterviewDateAndTime(String dt) throws ParseException {
        requireNonNull(dt);
        String trimmedInterviewDateAndTime = dt.trim();
        if (!InterviewDateAndTime.isValidInterviewDateAndTime(trimmedInterviewDateAndTime)) {
            throw new ParseException(InterviewDateAndTime.MESSAGE_CONSTRAINTS);
        } //only checks if the input string is in YYYY-MM-DD HHmm format
        if (!InterviewDateAndTime.isValidDateAndTime(trimmedInterviewDateAndTime)) {
            throw new ParseException(InterviewDateAndTime.MESSAGE_INVALIDDATEANDTIME);
        } //given that the string is in YYYY-MM-DD HHmm format, make sure the combination is actually valid
        //only if both checks passes can the dateAndTime be considered as valid
        return new InterviewDateAndTime(trimmedInterviewDateAndTime);
    }

    /**
     * Parses {@code Collection<InterviewDateAndTime> interviewDateAndTimes} into a {@code Set<InterviewDateAndTime>}.
     */
    public static Set<InterviewDateAndTime>
        parseInterviewDateAndTimes(Collection<String> interviewDateAndTimes) throws ParseException {
        requireNonNull(interviewDateAndTimes);
        final Set<InterviewDateAndTime> dtSet = new HashSet<>();
        for (String dtName : interviewDateAndTimes) {
            dtSet.add(parseInterviewDateAndTime(dtName));
        }
        return dtSet;
    }

}
