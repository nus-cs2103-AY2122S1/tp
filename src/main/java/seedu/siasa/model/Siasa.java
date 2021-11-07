package seedu.siasa.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.contact.UniqueContactList;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;
import seedu.siasa.model.policy.UniquePolicyList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameContact comparison)
 */
public class Siasa implements ReadOnlySiasa {

    private final UniqueContactList contacts;
    private final UniquePolicyList policies;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
        policies = new UniquePolicyList();
    }

    public Siasa() {
    }

    /**
     * Creates an SIASA using the Contacts and Policies in the {@code toBeCopied}
     */
    public Siasa(ReadOnlySiasa toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlySiasa newData) {
        requireNonNull(newData);

        setContacts(newData.getContactList());
        setPolicies(newData.getPolicyList());
    }

    /**
     * Replaces the policies of the current newData with {@code newData}.
     */
    public void mergePolicies(ReadOnlySiasa newData) {
        requireNonNull(newData);
        this.setPolicies(newData.getPolicyList());
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in SIASA.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Returns true if a contact with a similar full name as {@code contact}
     * exists in SIASA. Similar is defined as full names having an edit distance of zero
     * or one (case insensitive).
     */
    public Optional<Contact> getSimilarContact(Contact contact) {
        requireNonNull(contact);
        return contacts.getSimilar(contact);
    }

    /**
     * Adds a contact to the SIASA.
     * The contact must not already exist in the SIASA.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the SIASA.
     * The contact identity of {@code editedContact} must not be the same as another
     * existing contact in the SIASA.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    private void setContacts(List<Contact> contacts) {
        // Note: Have to be careful that we do not orphan associated policies
        this.contacts.setContacts(contacts);
    }

    /**
     * Removes {@code key} from this {@code Siasa} and removes associated policies.
     * {@code key} must exist in SIASA.
     */
    public void removeContactAndAssociatedPolicies(Contact key) {
        contacts.remove(key);
        policies.removeBelongingTo(key);
    }

    /**
     * Removes all policies associated with {@code key}.
     * {@code key} must exist in SIASA.
     */
    public void removePoliciesBelongingTo(Contact key) {
        policies.removeBelongingTo(key);
    }

    public Map<Contact, Integer> getNumberPoliciesPerContact() {
        HashMap<Contact, Integer> hashMap = new HashMap<>();
        contacts.forEach(contact -> {
            hashMap.put(contact, 0);
        });
        policies.forEach(policy -> {
            Contact owner = policy.getOwner();
            int currentCount = hashMap.get(owner);
            hashMap.put(owner, currentCount + 1);
        });
        List<Map.Entry<Contact, Integer>> list = new ArrayList<>(hashMap.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        Map<Contact, Integer> sortedResult = new LinkedHashMap<>();
        for (Map.Entry<Contact, Integer> entry : list) {
            sortedResult.put(entry.getKey(), entry.getValue());
        }
        return sortedResult;
    }

    public Map<Contact, Double> getCommissionPerContact() {
        HashMap<Contact, Double> hashMap = new HashMap<>();
        contacts.forEach(contact -> {
            double commission = 0;
            for (Policy policy : policies) {
                if (policy.getOwner().equals(contact)) {
                    commission += policy.getTotalCommission();
                }
            }
            hashMap.put(contact, commission);
        });
        List<Map.Entry<Contact, Double>> list = new ArrayList<>(hashMap.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        Map<Contact, Double> sortedResult = new LinkedHashMap<>();
        for (Map.Entry<Contact, Double> entry : list) {
            sortedResult.put(entry.getKey(), entry.getValue());
        }
        return sortedResult;
    }

    /// policy-level operations

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in SIASA.
     */
    public boolean hasPolicy(Policy policy) {
        requireNonNull(policy);
        return policies.contains(policy);
    }

    /**
     * Returns true if a policy with similar title and the same owner as {@code policy}
     * exists in the SIASA. Similar titles are defined as having edit distance of zero or
     * one (case insensitive).
     */
    public Optional<Policy> getSimilarPolicy(Policy policy) {
        requireNonNull(policy);
        return policies.getSimilar(policy);
    }

    public void removePolicy(Policy target) {
        policies.remove(target);
    }

    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    /**
     * Replaces the given policy {@code target} in the list with {@code editedPolicy}.
     * {@code target} must exist in the SIASA.
     * The policy identity of {@code editedPolicy} must not be the same as another existing policy in the SIASA.
     */
    public void setPolicy(Policy target, Policy editedPolicy) {
        // Note: Have to be careful that we do not have associated owners not in persons.
        requireNonNull(editedPolicy);

        policies.setPolicy(target, editedPolicy);
    }

    /**
     * Replaces the contents of the policy list with {@code policies}.
     * {@code policies} must not contain duplicate policies.
     */
    private void setPolicies(List<Policy> policies) {
        // Note: Have to be careful that we do not have associated owners not in persons.
        this.policies.setPolicies(policies);
    }

    public double getTotalCommission() {
        return policies.getTotalCommission();
    }

    public ObservableList<Policy> getPoliciesBelongingTo(Contact target) {
        return policies.asUnmodifiableObservableList().filtered(new PolicyIsOwnedByPredicate(target));
    }
    //// util methods

    @Override
    public String toString() {
        return contacts.asUnmodifiableObservableList().size() + " persons; "
                + policies.asUnmodifiableObservableList().size() + " policies;";
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Policy> getPolicyList() {
        return policies.asUnmodifiableObservableList();
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Siasa // instanceof handles nulls
                && contacts.equals(((Siasa) other).contacts)
                && policies.equals(((Siasa) other).policies));
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, policies);
    }
}
