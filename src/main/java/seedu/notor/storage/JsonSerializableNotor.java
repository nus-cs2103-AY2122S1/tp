package seedu.notor.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * An Immutable Notor that is serializable to JSON format.
 */
@JsonRootName(value = "notor")
class JsonSerializableNotor {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Notor's %s field is missing!";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedPerson> personArchive = new ArrayList<>();

    private final List<JsonAdaptedSuperGroup> superGroups = new ArrayList<>();

    private final String note;

    private final String noteDate;


    /**
     * Constructs a {@code JsonSerializableNotor} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNotor(@JsonProperty("note") String note, @JsonProperty("noteDate") String noteDate,
            @JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("personArchive") List<JsonAdaptedPerson> personArchive,
            @JsonProperty("superGroups") List<JsonAdaptedSuperGroup> superGroups) {
        this.persons.addAll(persons);
        this.personArchive.addAll(personArchive);
        this.superGroups.addAll(superGroups);
        this.note = note;
        this.noteDate = noteDate;
    }

    /**
     * Converts a given {@code ReadOnlyNotor} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNotor}.
     */
    public JsonSerializableNotor(ReadOnlyNotor source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(
                Collectors.toList()));
        personArchive.addAll(source.getPersonArchiveList().stream().map(JsonAdaptedPerson::new).collect(
                Collectors.toList()));
        superGroups
                .addAll(source.getSuperGroups().stream().map(JsonAdaptedSuperGroup::new).collect(
                        Collectors.toList()));
        note = source.getNote().value;
        noteDate = source.getNote().getSavedDate();
    }

    /**
     * Converts this notor into the model's {@code Notor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Notor toModelType() throws IllegalValueException {
        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (noteDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Note.class.getSimpleName()));
        }
        final Note modelNote = Note.of(note, noteDate);

        Notor notor = new Notor(modelNote);

        for (JsonAdaptedSuperGroup jsonAdaptedSuperGroup : superGroups) {
            SuperGroup superGroup = jsonAdaptedSuperGroup.toModelType();
            if (!notor.hasSuperGroup(superGroup)) {
                notor.addSuperGroup(superGroup);
            }
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (notor.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (String superGroup : person.getDisplaySuperGroups()) {
                notor.findSuperGroup(superGroup).addPerson(person);
            }
            for (String subGroup : person.getDisplaySubGroups()) {
                String[] split = subGroup.split("_");
                notor.findSuperGroup(split[0]).addPersonToSubGroup(split[1], person);
            }

            notor.addPerson(person);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : personArchive) {
            Person person = jsonAdaptedPerson.toModelType();
            if (notor.hasArchive(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }

            notor.addArchivePerson(person);
        }
        return notor;
    }
}
