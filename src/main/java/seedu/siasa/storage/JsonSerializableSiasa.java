package seedu.siasa.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableSiasa {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_POLICY = "Policy list contains duplicate policies(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPolicy> policies = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSiasa} with the given persons and policies.
     */
    @JsonCreator
    public JsonSerializableSiasa(@JsonProperty("persons") List<JsonAdaptedPerson> persons, @JsonProperty("policies") List<JsonAdaptedPolicy> policies) {
        this.persons.addAll(persons);
        this.policies.addAll(policies);
    }

    /**
     * Converts a given {@code ReadOnlySiasa} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableSiasa(ReadOnlySiasa source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        policies.addAll(source.getPolicyList().stream().map(JsonAdaptedPolicy::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Siasa toModelType() throws IllegalValueException {
        Siasa siasa = new Siasa();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (siasa.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            siasa.addPerson(person);
        }

        for (JsonAdaptedPolicy jsonAdaptedPolicy : policies) {
            Person policyOwner = jsonAdaptedPolicy.getOwner().toModelType();
            Person owner = siasa.getPersonList().get(siasa.getPersonList().indexOf(policyOwner));
            Policy policy = jsonAdaptedPolicy.toModelType(owner);
            if (siasa.hasPolicy(policy)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POLICY);
            }
            siasa.addPolicy(policy);
        }
        return siasa;
    }

}
