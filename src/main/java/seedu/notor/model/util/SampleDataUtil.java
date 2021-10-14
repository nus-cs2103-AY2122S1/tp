package seedu.notor.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Note;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Notor} with sample data.
 */
public class SampleDataUtil {
    public static final Note EMPTY_NOTE = new Note("", "");

    // @formatter:off
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    EMPTY_NOTE, getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    EMPTY_NOTE, getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    EMPTY_NOTE, getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    EMPTY_NOTE, getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    EMPTY_NOTE, getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    EMPTY_NOTE, getTagSet("colleagues"))
        };
    }

    public static ReadOnlyNotor getSampleNotor() {
        Notor sampleAb = new Notor();
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

}
