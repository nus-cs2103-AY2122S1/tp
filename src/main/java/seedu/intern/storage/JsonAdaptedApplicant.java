package seedu.intern.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.intern.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Applicant}.
 */
class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String grade;
    private final String institution;
    private final String graduationYearMonth;
    private final String course;
    private final String job;
    private final String status;
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given applicant details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("grade") String grade,
                                @JsonProperty("institution") String institution, @JsonProperty("course") String course,
                                @JsonProperty("graduationYearMonth") String graduationYearMonth,
                                @JsonProperty("job") String job,
                                @JsonProperty("status") String status,
                                @JsonProperty("skills") List<JsonAdaptedSkill> skills) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.institution = institution;
        this.course = course;
        this.graduationYearMonth = graduationYearMonth;
        this.job = job;
        this.status = status;
        if (skills != null) {
            this.skills.addAll(skills);
        }
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        grade = source.getGrade().value;
        institution = source.getInstitution().value;
        course = source.getCourse().value;
        graduationYearMonth = source.getGraduationYearMonth().toString();
        job = source.getJob().jobName;
        status = source.getApplicationStatus().value.toString();
        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Applicant toModelType() throws IllegalValueException {
        final List<Skill> applicantSkills = new ArrayList<>();
        for (JsonAdaptedSkill tag : skills) {
            applicantSkills.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        if (institution == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Institution.class.getSimpleName()));
        }
        if (!Institution.isValidInstitution(institution)) {
            throw new IllegalValueException(Institution.MESSAGE_CONSTRAINTS);
        }
        final Institution modelInstitution = new Institution(institution);

        if (graduationYearMonth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GraduationYearMonth.class.getSimpleName()));
        }
        if (!GraduationYearMonth.isValidGraduationYearMonth(graduationYearMonth)) {
            throw new IllegalValueException(GraduationYearMonth.MESSAGE_CONSTRAINTS);
        }
        final GraduationYearMonth modelYearMonth = new GraduationYearMonth(graduationYearMonth);

        if (course == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Course.class.getSimpleName()));
        }
        if (!Course.isValidCourse(course)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(course);

        if (job == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Job.class.getSimpleName()));
        }
        if (!Job.isValidJobName(job)) {
            throw new IllegalValueException(Job.MESSAGE_CONSTRAINTS);
        }
        final Job modelJob = new Job(job);

        final ApplicationStatus modelStatus;
        if (status == null) {
            modelStatus = new ApplicationStatus();
        } else if (!ApplicationStatus.isValidStatus(status)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        } else {
            modelStatus = new ApplicationStatus(status);
        }

        final Set<Skill> modelSkills = new HashSet<>(applicantSkills);

        return new Applicant(modelName, modelPhone, modelEmail, modelGrade,
                modelInstitution, modelCourse, modelYearMonth , modelJob, modelStatus, modelSkills);

    }

}
