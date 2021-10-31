package seedu.track2gather.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_BOB;
import static seedu.track2gather.testutil.Assert.assertThrows;
import static seedu.track2gather.testutil.TypicalPersons.ALICE;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.exceptions.DuplicatePersonException;
import seedu.track2gather.testutil.PersonBuilder;

public class Track2GatherTest {

    private final Track2Gather track2Gather = new Track2Gather();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), track2Gather.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> track2Gather.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrack2Gather_replacesData() {
        Track2Gather newData = getTypicalTrack2Gather();
        track2Gather.resetData(newData);
        assertEquals(newData, track2Gather);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        Track2GatherStub newData = new Track2GatherStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> track2Gather.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> track2Gather.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTrack2Gather_returnsFalse() {
        assertFalse(track2Gather.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTrack2Gather_returnsTrue() {
        track2Gather.addPerson(ALICE);
        assertTrue(track2Gather.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTrack2Gather_returnsTrue() {
        track2Gather.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        assertTrue(track2Gather.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> track2Gather.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTrack2Gather whose persons list can violate interface constraints.
     */
    private static class Track2GatherStub implements ReadOnlyTrack2Gather {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        Track2GatherStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
