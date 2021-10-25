package seedu.fast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalPersons.ALICE;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;
import static seedu.fast.testutil.TypicalPersons.getTypicalFastSetTwo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.exceptions.DuplicatePersonException;
import seedu.fast.testutil.PersonBuilder;

public class FastTest {

    private final Fast fast = new Fast();
    private final Fast fastSetTwo = getTypicalFastSetTwo();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fast.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fast.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFast_replacesData() {
        Fast newData = getTypicalFast();
        fast.resetData(newData);
        assertEquals(newData, fast);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        FastStub newData = new FastStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> fast.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fast.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInFast_returnsFalse() {
        assertFalse(fast.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInFast_returnsTrue() {
        fast.addPerson(ALICE);
        assertTrue(fast.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInFast_returnsTrue() {
        fast.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(fast.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fast.getPersonList().remove(0));
    }




    @Test
    void getHighPriorityCount_success() {
        assertEquals(fastSetTwo.getHighPriorityCount(), 2);
    }

    @Test
    void getMediumPriorityCount_success() {
        assertEquals(fastSetTwo.getMediumPriorityCount(), 2);
    }

    @Test
    void getLowPriorityCount_success() {
        assertEquals(fastSetTwo.getLowPriorityCount(), 4);
    }

    @Test
    void getLifeInsuranceCount() {
        assertEquals(fastSetTwo.getLifeInsuranceCount(), 2);
    }

    @Test
    void getMotorInsuranceCount() {
        assertEquals(fastSetTwo.getMotorInsuranceCount(), 2);
    }

    @Test
    void getHealthInsuranceCount() {
        assertEquals(fastSetTwo.getHealthInsuranceCount(), 1);
    }

    @Test
    void getTravelInsuranceCount() {
        assertEquals(fastSetTwo.getTravelInsuranceCount(), 4);
    }

    @Test
    void getPropertyInsuranceCount() {
        assertEquals(fastSetTwo.getPropertyInsuranceCount(), 1);
    }

    @Test
    void getInvestmentCount() {
        assertEquals(fastSetTwo.getInvestmentCount(), 3);
    }

    @Test
    void getSavingsCount() {
        assertEquals(fastSetTwo.getSavingsCount(), 8);
    }


    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FastStub implements ReadOnlyFast {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        FastStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
