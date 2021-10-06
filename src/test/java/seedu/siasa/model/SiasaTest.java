package seedu.siasa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PRICE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalPersons.ALICE;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.person.exceptions.DuplicatePersonException;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.exceptions.DuplicatePolicyException;
import seedu.siasa.testutil.PersonBuilder;
import seedu.siasa.testutil.PolicyBuilder;

public class SiasaTest {

    private final Siasa siasa = new Siasa();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), siasa.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySiasa_replacesData() {
        Siasa newData = getTypicalSiasa();
        siasa.resetData(newData);
        assertEquals(newData, siasa);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        SiasaStub newData = new SiasaStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> siasa.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInSiasa_returnsFalse() {
        assertFalse(siasa.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInSiasa_returnsTrue() {
        siasa.addPerson(ALICE);
        assertTrue(siasa.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInSiasa_returnsTrue() {
        siasa.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(siasa.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> siasa.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicatePolicy_throwsDuplicatePolicyException() {
        // Two policies with the same identity fields (name and owner)
        Policy editedLifePlan = new PolicyBuilder(FULL_LIFE)
                .withPrice(VALID_POLICY_PRICE_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        List<Policy> policies = Arrays.asList(FULL_LIFE, editedLifePlan);
        List<Person> persons = Arrays.asList(FULL_LIFE.getOwner());
        SiasaStub newData = new SiasaStub(persons, policies);

        assertThrows(DuplicatePolicyException.class, () -> siasa.resetData(newData));
    }

    @Test
    public void hasPolicy_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.hasPolicy(null));
    }

    @Test
    public void hasPolicy_policyNotInSiasa_returnsFalse() {
        assertFalse(siasa.hasPolicy(FULL_LIFE));
    }

    @Test
    public void hasPolicy_policyInSiasa_returnsTrue() {
        siasa.addPolicy(FULL_LIFE);
        assertTrue(siasa.hasPolicy(FULL_LIFE));
    }

    @Test
    public void hasPolicy_policyWithSameIdentityFieldsInSiasa_returnsTrue() {
        siasa.addPolicy(FULL_LIFE);
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE)
                .withPrice(VALID_POLICY_PRICE_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        assertTrue(siasa.hasPolicy(editedFullLife));
    }

    /**
     * A stub ReadOnlySiasa whose persons list can violate interface constraints.
     */
    private static class SiasaStub implements ReadOnlySiasa {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Policy> policies = FXCollections.observableArrayList();

        SiasaStub(Collection<Person> persons, Collection<Policy> policies) {
            this.policies.setAll(policies);
            this.persons.setAll(persons);
        }

        SiasaStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Policy> getPolicyList() {
            return policies;
        }
    }

}
