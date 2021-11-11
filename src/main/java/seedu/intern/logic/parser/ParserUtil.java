package seedu.intern.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CliSyntax.FLAG_TOGGLE;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.intern.commons.core.selection.Index;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.commons.util.StringUtil;
import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Job;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_SELECTION = "Selection is not a non-zero unsigned integer or ALL.";
    public static final String MESSAGE_INVALID_TAG = "Selection provided does not end with TOGGLE";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified selection is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code selection} into a {@code Selection} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified selection is invalid.
     */
    public static Selection parseSelection(String selection) throws ParseException {
        String trimmedSelection = selection.trim();
        if (StringUtil.isInteger(trimmedSelection) && !StringUtil.isNonZeroUnsignedInteger(trimmedSelection)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedSelection)
                && !StringUtil.isAll(trimmedSelection)) {
            throw new ParseException(MESSAGE_INVALID_SELECTION);
        }

        if (StringUtil.isAll(trimmedSelection)) {
            return Selection.fromAllFlag();
        } else {
            return Selection.fromIndex(Index.fromOneBased(Integer.parseInt(trimmedSelection)));
        }
    }

    //@@author nicolej2122
    /**
     * Parses {@code selection} into a {@code Pair} with a
     * {@code Index} and {@code isExtraCondition} flag and returns it.
     * Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified selection is invalid.
     */
    public static Pair<Index, Boolean> parseView(String selection) throws ParseException {
        String [] trimmedSelection = selection.trim().split(" ");
        if (trimmedSelection.length > 2) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (!StringUtil.isInteger(trimmedSelection[0])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (StringUtil.isInteger(trimmedSelection[0]) && !StringUtil.isNonZeroUnsignedInteger(trimmedSelection[0])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        if (trimmedSelection.length == 2 && !StringUtil.isToggle(trimmedSelection[1])) {
            throw new ParseException(MESSAGE_INVALID_TAG);
        }

        if (trimmedSelection.length == 2) {
            return new Pair<>(Index.fromOneBased(Integer.parseInt(trimmedSelection[0])),
                    trimmedSelection[1].equals(FLAG_TOGGLE));
        } else {
            return new Pair<>(Index.fromOneBased(Integer.parseInt(trimmedSelection[0])),
                    false);
        }
    }

    //@@author
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
     * Parses a {@code String grade} into an {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
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
     * Parses a {@code String institution} into an {@code Institution}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code institution} is invalid.
     */
    public static Institution parseInstitution(String institution) throws ParseException {
        requireNonNull(institution);
        String trimmedInstitution = institution.trim();
        if (!Institution.isValidInstitution(trimmedInstitution)) {
            throw new ParseException(Institution.MESSAGE_CONSTRAINTS);
        }
        return new Institution(trimmedInstitution);
    }

    /**
     * Parses {@code Collection<String> institutions} into a {@code Set<Institution>}.
     */
    public static Set<Institution> parseInstitutions(Collection<String> institutions) throws ParseException {
        requireNonNull(institutions);
        final Set<Institution> institutionSet = new HashSet<>();
        for (String institution : institutions) {
            institutionSet.add(parseInstitution(institution));
        }
        return institutionSet;
    }

    /**
     * Parses a {@code String graduationYearMonth} into an {@code GraduationYearMonth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code graduationYearMonth} is invalid.
     */
    public static GraduationYearMonth parseGraduationYearMonth(String graduationYearMonth) throws ParseException {
        requireNonNull(graduationYearMonth);
        String trimmedYearMonth = graduationYearMonth.trim();
        if (!GraduationYearMonth.isValidGraduationYearMonth(trimmedYearMonth)) {
            throw new ParseException(GraduationYearMonth.MESSAGE_CONSTRAINTS);
        }
        return new GraduationYearMonth(trimmedYearMonth);
    }

    /**
     * Parses a {@code String job} into an {@code Job}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code job} is invalid.
     */
    public static Job parseJob(String job) throws ParseException {
        requireNonNull(job);
        String trimmedJobName = job.trim();
        if (!Job.isValidJobName(job)) {
            throw new ParseException(Job.MESSAGE_CONSTRAINTS);
        }
        return new Job(trimmedJobName);
    }

    /**
     * Parses {@code Collection<String> jobs} into a {@code Set<Job>}.
     */
    public static Set<Job> parseJobs(Collection<String> jobs) throws ParseException {
        requireNonNull(jobs);
        final Set<Job> jobSet = new HashSet<>();
        for (String job : jobs) {
            jobSet.add(parseJob(job));
        }
        return jobSet;
    }

    /**
     * Parses a {@code String jobFilter} into a {@code List<String>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code job} is invalid.
     */
    public static List<String> parseJobFilter(String jobFilter) throws ParseException {
        requireNonNull(jobFilter);
        String trimmedJob = jobFilter.trim();
        if (!Job.isValidJobName(trimmedJob)) {
            throw new ParseException(Job.MESSAGE_CONSTRAINTS);
        }
        if (trimmedJob.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String[] jobKeywords = trimmedJob.split("\\s+");

        return Arrays.asList(jobKeywords);
    }

    /**
     * Parses {@code Collection<String> jobFilters} into a {@code Set<List<String>>}.
     */
    public static Set<List<String>> parseJobFilters(Collection<String> jobs) throws ParseException {
        requireNonNull(jobs);
        final Set<List<String>> jobSet = new HashSet<>();
        for (String job : jobs) {
            jobSet.add(parseJobFilter(job));
        }
        return jobSet;
    }

    /**
     * Parses a {@code String course} into a {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static Course parseCourse(String course) throws ParseException {
        requireNonNull(course);
        String trimmedCourse = course.trim();
        if (!Course.isValidCourse(trimmedCourse)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(trimmedCourse);
    }

    /**
     * Parses a {@code String courseFilter} into a {@code List<String>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static List<String> parseCourseFilter(String courseFilter) throws ParseException {
        requireNonNull(courseFilter);
        String trimmedCourse = courseFilter.trim();
        if (!Course.isValidCourse(trimmedCourse)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        if (trimmedCourse.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String[] courseKeywords = trimmedCourse.split("\\s+");

        return Arrays.asList(courseKeywords);
    }

    /**
     * Parses {@code Collection<String> courseFilters} into a {@code Set<List<String>>}.
     */
    public static Set<List<String>> parseCourseFilters(Collection<String> courses) throws ParseException {
        requireNonNull(courses);
        final Set<List<String>> courseSet = new HashSet<>();
        for (String course : courses) {
            courseSet.add(parseCourseFilter(course));
        }
        return courseSet;
    }

    /**
     * Parses a {@code String status} into an {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static ApplicationStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!ApplicationStatus.isValidStatus(trimmedStatus)) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(trimmedStatus);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<ApplicationStatus>}.
     */
    public static Set<ApplicationStatus> parseApplicationStatuses(Collection<String> applicationStatuses)
            throws ParseException {
        requireNonNull(applicationStatuses);
        final Set<ApplicationStatus> applicationStatusSet = new HashSet<>();
        for (String applicationStatus : applicationStatuses) {
            applicationStatusSet.add(parseStatus(applicationStatus));
        }
        return applicationStatusSet;
    }

    /**
     * Parses a {@code String skill} into a {@code skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        if (!Skill.isValidSkillName(trimmedSkill)) {
            throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(trimmedSkill);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<skill>}.
     */
    public static Set<Skill> parseSkills(Collection<String> skills) throws ParseException {
        requireNonNull(skills);
        final Set<Skill> skillSet = new HashSet<>();
        for (String skillName : skills) {
            skillSet.add(parseSkill(skillName));
        }
        return skillSet;
    }
}
