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
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * An Immutable Notor that is serializable to JSON format.
 */
@JsonRootName(value = "notor")
class JsonSerializableNotor {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedSuperGroup> superGroups = new ArrayList<>();

    private final List<JsonAdaptedSubGroup> subGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNotor} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNotor(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
        @JsonProperty("superGroups") List<JsonAdaptedSuperGroup> superGroups,
        @JsonProperty("subGroups") List<JsonAdaptedSubGroup> subGroups) {
        this.persons.addAll(persons);
        this.superGroups.addAll(superGroups);
        this.subGroups.addAll(subGroups);
    }

    /**
     * Converts a given {@code ReadOnlyNotor} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNotor}.
     */
    public JsonSerializableNotor(ReadOnlyNotor source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(
            Collectors.toList()));
        superGroups
            .addAll(source.getSuperGroups().values().stream().map(JsonAdaptedSuperGroup::new).collect(
            Collectors.toList()));
        subGroups
            .addAll(source.getSubGroups().values().stream().map(JsonAdaptedSubGroup::new).collect(
                Collectors.toList()));
    }

    /**
     * Converts this notor into the model's {@code Notor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Notor toModelType() throws IllegalValueException {
        Notor notor = new Notor();
        for (JsonAdaptedSuperGroup jsonAdaptedSuperGroup : superGroups) {
            SuperGroup superGroup = jsonAdaptedSuperGroup.toModelType();
            if (!notor.hasSuperGroup(superGroup)) {
                notor.addSuperGroup(superGroup);
            }
        }
        for (JsonAdaptedSubGroup jsonAdaptedSubGroups : subGroups) {
            SubGroup subGroup = jsonAdaptedSubGroups.toModelType();
            if (!notor.hasSubGroup(subGroup)) {
                notor.addSubGroup(subGroup);
            }
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (notor.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (String superGroup : person.getSuperGroups()) {
                notor.findSuperGroup(superGroup).addPerson(person);
            }
            for (String subGroup : person.getSubGroups()) {
                notor.findSubGroup(subGroup).addPerson(person);
            }

            notor.addPerson(person);
        }
        return notor;
    }
}
