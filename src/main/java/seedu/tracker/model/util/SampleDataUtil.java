package seedu.tracker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ModuleTracker} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Code("CS2030S"), new Title("OOP"),
                    new Description("learning about OOP"), new Mc(4),
                    getTagSet("core")),
            new Module(new Code("CS2040S"), new Title("algorithm"),
                    new Description("learning basic algorithm"), new Mc(4),
                    getTagSet("core")),
            new Module(new Code("CS2105"), new Title("network"),
                    new Description("learning about computer network"), new Mc(4),
                    getTagSet("UE")),
            new Module(new Code("CS3243"), new Title("Introduction to AI"),
                    new Description("learn basic AI"), new Mc(4),
                    getTagSet("UE")),
            new Module(new Code("GER1000"), new Title("quantitative reasoning"),
                    new Description("learn quantitative reasoning"), new Mc(4),
                    getTagSet("GE")),
            new Module(new Code("CS2103T"), new Title("software engineering"),
                    new Description("learning about software engineering"), new Mc(4),
                    getTagSet("core"))
        };
    }

    public static ReadOnlyModuleTracker getSampleModuleTracker() {
        ModuleTracker sampleMt = new ModuleTracker();
        for (Module sampleModule : getSampleModules()) {
            sampleMt.addModule(sampleModule);
        }
        return sampleMt;
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
