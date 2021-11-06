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
            new Task(new Name("Quiz 1"), new Time("10:10"),
                new Date("2021-08-18"),
                getTagSet("CS1234"), new State(State.ObjectState.DONE), new Priority()),
            new Task(new Name("Training"), new Time("11:30"),
                new Date("2021-10-28"),
                getTagSet("CCA", "sports"), new Priority()),
            new Task(new Name("Lab 2"), new Time("10:00"),
                new Date("2021-11-03"),
                getTagSet("CS2101"), new Priority(Priority.ObjectPriority.HIGH)),
            new Task(new Name("Presentation 2"), new Time("16:00"),
                new Date("2021-11-10"),
                getTagSet("ES5678"), new Priority()),
            new Task(new Name("Quiz 2"), new Time("23:59"),
                new Date("2021-11-21"),
                getTagSet("MA4321"), new Priority()),
            new Task(new Name("Meeting"), new Time("12:00"),
                new Date("2021-11-22"),
                getTagSet("CS2222"), new Priority())
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
