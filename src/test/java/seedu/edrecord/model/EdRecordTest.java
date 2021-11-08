package seedu.edrecord.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_INFO_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalPersons.ALICE;
import static seedu.edrecord.testutil.TypicalPersons.getTypicalEdRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.exceptions.DuplicatePersonException;
import seedu.edrecord.testutil.PersonBuilder;

public class EdRecordTest {

    private final EdRecord edRecord = new EdRecord();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), edRecord.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> edRecord.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEdRecord_replacesData() {
        EdRecord newData = getTypicalEdRecord();
        edRecord.resetData(newData);
        assertEquals(newData, edRecord);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withInfo(VALID_INFO_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        EdRecordStub newData = new EdRecordStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> edRecord.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> edRecord.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEdRecord_returnsFalse() {
        assertFalse(edRecord.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEdRecord_returnsTrue() {
        edRecord.addPerson(ALICE);
        assertTrue(edRecord.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEdRecord_returnsTrue() {
        edRecord.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withInfo(VALID_INFO_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(edRecord.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> edRecord.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEdRecord whose persons list can violate interface constraints.
     */
    private static class EdRecordStub implements ReadOnlyEdRecord {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        EdRecordStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
