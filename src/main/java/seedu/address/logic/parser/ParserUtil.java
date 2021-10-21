package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;

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
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static int parseWeek(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!Attendance.isValidWeek(Integer.parseInt(oneBasedIndex))) {
            throw new ParseException(String.format(Attendance.MESSAGE_CONSTRAINTS, Attendance.NUMBER_OF_WEEKS));
        }
        return Integer.parseInt(trimmedIndex) - 1;
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
    public static Description parseDescription(String description) throws ParseException {
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String name} into a {@code GroupName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gorupName} is invalid.
     */
    public static GroupName parseGroupName(String groupName) throws ParseException {
        requireNonNull(groupName);
        String trimmedName = groupName.trim();
        if (!GroupName.isValidName(trimmedName)) {
            throw new ParseException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(trimmedName);
    }

    /**
     * Parses a {@code String year} into an {@code LinkYear}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static LinkYear parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!LinkYear.isValidYear(trimmedYear)) {
            throw new ParseException(LinkYear.MESSAGE_CONSTRAINTS);
        }
        return new LinkYear(trimmedYear);
    }

    /**
     * Parses a {@code String repoName} into an {@code RepoName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code repoName} is invalid.
     */
    public static RepoName parseRepo(String repoName) throws ParseException {
        requireNonNull(repoName);
        String trimmedRepoName = repoName.trim();
        if (!RepoName.isValidRepoName(trimmedRepoName)) {
            throw new ParseException(RepoName.MESSAGE_CONSTRAINTS);
        }
        return new RepoName(trimmedRepoName);
    }

    /**
     * Parses a {@code String userName} into an {@code UserName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code userName} is invalid.
     */
    public static UserName parseUserName(String userName) throws ParseException {
        requireNonNull(userName);
        String trimmedUserName = userName.trim();
        if (!UserName.isValidUserName(trimmedUserName)) {
            throw new ParseException(UserName.MESSAGE_CONSTRAINTS);
        }
        return new UserName(trimmedUserName);
    }


    /**
     * Parses a {@code String name} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TaskName parseTaskName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TaskName.isValidName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedName);
    }


    /**
     * Parses a {@code String taskDate} into a {@code TaskDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDate} is invalid.
     */
    public static TaskDate parseTaskDate(String taskDate) throws ParseException {
        requireNonNull(taskDate);
        String trimmedTaskDate = taskDate.trim();
        if (!TaskDate.isValidDeadline(trimmedTaskDate)) {
            throw new ParseException(TaskDate.MESSAGE_CONSTRAINTS + " HUH:" + trimmedTaskDate);
        }
        return new TaskDate(trimmedTaskDate);
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
     * Parses a {@code String studentNumber} into an {@code StudentNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static StudentNumber parseStudentNumber(String studentNumber) throws ParseException {
        requireNonNull(studentNumber);
        String trimmedStudentNumber = studentNumber.trim();
        if (!StudentNumber.isValidNumber(trimmedStudentNumber)) {
            throw new ParseException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        return new StudentNumber(trimmedStudentNumber);
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
}
