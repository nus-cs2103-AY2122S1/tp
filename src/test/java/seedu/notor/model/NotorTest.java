package seedu.notor.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.notor.testutil.Assert.assertThrows;
import static seedu.notor.testutil.TypicalPersons.ALICE;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonBuilder;


public class NotorTest {

    private final Notor notor = new Notor();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), notor.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notor.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNotor_replacesData() {
        Notor newData = getTypicalNotor();
        notor.resetData(newData);
        assertEquals(newData, notor);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notor.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInNotor_returnsFalse() {
        assertFalse(notor.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInNotor_returnsTrue() {
        notor.addPerson(ALICE);
        assertTrue(notor.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInNotor_returnsTrue() {
        notor.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(notor.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> notor.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyNotor whose persons list can violate interface constraints.
     */
    private static class NotorStub implements ReadOnlyNotor {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        NotorStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public HashMap<String, SuperGroup> getSuperGroups() {
            return null;
        }

        @Override
        public HashMap<String, SubGroup> getSubGroups() {
            return null;
        }
    }

}
