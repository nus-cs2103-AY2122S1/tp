package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessoncode.LessonCode;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TeleHandle;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_MISSING_TIME = "Missing compulsory start time or end time.";
    public static final String MESSAGE_INVALID_LESSON_DURATION =
            "The start time should be before end time.";
    public static final String MESSAGE_INVALID_MODULE_INFO =
            "Missing either the module code or the lesson code. Both are compulsory.";

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
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String processedModuleCode = moduleCode.trim().toUpperCase();

        String[] moduleCodeArr = processedModuleCode.split("\\s+");
        assert moduleCodeArr.length >= 1 : "Array should not be empty\n";
        if (!ModuleCode.isValidModuleCode(moduleCodeArr[0])) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        for (int i = 1; i < moduleCodeArr.length; i++) {
            if (!LessonCode.isValidLessonCode(moduleCodeArr[i])) {
                throw new ParseException(LessonCode.MESSAGE_CONSTRAINTS);
            }
        }
        Set<LessonCode> lessonCodes = Arrays.stream(moduleCodeArr)
                .skip(1)
                .map(LessonCode::new)
                .collect(Collectors.toSet());
        return new ModuleCode(moduleCodeArr[0], lessonCodes);
    }

    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCodeForModuleLesson(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String processedModuleCode = moduleCode.trim().toUpperCase();
        String[] moduleCodeArr = processedModuleCode.split("\\s+");
        if (moduleCodeArr.length < 2) {
            throw new ParseException(MESSAGE_INVALID_MODULE_INFO);
        }
        if (!ModuleCode.isValidModuleCode(moduleCodeArr[0])) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        if (!LessonCode.isValidLessonCode(moduleCodeArr[1])) {
            throw new ParseException(LessonCode.MESSAGE_CONSTRAINTS);
        }
        Set<LessonCode> lessonCodeSet = Set.of(new LessonCode(moduleCodeArr[1]));
        return new ModuleCode(moduleCodeArr[0], lessonCodeSet);
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<ModuleCode>}.
     */
    public static Set<ModuleCode> parseModuleCodes(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);
        final Set<ModuleCode> moduleCodeSet = new HashSet<>();
        for (String moduleCodeName: moduleCodes) {
            moduleCodeSet.add(parseModuleCode(moduleCodeName));
        }
        return moduleCodeSet;
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
     * Parses a {@code String teleHandle} into a {@code TeleHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code teleHandle} is invalid.
     */
    public static TeleHandle parseTeleHandle(String teleHandle) throws ParseException {
        requireNonNull(teleHandle);
        String trimmedTeleHandle = teleHandle.trim();
        if (!TeleHandle.isValidTeleHandle(trimmedTeleHandle)) {
            throw new ParseException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        return new TeleHandle(trimmedTeleHandle);
    }

    /**
     * Parses a {@code String lessonCode} into a {@code LessonCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lessonCode} is invalid.
     */
    public static LessonCode parseLessonCode(String lessonCode) throws ParseException {
        requireNonNull(lessonCode);
        String trimmedTag = lessonCode.trim();
        if (!LessonCode.isValidLessonCode(trimmedTag)) {
            throw new ParseException(LessonCode.MESSAGE_CONSTRAINTS);
        }
        return new LessonCode(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> lessonCodes} into a {@code Set<LessonCode>}.
     */
    public static Set<LessonCode> parseLessonCodes(Collection<String> lessonCodes) throws ParseException {
        requireNonNull(lessonCodes);
        final Set<LessonCode> lessonCodeSet = new HashSet<>();
        for (String tagName : lessonCodes) {
            lessonCodeSet.add(parseLessonCode(tagName));
        }
        return lessonCodeSet;
    }

    /**
     * Parses a {@code String remark} into a {@code remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String lessonDay} into a {@code LessonDay}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lessonDay} is invalid.
     */
    public static LessonDay parseLessonDay(String lessonDay) throws ParseException {
        requireNonNull(lessonDay);
        String trimmedLessonDay = lessonDay.trim();
        if (!LessonDay.isValidDay(trimmedLessonDay)) {
            throw new ParseException(LessonDay.MESSAGE_CONSTRAINTS);
        }
        return new LessonDay(trimmedLessonDay);
    }

    /**
     * Parses a {@code String lessonTime} into a {@code List<LessonTime>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {code lessonTime} is invalid.
     */
    public static List<LessonTime> parseLessonTime(String lessonTime) throws ParseException {
        requireNonNull(lessonTime);
        String trimmedLessonTime = lessonTime.trim();
        String[] lessonTimeArr = trimmedLessonTime.split("\\s+");
        if (lessonTimeArr.length != 2) {
            throw new ParseException(MESSAGE_MISSING_TIME);
        }
        if (!LessonTime.isValidTime(lessonTimeArr[0]) || !LessonTime.isValidTime(lessonTimeArr[1])) {
            throw new ParseException(LessonTime.MESSAGE_CONSTRAINTS);
        }
        if (!LocalTime.parse(lessonTimeArr[0]).isBefore(LocalTime.parse(lessonTimeArr[1]))) {
            throw new ParseException(MESSAGE_INVALID_LESSON_DURATION);
        }
        return Arrays.stream(lessonTimeArr).map(LessonTime::new).collect(Collectors.toUnmodifiableList());
    }
}
