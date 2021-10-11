package seedu.unify.model.util;

import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;
import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;


/**
 * Contains utility methods for populating {@code Uni-Fy} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Alex Yeoh"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important")),
            new Task(new Name("Bernice Yu"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important")),
            new Task(new Name("Charlotte Oliveiro"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important")),
            new Task(new Name("David Li"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important")),
            new Task(new Name("Irfan Ibrahim"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important")),
            new Task(new Name("Roy Balakrishnan"), new Time("10:10"),
                new Date("2020-10-10"),
                new Tag("Important"))
        };
    }

    public static ReadOnlyUniFy getSampleUniFy() {
        UniFy sampleAb = new UniFy();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

}
