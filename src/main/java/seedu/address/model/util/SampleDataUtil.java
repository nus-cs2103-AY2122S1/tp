package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.interaction.Interaction;
import seedu.address.model.person.Compatibility;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.remark.Remark;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                    new Faculty("computing"), new Major("computer science"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("friends"),
                    getRemarkSet("remarks1")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                    new Faculty("fass"), new Major("economics"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("colleagues", "friends"),
                    getRemarkSet("remarks1")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                    new Faculty("fass"), new Major("social work"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("neighbours"),
                    getRemarkSet("remarks1")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"),
                    new Faculty("science"), new Major("physics"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("family"),
                    getRemarkSet("remarks1")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"),
                    new Faculty("computing"), new Major("business analytics"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("classmates"),
                    getRemarkSet("remarks1")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                    new Faculty("computing"), new Major("computer science"),
                    new Compatibility(80),
                    getSkillSet("frontend"), getLanguageSet("java"),
                    getFrameworkSet("javafx"), getTagSet("colleagues"),
                    getRemarkSet("remarks1"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a language set containing the list of strings given.
     */
    public static Set<Language> getLanguageSet(String... strings) {
        return Arrays.stream(strings)
                .map(Language::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Interaction> getInteractionSet(String... description) {
        return Arrays.stream(description)
                .map(Interaction::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a framework set containing the list of strings given.
     */
    public static Set<Framework> getFrameworkSet(String... strings) {
        return Arrays.stream(strings)
                .map(Framework::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a remark set containing the list of strings given.
     */
    public static Set<Remark> getRemarkSet(String... strings) {
        return Arrays.stream(strings)
                .map(Remark::new)
                .collect(Collectors.toSet());
    }

}
