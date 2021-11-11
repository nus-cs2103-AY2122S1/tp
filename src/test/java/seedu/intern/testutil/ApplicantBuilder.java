package seedu.intern.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.intern.model.applicant.Applicant;
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
import seedu.intern.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GRADE = "4.50";
    public static final String DEFAULT_INSTITUTION = "NTU";
    public static final String DEFAULT_JOB = "Software Engineer";
    @SuppressWarnings("SpellCheckingInspection")
    public static final String DEFAULT_GRADUATIONYEARMONTH = "06/2024";
    public static final String DEFAULT_COURSE = "Computer Science";

    private Name name;
    private Phone phone;
    private Email email;
    private Grade grade;
    private ApplicationStatus status;
    private Institution institution;
    private GraduationYearMonth graduationYearMonth;
    private Course course;
    private Job job;
    private Set<Skill> skills;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        grade = new Grade(DEFAULT_GRADE);
        institution = new Institution(DEFAULT_INSTITUTION);
        graduationYearMonth = new GraduationYearMonth(DEFAULT_GRADUATIONYEARMONTH);
        status = new ApplicationStatus(ApplicationStatus.DEFAULT_STATUS);
        course = new Course(DEFAULT_COURSE);
        job = new Job(DEFAULT_JOB);
        skills = new HashSet<>();
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code ApplicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        grade = applicantToCopy.getGrade();
        institution = applicantToCopy.getInstitution();
        graduationYearMonth = applicantToCopy.getGraduationYearMonth();
        course = applicantToCopy.getCourse();
        job = applicantToCopy.getJob();
        status = applicantToCopy.getApplicationStatus();
        skills = new HashSet<>(applicantToCopy.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code Institution} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withInstitution(String institution) {
        this.institution = new Institution(institution);
        return this;
    }

    /**
     * Sets the {@code GraduationYearMonth} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withGraduationYearMonth(String graduationYearMonth) {
        this.graduationYearMonth = new GraduationYearMonth(graduationYearMonth);
        return this;
    }

    /**
     * Sets the {@code Job} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withJob(String job) {
        this.job = new Job(job);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withApplicationStatus(String applicationStatus) {
        this.status = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }


    public Applicant build() {
        return new Applicant(name, phone, email, grade, institution, course, graduationYearMonth, job, status, skills);
    }
}
