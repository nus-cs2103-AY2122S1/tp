package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.task.Task;

public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TELE = "@amy_bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ID = "A1234567A";
    public static final List<Task> DEFAULT_TASKS = new ArrayList<>();

    // Identity fields
    private Name name;
    private TeleHandle teleHandle;
    private Email email;

    // Data fields
    private StudentId studentId;

    private List<Task> taskList;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        teleHandle = new TeleHandle(DEFAULT_TELE);
        email = new Email(DEFAULT_EMAIL);
        studentId = new StudentId(DEFAULT_ID);
        this.taskList = DEFAULT_TASKS;
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code teleHandle} of the {@code Student} that we are building.
     */
    public StudentBuilder withTeleHandle(String teleHandle) {
        this.teleHandle = new TeleHandle(teleHandle);
        return this;
    }

    /**
     * Sets the {@code email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code studentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code studentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withTasks(List<Task> taskList) {
        this.taskList = taskList;
        return this;
    }

    /**
     * Build a typical student
     * @return A {@code Student} Object
     */
    public Student build() {
        Student student = new Student(studentId, name, teleHandle, email);
        for (Task task: this.taskList) {
            student.addTask(task);
        }
        return student;
    }


}
