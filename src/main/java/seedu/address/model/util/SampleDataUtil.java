package seedu.address.model.util;

import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;

/**
 * Contains utility methods for populating {@code TeachingAssistantBuddy} with sample data.
 */
public class SampleDataUtil {

    static final String SAMPLE_MODULE_ONE = "CS2103";
    static final String SAMPLE_MODULE_TWO = "CS2100";

    public static Task[] getSampleTasksForModule(String moduleName) {
        return new Task[] {new Task(new ModuleName(moduleName), new TaskId("T1"),
                new TaskName("Assignment 1"), new TaskDeadline("2021-11-11"))
        };
    }

    public static Student[] getSampleStudents() {
        return new Student[]{new Student(new StudentId("A1234567A"), new Name("Alex Yeoh"), new TeleHandle("@Alexyeoh"),
                new Email("alexyeoh@u.nus.edu")), new Student(new StudentId("A7654321A"),
                new Name("Bernice Yu"),
                new TeleHandle("@Berny"), new Email("berniceyu@u.nus.edu")),
        };
    }

    public static Module[] getSampleModules() {

        Module sampleModuleOne = new Module(new ModuleName(SAMPLE_MODULE_ONE));

        for (Task sampleTask: getSampleTasksForModule(SAMPLE_MODULE_ONE)) {
            sampleModuleOne.addTask(sampleTask);
        }
        for (Student sampleStudent: getSampleStudents()) {
            for (Task task: getSampleTasksForModule(SAMPLE_MODULE_ONE)) {
                sampleStudent.addTask(task);
            }
            sampleModuleOne.addStudent(sampleStudent);
        }

        Module sampleModuleTwo = new Module(new ModuleName(SAMPLE_MODULE_TWO));

        for (Task sampleTask: getSampleTasksForModule(SAMPLE_MODULE_TWO)) {
            sampleModuleTwo.addTask(sampleTask);
        }
        for (Student sampleStudent: getSampleStudents()) {
            for (Task task: getSampleTasksForModule(SAMPLE_MODULE_TWO)) {
                sampleStudent.addTask(task);
            }
            sampleModuleTwo.addStudent(sampleStudent);
        }

        return new Module[] {
            sampleModuleOne,
            sampleModuleTwo,
        };
    }

    public static ReadOnlyTeachingAssistantBuddy getSampleTeachingAssistantBuddy() {
        TeachingAssistantBuddy sampleAb = new TeachingAssistantBuddy();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }
}
