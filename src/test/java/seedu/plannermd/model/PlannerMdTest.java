package seedu.plannermd.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.TypicalPersons.ALICE;
import static seedu.plannermd.testutil.TypicalPersons.getTypicalPlannerMd;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.exceptions.DuplicatePersonException;
import seedu.plannermd.testutil.PersonBuilder;

public class PlannerMdTest {

    private final PlannerMd plannerMd = new PlannerMd();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), plannerMd.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> plannerMd.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPlannerMd_replacesData() {
        PlannerMd newData = getTypicalPlannerMd();
        plannerMd.resetData(newData);
        assertEquals(newData, plannerMd);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        PlannerMdStub newData = new PlannerMdStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> plannerMd.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> plannerMd.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPlannerMd_returnsFalse() {
        assertFalse(plannerMd.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPlannerMd_returnsTrue() {
        plannerMd.addPerson(ALICE);
        assertTrue(plannerMd.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPlannerMd_returnsTrue() {
        plannerMd.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(plannerMd.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> plannerMd.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyPlannerMd whose persons list can violate interface constraints.
     */
    private static class PlannerMdStub implements ReadOnlyPlannerMd {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        PlannerMdStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
