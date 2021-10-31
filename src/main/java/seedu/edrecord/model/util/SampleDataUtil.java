package seedu.edrecord.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.AssignmentGradeMap;
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
        ModuleSet personOneSet = new ModuleSet();
        personOneSet.add(new Module("CS2103"), new Group("T03"));
        ModuleSet personTwoSet = new ModuleSet();
        personTwoSet.add(new Module("CS2103T"), new Group("T07"));
        ModuleSet personThreeSet = new ModuleSet();
        personThreeSet.add(new Module("CS2103"), new Group("T03"));
        ModuleSet personFourSet = new ModuleSet();
        personFourSet.add(new Module("CS2103T"), new Group("T07"));
        ModuleSet personFiveSet = new ModuleSet();
        personFiveSet.add(new Module("CS2103"), new Group("T03"));
        ModuleSet personSixSet = new ModuleSet();
        personSixSet.add(new Module("CS2103T"), new Group("T07"));

        return new Person[] {
            // TODO change AssignmentGradeMap() to sample grades
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Info("Hardworking and helpful"), personOneSet, getTagSet("weak"),
                    new AssignmentGradeMap()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Info("Participates actively"), personTwoSet, getTagSet("strong", "rude"),
                    new AssignmentGradeMap()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Info("Needs help with Tutorial 3 Q5"), personThreeSet, getTagSet("average"),
                    new AssignmentGradeMap()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Info("Currently on SHN"), personFourSet, getTagSet("strong"), new AssignmentGradeMap()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Info("Will not be attending tutorial this week"), personFiveSet, getTagSet("weak", "shy"),
                    new AssignmentGradeMap()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Info("Smart and diligent"), personSixSet, getTagSet("average"), new AssignmentGradeMap())
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
