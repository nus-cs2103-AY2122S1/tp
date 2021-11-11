package seedu.intern.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Job;
import seedu.intern.model.skills.Skill;

/**
 * A utility class to help with building EditApplicantDescriptor objects.
 */

public class FilterApplicantDescriptorBuilder {
    private FilterApplicantDescriptor descriptor;

    public FilterApplicantDescriptorBuilder() {
        descriptor = new FilterApplicantDescriptor();
    }

    public FilterApplicantDescriptorBuilder(FilterApplicantDescriptor descriptor) {
        this.descriptor = new FilterApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicantDescriptor} with fields containing {@code applicant}'s details
     */
    public FilterApplicantDescriptorBuilder(Applicant applicant) {
        List<String> keywords = Arrays.asList(applicant.getCourse().toString().split(" "));
        Set<List<String>> courses = new HashSet<List<String>>();
        Set<ApplicationStatus> statuses = new HashSet<>();
        Set<Institution> institutions = new HashSet<>();
        Set<Job> jobs = new HashSet<>();
        courses.add(keywords);
        statuses.add(applicant.getApplicationStatus());
        institutions.add(applicant.getInstitution());
        jobs.add(applicant.getJob());
        descriptor = new FilterApplicantDescriptor();
        descriptor.setGrade(applicant.getGrade());
        descriptor.setInstitutions(institutions);
        descriptor.setApplicationStatuses(statuses);
        descriptor.setGraduationYearMonth(applicant.getGraduationYearMonth());
        descriptor.setCourses(courses);
        descriptor.setSkills(applicant.getSkills());
    }

    /**
     * Sets the {@code Grade} of the {@code EditApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    /**
     * Sets the {@code Institution} of the {@code EditApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withInstitution(String... institutions) {
        Set<Institution> institutionSet = Stream.of(institutions).map(Institution::new).collect(Collectors.toSet());
        descriptor.setInstitutions(institutionSet);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withApplicationStatus(String... statuses) {
        Set<ApplicationStatus> statusSet = Stream.of(statuses).map(ApplicationStatus::new).collect(Collectors.toSet());
        descriptor.setApplicationStatuses(statusSet);
        return this;
    }

    /**
     * Sets the {@code GraduationYearMonth} of the {@code EditApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withGraduationYearMonth(String graduationYearMonth) {
        descriptor.setGraduationYearMonth(new GraduationYearMonth(graduationYearMonth));
        return this;
    }
    /**
     * Sets the {@code Course} of the {@code EditApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withCourses(String... courses) {
        Set<List<String>> courseSet = Stream.of(courses).map((x) -> Arrays.asList(x.split(" ")))
                .collect(Collectors.toSet());
        descriptor.setCourses(courseSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public FilterApplicantDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    /**
     * Parses the {@code jobs} into a {@code Set<Tag>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public FilterApplicantDescriptorBuilder withJobs(String... jobs) {
        Set<List<String>> jobSet = Stream.of(jobs).map((x) -> Arrays.asList(x.split(" ")))
                .collect(Collectors.toSet());
        descriptor.setJobs(jobSet);
        return this;
    }

    public FilterApplicantDescriptor build() {
        return descriptor;
    }

}
