package seedu.plannermd.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.person.Person;

/**
 * An Immutable PlannerMd that is serializable to JSON format.
 */
@JsonRootName(value = "plannermd")
class JsonSerializablePlannerMd {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlannerMd} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlannerMd(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPlannerMd} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlannerMd}.
     */
    public JsonSerializablePlannerMd(ReadOnlyPlannerMd source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this plannermd into the model's {@code PlannerMd} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PlannerMd toModelType() throws IllegalValueException {
        PlannerMd plannerMd = new PlannerMd();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (plannerMd.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            plannerMd.addPerson(person);
        }
        return plannerMd;
    }

}
