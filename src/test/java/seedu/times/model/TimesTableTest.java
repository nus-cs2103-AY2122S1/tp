package seedu.times.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalTimestable.ALICE;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.times.model.person.Student;
import seedu.times.model.person.exceptions.DuplicatePersonException;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.PersonBuilder;

public class TimesTableTest {

    private final TimesTable timesTable = new TimesTable();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), timesTable.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timesTable.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTimesTable_replacesData() {
        TimesTable newData = getTypicalTimesTable();
        timesTable.resetData(newData);
        assertEquals(newData, timesTable);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        TimesTableStub newData = new TimesTableStub(newStudents);

        assertThrows(DuplicatePersonException.class, () -> timesTable.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timesTable.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTimesTable_returnsFalse() {
        assertFalse(timesTable.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTimesTable_returnsTrue() {
        timesTable.addStudent(ALICE);
        assertTrue(timesTable.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTimesTable_returnsTrue() {
        timesTable.addStudent(ALICE);
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(timesTable.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> timesTable.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyTimesTable whose persons list can violate interface constraints.
     */
    private static class TimesTableStub implements ReadOnlyTimesTable {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        TimesTableStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<TuitionClass> getTuitionClassList() {
            return null;
        }


    }

}
