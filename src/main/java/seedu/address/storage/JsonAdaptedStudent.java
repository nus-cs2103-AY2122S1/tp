package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;


/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String STUDENT_MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String teleHandle;
    private final String email;
    private final String studentId;
    private final List<JsonAdaptedTask> studentTaskList = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("teleHandle") String teleHandle,
                              @JsonProperty("email") String email, @JsonProperty("studentId") String studentId,
                              @JsonProperty("studentTaskList")List<JsonAdaptedTask> studentTaskList) {
        this.name = name;
        this.teleHandle = teleHandle;
        this.email = email;
        this.studentId = studentId;
        this.studentTaskList.addAll(studentTaskList);
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        teleHandle = source.getTeleHandle().value;
        email = source.getEmail().value;
        studentId = source.getStudentId().value;
        List<Task> tempTaskList = source.getTaskList().asModifiableObservableList();
        for (Task task : tempTaskList) {
            JsonAdaptedTask taskToAdd = new JsonAdaptedTask(task);
            studentTaskList.add(taskToAdd);
        }
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (teleHandle == null) {
            throw new IllegalValueException(String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT,
                    TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(teleHandle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelTeleHandle = new TeleHandle(teleHandle);

        if (email == null) {
            throw new IllegalValueException(String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (studentId == null) {
            throw new IllegalValueException(String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        final UniqueTaskList tasks = new UniqueTaskList();
        if (!studentTaskList.isEmpty()) {
            for (JsonAdaptedTask task : studentTaskList) {
                Task modelTask = task.toModelType();
                tasks.add(modelTask);
            }
        }
        return new Student(modelStudentId, modelName, modelTeleHandle, modelEmail, tasks);
    }

}
