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


    /**
     * Constructs a {@code JsonSerializableNotor} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNotor(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
        @JsonProperty("superGroups") List<JsonAdaptedSuperGroup> superGroups) {
        this.persons.addAll(persons);
        this.superGroups.addAll(superGroups);
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
            .addAll(source.getSuperGroups().stream().map(JsonAdaptedSuperGroup::new).collect(
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
        System.out.println(notor.getSuperGroups());

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (notor.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (String superGroup : person.getSuperGroups()) {
                System.out.println(notor.findSuperGroup(superGroup));
                notor.findSuperGroup(superGroup).addPerson(person);
            }
            for (String subGroup : person.getSubGroups()) {
                String[] split = subGroup.split("_");
                // TODO: Create method to check for validity
                notor.findSuperGroup(split[0]).addPersonToSubGroup(split[1], person);
            }

            notor.addPerson(person);
        }
        return notor;
    }
}
