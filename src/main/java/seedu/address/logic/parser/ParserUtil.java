package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
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
    public static TeleHandle parseTeleHandle(String teleHandle) throws ParseException {
        requireNonNull(teleHandle);
        String trimmedPhone = teleHandle.trim();
        if (!TeleHandle.isValidTeleHandle(trimmedPhone)) {
            throw new ParseException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        return new TeleHandle(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedAddress = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedAddress)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedAddress);
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
     * Parses a {@code String moduleName} into a {@code ModuleName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedName = moduleName.trim();
        if (!ModuleName.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedName);
    }

    /**
     * Parses a {@code String taskId} into a {@code TaskId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static TaskId parseTaskId(String taskId) throws ParseException {
        requireNonNull(taskId);
        String trimmedId = taskId.trim();
        if (!TaskId.isValidTaskId(trimmedId)) {
            throw new ParseException(TaskId.MESSAGE_CONSTRAINTS);
        }
        return new TaskId(trimmedId);
    }

    /**
     * Parses a {@code String taskName} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static TaskName parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedTaskName = taskName.trim();
        if (!TaskName.isValidTaskName(trimmedTaskName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedTaskName);
    }

    /**
     * Parses a {@code String taskName} into a {@code TaskDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static TaskDeadline parseTaskDeadline(String taskDeadline) throws ParseException {
        requireNonNull(taskDeadline);
        String trimmedTaskDeadline = taskDeadline.trim();
        if (!TaskDeadline.isValidTaskDeadline(trimmedTaskDeadline)) {
            throw new ParseException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        return new TaskDeadline(trimmedTaskDeadline);
    }
}
