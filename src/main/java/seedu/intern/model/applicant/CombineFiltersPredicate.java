package seedu.intern.model.applicant;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.intern.commons.util.StringUtil;
import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.model.skills.Skill;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches any of the keywords given.
 */
public class CombineFiltersPredicate implements Predicate<Applicant> {
    private final FilterApplicantDescriptor filterApplicantDescriptor;
    private boolean result;

    /**
     * Constructor for a CombineFilterPredicate
     * @param filterApplicantDescriptor a FilterApplicantDescriptor that has details of the filters
     */
    public CombineFiltersPredicate(FilterCommand.FilterApplicantDescriptor filterApplicantDescriptor) {
        this.filterApplicantDescriptor = filterApplicantDescriptor;
        result = true;
    }

    @Override
    public boolean test(Applicant applicant) {
        result = true;
        Optional<Grade> grade = filterApplicantDescriptor.getGrade();
        Optional<Set<Institution>> institutions = filterApplicantDescriptor.getInstitutions();
        Optional<GraduationYearMonth> graduationYearMonth = filterApplicantDescriptor.getGraduationYearMonth();
        Optional<Set<List<String>>> courses = filterApplicantDescriptor.getCourses();
        Optional<Set<List<String>>> jobs = filterApplicantDescriptor.getJobs();
        Optional<Set<ApplicationStatus>> statuses = filterApplicantDescriptor.getApplicationStatuses();
        Optional<Set<Skill>> skills = filterApplicantDescriptor.getSkills();

        grade.ifPresent(gradeContent ->
                setResult(result && Float.compare(Float.parseFloat(applicant.getGrade().value),
                                Float.parseFloat(gradeContent.value)) >= 0));
        institutions.ifPresent(institutionsContent ->
                setResult(result && institutionsContent.stream().anyMatch(institution ->
                        StringUtil.containsWordIgnoreCase(applicant.getInstitution().value, institution.value))));
        graduationYearMonth.ifPresent(graduationContent ->
                setResult(result && applicant.getGraduationYearMonth().isBefore(graduationContent)));
        courses.ifPresent(coursesContent ->
                setResult(result && coursesContent.stream().anyMatch(courseFilter ->
                        courseFilter.stream().allMatch(courseKeyword ->
                                StringUtil.containsWordIgnoreCase(applicant.getCourse().value, courseKeyword)))));
        jobs.ifPresent(jobsContent ->
                setResult(result && jobsContent.stream().anyMatch(jobFilter ->
                        jobFilter.stream().allMatch(jobKeyword ->
                                StringUtil.containsWordIgnoreCase(applicant.getJob().jobName, jobKeyword)))));
        statuses.ifPresent(statusesContent ->
                setResult(result && statusesContent.stream().anyMatch(status ->
                        StringUtil.containsWordIgnoreCase(applicant.getApplicationStatus().value.name(),
                                status.value.name()))));
        skills.ifPresent(skillsContent ->
                setResult(result && applicant.getSkills().containsAll(skillsContent)));

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CombineFiltersPredicate // instanceof handles nulls
                && filterApplicantDescriptor.equals(((CombineFiltersPredicate) other)
                .filterApplicantDescriptor)); // state check
    }

    private void setResult(boolean newResult) {
        result = newResult;
    }

}
