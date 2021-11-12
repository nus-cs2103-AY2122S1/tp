package seedu.notor.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String note;
    private final String noteDate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<String> superGroups = new ArrayList<>();
    private final List<String> subGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("note") String note,
            @JsonProperty("noteDate") String noteDate, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("superGroups") List<String> superGroups, @JsonProperty("subGroups") List<String> subGroups) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.noteDate = noteDate;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.superGroups.addAll(superGroups);
        this.subGroups.addAll(subGroups);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = Objects.requireNonNullElse(source.getPhone().value, "");
        email = Objects.requireNonNullElse(source.getEmail().value, "");
        note = source.getNote().value;
        noteDate = source.getNote().getSavedDate();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        superGroups.addAll(source.getDisplaySuperGroups());
        subGroups.addAll(source.getDisplaySubGroups());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (!phone.equals("")) {
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
        }
        final Phone modelPhone = new Phone(phone);

        if (!email.equals("")) {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
        }
        final Email modelEmail = new Email(email);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (noteDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Note.class.getSimpleName()));
        }
        final Note modelNote = Note.of(note, noteDate);

        if (!superGroups.stream().allMatch(SuperGroup::isValidGroupName)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }

        if (!subGroups.stream().allMatch(Person::isValidDisplaySubGroup)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelNote, modelTags,
                new HashSet<>(superGroups), new HashSet<>(subGroups));
    }

}
