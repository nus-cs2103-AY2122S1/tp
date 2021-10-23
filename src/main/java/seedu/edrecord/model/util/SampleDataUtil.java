package seedu.edrecord.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleGroupMap;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EdRecord} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        ModuleGroupMap personOneMapping = new ModuleGroupMap();
        personOneMapping.add(new Module("CS2103"), new Group("T03"));
        ModuleGroupMap personTwoMapping = new ModuleGroupMap();
        personTwoMapping.add(new Module("CS2103T"), new Group("T07"));
        ModuleGroupMap personThreeMapping = new ModuleGroupMap();
        personThreeMapping.add(new Module("CS2103"), new Group("T03"));
        ModuleGroupMap personFourMapping = new ModuleGroupMap();
        personFourMapping.add(new Module("CS2103T"), new Group("T07"));
        ModuleGroupMap personFiveMapping = new ModuleGroupMap();
        personFiveMapping.add(new Module("CS2103"), new Group("T03"));
        ModuleGroupMap personSixMapping = new ModuleGroupMap();
        personSixMapping.add(new Module("CS2103T"), new Group("T07"));

        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Info("Hardworking and helpful"), personOneMapping, getTagSet("weak")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Info("Participates actively"), personTwoMapping, getTagSet("strong", "rude")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Info("Needs help with Tutorial 3 Q5"), personThreeMapping, getTagSet("average")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Info("Currently on SHN"), personFourMapping, getTagSet("strong")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Info("Will not be attending tutorial this week"), personFiveMapping, getTagSet("weak", "shy")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Info("Smart and diligent"), personSixMapping, getTagSet("average"))
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group("T03"),
            new Group("T07")
        };
    }

    public static GroupSystem getSampleGroupSystem() {
        GroupSystem sampleGs = new GroupSystem();
        for (Group sampleGroup : getSampleGroups()) {
            sampleGs.addGroup(sampleGroup);
        }
        return sampleGs;
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module("CS2103", getSampleGroupSystem()),
            new Module("CS2103T", getSampleGroupSystem())
        };
    }

    public static ReadOnlyModuleSystem getSampleModuleSystem() {
        ModuleSystem sampleMs = new ModuleSystem();
        for (Module sampleModule : getSampleModules()) {
            sampleMs.addModule(sampleModule);
        }
        return sampleMs;
    }

    public static ReadOnlyEdRecord getSampleEdRecord() {
        EdRecord sampleEr = new EdRecord();
        for (Person samplePerson : getSamplePersons()) {
            sampleEr.addPerson(samplePerson);
        }
        return sampleEr;
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
