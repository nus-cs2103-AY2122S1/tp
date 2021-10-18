package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String email;
    private final String studentNumber;
    private final String username;
    private final String repo;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final ArrayList<Integer> attendance = new ArrayList<>();
    private Participation participation;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name,
                              @JsonProperty("email") String email,
                              @JsonProperty("studentNumber") String studentNumber,
                              @JsonProperty("username") String username,
                              @JsonProperty("repo") String repo,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("attendance") ArrayList<Integer> attendance) {
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.attendance.addAll(attendance);
        this.repo = repo;
        this.username = username;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        studentNumber = source.getStudentNumber().toString();
        participation = source.getParticipation();
        username = source.getUserName().userName;
        repo = source.getRepoName().repoName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attendance.addAll(source.getAttendance().attendanceList);
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        final ArrayList<Integer> studentAttendance = new ArrayList<>();
        final RepoName modelRepoName;
        final UserName modelUserName;

        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }
        studentAttendance.addAll(attendance);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (studentNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentNumber.class.getSimpleName()));
        }
        if (!StudentNumber.isValidNumber(studentNumber)) {
            throw new IllegalValueException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        final StudentNumber modelStudentNumber = new StudentNumber(studentNumber);

        if (username == null) {
            modelUserName = new UserName();
        } else {
            modelUserName = new UserName(username);
        }

        if (repo == null) {
            modelRepoName = new RepoName();
        } else {
            modelRepoName = new RepoName(repo);
        }

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        final Attendance modelAttendance = new Attendance(studentAttendance);

        return new Student(modelName, modelEmail, modelStudentNumber, modelUserName, modelRepoName,
                modelTags, modelAttendance);
    }
}
