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
import seedu.siasa.model.policy.Policy;

/**
 * An Immutable PolicyBook that is serializable to JSON format.
 */
@JsonRootName(value = "policybook")
public class JsonSerializablePolicyBook {

    public static final String MESSAGE_DUPLICATE_POLICY = "Policies list contains duplicate policy(s).";

    private final List<JsonAdaptedPolicy> policies = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePolicyBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePolicyBook(@JsonProperty("policies") List<JsonAdaptedPolicy> policies) {
        this.policies.addAll(policies);
    }

    /**
     * Converts a given {@code ReadOnlyPolicyBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePolicyBook}.
     */
    public JsonSerializablePolicyBook(ReadOnlySiasa source) {
        policies.addAll(source.getPolicyList().stream().map(JsonAdaptedPolicy::new).collect(Collectors.toList()));
    }

    /**
     * Converts this policy book into the model's {@code PolicyBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Siasa toModelType() throws IllegalValueException {
        Siasa siasa = new Siasa();
        for (JsonAdaptedPolicy jsonAdaptedPolicy : policies) {
            Policy policy = jsonAdaptedPolicy.toModelType();
            if (siasa.hasPolicy(policy)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POLICY);
            }
            siasa.addPolicy(policy);
        }
        return siasa;
    }
}
