package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Nationality("Chinese"), new TutorialGroup("T09"), new Gender("M"),
                    new Remark("likes to code"), getTagSet("friends"),
                    getSocialHandleSet("tg:alexy")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Nationality("Indonesian"), new TutorialGroup("T10"), new Gender("F"),
                    new Remark("likes to dance"), getTagSet("colleagues", "friends"),
                    getSocialHandleSet("tg:bernicey")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Nationality("Malaysian"), new TutorialGroup("W28"), new Gender("F"),
                    new Remark("likes to eat"), getTagSet("neighbours"),
                    getSocialHandleSet("tg:charlotteo")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Nationality("Singaporean"), new TutorialGroup("T02"), new Gender("M"),
                    new Remark("likes to cook"), getTagSet("family"),
                    getSocialHandleSet("tg:davidl")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Nationality("Singaporean"), new TutorialGroup("W01"), new Gender("M"),
                    new Remark("likes to bake"), getTagSet("classmates"),
                    getSocialHandleSet("tg:irfani")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Nationality("South Korean"), new TutorialGroup("T07"), new Gender("M"),
                    new Remark("likes to run"), getTagSet("colleagues"),
                    getSocialHandleSet("tg:royb"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a social handle set containing the list of strings given.
     */
    public static Set<SocialHandle> getSocialHandleSet(String... strings) {
        return Arrays.stream(strings)
                .map(SocialHandle::new)
                .collect(Collectors.toSet());
    }

}
