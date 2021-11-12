package seedu.notor.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_NAME = "Amy Toh";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "Why is her last name Bee tho?";
    public static final String DEFAULT_NOTE_DATE = "Thu., 11/02/2022";

    private Name name;
    private Phone phone;
    private Email email;
    private Note note;
    private Set<Tag> tags;
    private HashSet<String> superGroups;
    private HashSet<String> subGroups;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        note = Note.of(DEFAULT_NOTE, DEFAULT_NOTE_DATE);
        tags = new HashSet<>();
        superGroups = new HashSet<>();
        subGroups = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        note = personToCopy.getNote();
        tags = new HashSet<>(personToCopy.getTags());
        superGroups = new HashSet<>(personToCopy.getDisplaySuperGroups());
        subGroups = new HashSet<>(personToCopy.getDisplaySubGroups());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code superGroups} into a {@code Set<String>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSuperGroups(String... superGroups) {
        this.superGroups = new HashSet<>(Arrays.asList(superGroups));
        return this;
    }

    /**
     * Parses the {@code subGroups} into a {@code Set<String>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSubGroups(String... subGroups) {
        this.subGroups = new HashSet<>(Arrays.asList(subGroups));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note, String noteSavedDate) {
        this.note = Note.of(note, noteSavedDate);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building to the note given
     */
    public PersonBuilder withNote(Note note) {
        this.note = note;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, note, tags, superGroups, subGroups);
    }

}
