package seedu.unify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;
import seedu.unify.model.tag.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Priority;
import seedu.unify.model.task.State;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;


/**
 * Contains utility methods for populating {@code Uni-Fy} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Tutorial"), new Time("14:00"),
                new Date("2021-01-04"),
                getTagSet("CS2103T"), new Priority("MEDIUM")),
            new Task(new Name("Tutorial"), new Time("12:00"),
                new Date("2021-01-05"),
                getTagSet("GEQ1000"), new Priority("LOW")),
            new Task(new Name("Assignment"), new Time("23:59"),
                new Date("2021-01-05"), getTagSet("CS2103T"),
                new State("DONE"), new Priority("HIGH")),
            new Task(new Name("Assignment"), new Time("23:59"),
                new Date("2021-01-05"),
                getTagSet("CS2106"), new Priority("MEDIUM")),
            new Task(new Name("Quiz"), new Time("23:59"),
                new Date("2021-01-08"),
                getTagSet("GEQ1000"), new Priority()),
            new Task(new Name("Lab"), new Time("16:00"),
                new Date("2021-01-12"),
                getTagSet("CS2100"), new Priority()),
            new Task(new Name("Gym"), new Time("19:00"),
                new Date("2021-01-15"),
                getTagSet("General"), new Priority()),
            new Task(new Name("Presentation"), new Time("13:00"),
                new Date("2021-01-13"),
                getTagSet("CS1234"), new Priority("MEDIUM")),
            new Task(new Name("Lab Report"), new Time("23:59"),
                new Date("2021-01-13"),
                getTagSet("CS1234"), new Priority("HIGH"))
        };
    }

    /**
     * Returns a sample UniFy.
     */
    public static ReadOnlyUniFy getSampleUniFy() {
        UniFy sampleUniFy = new UniFy();
        for (Task sampleTask : getSampleTasks()) {
            sampleUniFy.addTask(sampleTask);
        }
        return sampleUniFy;
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
