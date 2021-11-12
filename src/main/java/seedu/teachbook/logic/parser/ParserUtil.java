package seedu.teachbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.commons.core.index.IndexComparator;
import seedu.teachbook.commons.util.StringUtil;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various {@code Parser} classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex one-based index to be parsed.
     * @return parsed index.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        BigInteger value = new BigInteger(trimmedIndex);
        if (value.compareTo(BigInteger.ZERO) <= 0 || value.compareTo(new BigInteger("2147483647")) > 0) {
            trimmedIndex = "2147483647";
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndicesSpaceSeparated} into a list of {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @param oneBasedIndicesSpaceSeparated one-based indices to be parsed.
     * @return parsed list of indices.
     * @throws ParseException if at least one specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndices(String oneBasedIndicesSpaceSeparated) throws ParseException {
        String trimmedIndices = oneBasedIndicesSpaceSeparated.trim();
        String[] indexArray = trimmedIndices.split("\\s+"); // "" will become String[1] { "" } after split

        // Remove duplicate indices
        Set<String> indexSet = new HashSet<>(Arrays.asList(indexArray));
        String[] rawIndices = indexSet.toArray(new String[0]);

        List<Index> oneBasedIndices = new ArrayList<>();
        for (String rawIndex : rawIndices) {
            oneBasedIndices.add(parseIndex(rawIndex));
        }

        // Sort indices
        oneBasedIndices.sort(new IndexComparator());

        return oneBasedIndices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name name string to be parsed.
     * @return parsed name.
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
     * Parses a {@code String className} into a {@code ClassName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param className class name string to be parsed.
     * @return parsed class name.
     * @throws ParseException if the given {@code className} is invalid.
     */
    public static ClassName parseClassName(String className) throws ParseException {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        if (!ClassName.isValidClassName(trimmedClassName)) {
            throw new ParseException(ClassName.MESSAGE_CONSTRAINTS);
        }
        return new ClassName(trimmedClassName);
    }

    /**
     * Parses a {@code String className} into a {@code ClassNameDescriptor}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param className class name descriptor string to be parsed.
     * @return parsed class name descriptor.
     */
    public static ClassNameDescriptor parseClassNameForLocatingClass(String className) {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        return new ClassNameDescriptor(trimmedClassName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is valid.
     *
     * @param phone phone string to be parsed.
     * @return parsed phone.
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
     * Parses a {@code Optional<String> phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is invalid.
     *
     * @param phone phone string to be parsed.
     * @return parsed phone.
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhoneForAdd(Optional<String> phone) throws ParseException {
        requireNonNull(phone);
        if (phone.isPresent() && phone.get().equals("")) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        String trimmedPhone = "";
        if (phone.isPresent()) {
            trimmedPhone = phone.get().trim();
        }
        return parsePhone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is valid.
     *
     * @param address address string to be parsed.
     * @return parsed address.
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
     * Parses a {@code Optional<String> address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is invalid.
     *
     * @param address address string to be parsed or null.
     * @return parsed address.
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddressForAdd(Optional<String> address) throws ParseException {
        requireNonNull(address);
        if (address.isPresent() && address.get().equals("")) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        String trimmedAddress = "";
        if (address.isPresent()) {
            trimmedAddress = address.get().trim();
        }
        return parseAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is valid.
     *
     * @param email email string to be parsed.
     * @return parsed email.
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
     * Parses a {@code Optional<String> email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     * An empty string is invalid.
     *
     * @param email email string to be parsed or null.
     * @return parsed email.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmailForAdd(Optional<String> email) throws ParseException {
        requireNonNull(email);
        if (email.isPresent() && email.get().equals("")) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        String trimmedEmail = "";
        if (email.isPresent()) {
            trimmedEmail = email.get().trim();
        }
        return parseEmail(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tag tag string to be parsed.
     * @return parsed tag.
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
     *
     * @param tags tags string to be parsed.
     * @return parsed tags.
     * @throws ParseException if the given {@code tags} is invalid.
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
     * Parses a {@code String grade} into a {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param grade grade string to be parsed.
     * @return parsed grade.
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    /**
     * Parses {@code grades} into a list of {@code Grade} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param grades grades string to be parsed.
     * @return parsed list of grades.
     * @throws ParseException if at least one specified grade is invalid.
     */
    public static List<Grade> parseGrades(String grades) throws ParseException {
        requireNonNull(grades);

        if (grades.endsWith(">")) { // prohibit grades ending with '>'
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }

        final List<Grade> gradeList = new ArrayList<>();
        String[] stringGradeList = grades.split(">");
        for (String grade : stringGradeList) {
            gradeList.add(parseGrade(grade));
        }

        Set<Grade> gradeSet = new HashSet<>(gradeList);
        if (gradeSet.size() < gradeList.size()) { // gradeList contains repeated grades
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }

        if (gradeList.stream().anyMatch(grade -> grade.equals(NOT_GRADED))) { // gradeList contains ""
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }

        return gradeList;
    }

    /**
     * Checks if the preamble equals "all".
     *
     * @param preamble preamble.
     * @return {@code true} if preamble equals "all".
     */
    public static boolean parseAll(String preamble) {
        return preamble.trim().equals("all");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
