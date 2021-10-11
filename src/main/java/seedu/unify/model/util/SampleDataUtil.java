package seedu.unify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;
import seedu.unify.model.tag.Tag;
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
                new Date("2020-10-11"),
                getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Time("10:10"),
                new Date("2020-10-12"),
                getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Time("10:10"),
                new Date("2020-10-13"),
                getTagSet("neighbours")),
            new Task(new Name("David Li"), new Time("10:10"),
                new Date("2020-10-14"),
                getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Time("10:10"),
                new Date("2020-10-15"),
                getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Time("10:10"),
                new Date("2020-10-16"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyUniFy getSampleUniFy() {
        UniFy sampleAb = new UniFy();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
