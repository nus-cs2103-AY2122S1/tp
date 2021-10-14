package seedu.siasa.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.person.UniquePersonList;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.UniquePolicyList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Siasa implements ReadOnlySiasa {

    private final UniquePersonList persons;
    private final UniquePolicyList policies;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        persons = new UniquePersonList();
        policies = new UniquePolicyList();
    }

    public Siasa() {
    }

    /**
     * Creates an SIASA using the Persons and Policies in the {@code toBeCopied}
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

        setPersons(newData.getPersonList());
        setPolicies(newData.getPolicyList());
    }

    /**
     * Replaces the policies of the current newData with {@code newData}.
     */
    public void mergePolicies(ReadOnlySiasa newData) {
        requireNonNull(newData);
        this.setPolicies(newData.getPolicyList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in SIASA.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    private void setPersons(List<Person> persons) {
        // Note: Have to be careful that we do not orphan associated policies
        this.persons.setPersons(persons);
    }

    /**
     * Removes {@code key} from this {@code Siasa} and removes associated policies.
     * {@code key} must exist in SIASA.
     */
    public void removePersonAndAssociatedPolicies(Person key) {
        persons.remove(key);
        policies.removeBelongingTo(key);
    }

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in SIASA.
     */
    public boolean hasPolicy(Policy policy) {
        requireNonNull(policy);
        return policies.contains(policy);
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


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons; "
            + policies.asUnmodifiableObservableList().size() + " policies;";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Policy> getPolicyList() {
        return policies.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Siasa // instanceof handles nulls
            && persons.equals(((Siasa) other).persons)
            && policies.equals(((Siasa) other).policies));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, policies);
    }
}
