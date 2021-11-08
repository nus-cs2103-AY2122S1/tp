package seedu.siasa.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableSiasa {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";
    public static final String MESSAGE_DUPLICATE_POLICY = "Policy list contains duplicate policies(s).";
    public static final String MESSAGE_POLICY_NO_OWNER_FOUND = "Contact list does not contain owner of policy.";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedPolicy> policies = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSiasa} with the given contacts and policies.
     */
    @JsonCreator
    public JsonSerializableSiasa(@JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                 @JsonProperty("policies") List<JsonAdaptedPolicy> policies) {
        this.contacts.addAll(contacts);
        this.policies.addAll(policies);
    }

    /**
     * Converts a given {@code ReadOnlySiasa} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableSiasa(ReadOnlySiasa source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        policies.addAll(source.getPolicyList().stream().map(JsonAdaptedPolicy::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SIASA into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Siasa toModelType() throws IllegalValueException {
        Siasa siasa = new Siasa();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (siasa.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            siasa.addContact(contact);
        }

        for (JsonAdaptedPolicy jsonAdaptedPolicy : policies) {
            Contact policyOwner = jsonAdaptedPolicy.getOwner().toModelType();
            ObservableList<Contact> contactList = siasa.getContactList();
            if (!contactList.contains(policyOwner)) {
                throw new IllegalValueException(MESSAGE_POLICY_NO_OWNER_FOUND);
            }
            int ownerIndex = contactList.indexOf(policyOwner);
            Contact owner = contactList.get(ownerIndex);
            Policy policy = jsonAdaptedPolicy.toModelType(owner);
            if (siasa.hasPolicy(policy)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POLICY);
            }
            siasa.addPolicy(policy);
        }
        return siasa;
    }

}
