package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalConthacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ConthacksTest {

    private final Conthacks conthacks = new Conthacks();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), conthacks.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> conthacks.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyConthacks_replacesData() {
        Conthacks newData = getTypicalConthacks();
        conthacks.resetData(newData);
        assertEquals(newData, conthacks);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ConthacksStub newData = new ConthacksStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> conthacks.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> conthacks.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInConthacks_returnsFalse() {
        assertFalse(conthacks.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInConthacks_returnsTrue() {
        conthacks.addPerson(ALICE);
        assertTrue(conthacks.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInConthacks_returnsTrue() {
        conthacks.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(conthacks.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> conthacks.getPersonList().remove(0));
    }

    @Test
    public void sortConthacksByName_success() {
        List<Person> persons = new ArrayList<>();
        persons.add(BOB);
        persons.add(ALICE);
        conthacks.setPersons(persons);

        Conthacks expectedConthacks = new Conthacks();
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(ALICE);
        expectedPersons.add(BOB);
        expectedConthacks.setPersons(expectedPersons);

        conthacks.sortConthacks();
        assertEquals(expectedConthacks, conthacks);
    }

    /**
     * A stub ReadOnlyConthacks whose persons list can violate interface constraints.
     */
    private static class ConthacksStub implements ReadOnlyConthacks {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<ModuleLesson> lessons = FXCollections.observableArrayList();

        ConthacksStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<ModuleLesson> getModuleLessonList() {
            return lessons;
        }
    }

}
