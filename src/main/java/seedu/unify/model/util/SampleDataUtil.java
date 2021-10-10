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
            new Task(new Name("Alex Yeoh"), new Time("87438807"),
                new Date("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Time("99272758"),
                new Date("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Time("93210283"),
                new Date("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Task(new Name("David Li"), new Time("91031282"),
                new Date("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Time("92492021"),
                new Date("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Time("92624417"),
                new Date("Blk 45 Aljunied Street 85, #11-31"),
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
