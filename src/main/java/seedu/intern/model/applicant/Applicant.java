package seedu.intern.model.applicant;

import static seedu.intern.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.intern.model.skills.Skill;

/**
 * Represents an applicant in Intern Watcher.
 * Guarantees: details are present and not null,
 * field values are validated, immutable.
 */
public class Applicant {

    private static final String[] DEFAULT_ACADEMICS = { "", "", "", "" };

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Grade grade;
    private final Institution institution;
    private final GraduationYearMonth graduationYearMonth;
    private final Course course;
    private final Job job;
    private final ApplicationStatus status;
    private final Set<Skill> skills = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Grade grade,
                     Institution institution, Course course, GraduationYearMonth graduationYearMonth,
                     Job job, Set<Skill> skills) {
        this(name, phone, email, grade, institution, course, graduationYearMonth, job,
                new ApplicationStatus(ApplicationStatus.DEFAULT_STATUS), skills);
    }

    /**
     * Overloaded constructor for creating candidates with default status
     */
    public Applicant(Name name, Phone phone, Email email, Grade grade,
                     Institution institution, Course course, GraduationYearMonth graduationYearMonth,
                     Job job, ApplicationStatus status, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, grade, status, institution, course, graduationYearMonth, job, skills);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.institution = institution;
        this.graduationYearMonth = graduationYearMonth;
        this.job = job;
        this.course = course;
        this.status = status;
        this.skills.addAll(skills);
    }

    public Name getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Course getCourse() {
        return course;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public ApplicationStatus getApplicationStatus() {
        return status;
    }

    public GraduationYearMonth getGraduationYearMonth() {
        return graduationYearMonth;
    }

    public Job getJob() {
        return job;
    }

    public String[] getAcademics() {
        return new String[]{institution.toString(), course.toString(), graduationYearMonth.toString(),
                grade.toString()};
    }

    public static String[] getDefaultAcademics() {
        return DEFAULT_ACADEMICS;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getGrade().equals(getGrade())
                && otherApplicant.getInstitution().equals(getInstitution())
                && otherApplicant.getGraduationYearMonth().equals(getGraduationYearMonth())
                && otherApplicant.getJob().equals(getJob())
                && otherApplicant.getCourse().equals(getCourse())
                && otherApplicant.getApplicationStatus().equals(getApplicationStatus())
                && otherApplicant.getSkills().equals(getSkills());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, grade, institution, course,
                graduationYearMonth, job, status, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Application Status: ")
                .append(getApplicationStatus())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Grade: ")
                .append(getGrade())
                .append("; Institution: ")
                .append(getInstitution())
                .append("; Course: ")
                .append(getCourse())
                .append("; Graduation Year Month: ")
                .append(getGraduationYearMonth())
                .append("; Job: ")
                .append(getJob());

        Set<Skill> skills = getSkills();
        if (!skills.isEmpty()) {
            builder.append("; Skills: ");
            skills.forEach(builder::append);
        }
        return builder.toString();
    }

}
