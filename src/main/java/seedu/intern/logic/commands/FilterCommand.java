package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.util.CollectionUtil;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.CombineFiltersPredicate;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.skills.Skill;

/**
 * Filters and lists all applicants in Inter Watcher whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": "
            + "Lists all applicants in InternWatcher with fields matching the arguments provided "
            + "and displays them as a list. \n"
            + "Filters of institution, course and job are case-insensitive and "
            + "filters of status and skill are case-sensitive. \n"
            + "For valid grade filter, display list with applicants with grade not less than the filter. \n"
            + "For valid graduation filter, display list with applicants with graduation strictly before the filter. \n"
            + "For valid skill filters, "
            + "display list with applicants with all skills specified in the filters. \n"
            + "For valid status/institution filters, "
            + "display list with applicants matching at least one of statues/institutions specified in the filters. \n"
            + "For valid course/job filters, display list with applicants containing "
            + "at least one of the courses/jobs specified among the course/job filters. \n"
            + "Parameters: "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_INSTITUTION + "INSTITUTION]... "
            + "[" + PREFIX_COURSE + "COURSE]... "
            + "[" + PREFIX_GRADUATIONYEARMONTH + "GRADUATION_YEAR_MONTH] "
            + "[" + PREFIX_JOB + "APPLIED JOB]... "
            + "[" + PREFIX_STATUS + "APPLICATION_STATUS]... "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example: " + COMMAND_WORD + " g/4.50 i/NUS c/computer science a/APPLIED s/HTML";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided. \n%1$s";

    private final FilterApplicantDescriptor filterApplicantDescriptor;
    private final CombineFiltersPredicate predicate;

    /**
     * @param filterApplicantDescriptor details to filter the applicant with
     */
    public FilterCommand(FilterApplicantDescriptor filterApplicantDescriptor) {
        this.filterApplicantDescriptor = new FilterApplicantDescriptor(filterApplicantDescriptor);
        this.predicate = new CombineFiltersPredicate(this.filterApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && filterApplicantDescriptor.equals(((FilterCommand) other).filterApplicantDescriptor)); // state check
    }

    /**
     * Stores the details to Filter the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class FilterApplicantDescriptor {
        private Grade grade;
        private Set<Institution> institutions;
        private GraduationYearMonth graduationYearMonth;
        private Set<List<String>> courses;
        private Set<List<String>> jobs;
        private Set<ApplicationStatus> statuses;
        private Set<Skill> skills;

        public FilterApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skill} is used internally.
         */
        public FilterApplicantDescriptor(FilterApplicantDescriptor toCopy) {
            setGrade(toCopy.grade);
            setInstitutions(toCopy.institutions);
            setGraduationYearMonth(toCopy.graduationYearMonth);
            setCourses(toCopy.courses);
            setJobs(toCopy.jobs);
            setApplicationStatuses(toCopy.statuses);
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is Filtered.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(grade, institutions, graduationYearMonth,
                    courses, statuses, jobs, skills);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        public void setGraduationYearMonth(GraduationYearMonth graduationYearMonth) {
            this.graduationYearMonth = graduationYearMonth;
        }

        public Optional<GraduationYearMonth> getGraduationYearMonth() {
            return Optional.ofNullable(graduationYearMonth);
        }

        public void setInstitutions(Set<Institution> institutions) {
            this.institutions = (institutions != null) ? new HashSet<>(institutions) : null;
        }

        public Optional<Set<Institution>> getInstitutions() {
            return (institutions != null) ? Optional.of(Collections.unmodifiableSet(institutions)) : Optional.empty();
        }

        public void setJobs(Set<List<String>> jobs) {
            this.jobs = (jobs != null) ? new HashSet<>(jobs) : null;
        }

        public Optional<Set<List<String>>> getJobs() {
            return (jobs != null) ? Optional.of(Collections.unmodifiableSet(jobs)) : Optional.empty();
        }

        public void setCourses(Set<List<String>> courses) {
            this.courses = (courses != null) ? new HashSet<>(courses) : null;
        }

        public Optional<Set<List<String>>> getCourses() {
            return (courses != null) ? Optional.of(Collections.unmodifiableSet(courses)) : Optional.empty();
        }

        public void setApplicationStatuses(Set<ApplicationStatus> statuses) {
            this.statuses = (statuses != null) ? new HashSet<>(statuses) : null;
        }

        public Optional<Set<ApplicationStatus>> getApplicationStatuses() {
            return (statuses != null) ? Optional.of(Collections.unmodifiableSet(statuses)) : Optional.empty();
        }

        /**
         * Sets {@code skill} to this object's {@code skill}.
         * A defensive copy of {@code skill} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skill} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterApplicantDescriptor)) {
                return false;
            }

            // state check
            FilterApplicantDescriptor e = (FilterApplicantDescriptor) other;

            return getGrade().equals(e.getGrade())
                    && getInstitutions().equals(e.getInstitutions())
                    && getGraduationYearMonth().equals(e.getGraduationYearMonth())
                    && getCourses().equals(e.getCourses())
                    && getJobs().equals(e.getJobs())
                    && getApplicationStatuses().equals(e.getApplicationStatuses())
                    && getSkills().equals(e.getSkills());
        }

        @Override
        public String toString() {
            return "FilterApplicantDescriptor{"
                    + ", grade=" + grade
                    + ", institution=" + institutions
                    + ", graduation year month=" + graduationYearMonth
                    + ", course=" + courses
                    + ", applied job=" + jobs
                    + ", application status=" + statuses
                    + ", skill=" + skills + '}';
        }

    }
}
