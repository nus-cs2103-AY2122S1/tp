package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.ASSISTANT_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.HR_MANAGER_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.position.exceptions.DuplicatePositionException;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PositionBuilder;

public class HrManagerTest {

    private final HrManager hrManager = new HrManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hrManager.getPersonList());
        assertEquals(Collections.emptyList(), hrManager.getPositionList());
        assertEquals(Collections.emptyList(), hrManager.getInterviewList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHrManager_replacesData() {
        HrManager newData = getTypicalHrManager();
        hrManager.resetData(newData);
        assertEquals(newData, hrManager);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        HrManagerStub newData = new HrManagerStub(() -> newPersons);

        assertThrows(DuplicatePersonException.class, () -> hrManager.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePositions_throwsDuplicatePositionException() {
        // Two positions with the same identity fields
        Position editedAssistant = new PositionBuilder(ADMIN_ASSISTANT).build();
        List<Position> newPositions = Arrays.asList(ADMIN_ASSISTANT, editedAssistant);
        HrManagerStub newData = new HrManagerStub(() -> newPositions);

        assertThrows(DuplicatePositionException.class, () -> hrManager.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateInterviews_throwsDuplicateInterviewException() {
        // Two interviews with the same identity fields
        Interview editedAssistantInterview = new InterviewBuilder().build();
        List<Interview> newInterviews = Arrays.asList(HR_MANAGER_INTERVIEW, editedAssistantInterview);
        HrManagerStub newData = new HrManagerStub(() -> newInterviews);

        assertThrows(DuplicateInterviewException.class, () -> hrManager.resetData(newData));
    }

    //// person list

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInHrManager_returnsFalse() {
        assertFalse(hrManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInHrManager_returnsTrue() {
        hrManager.addPerson(ALICE);
        assertTrue(hrManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInHrManager_returnsTrue() {
        hrManager.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hrManager.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrManager.getPersonList().remove(0));
    }

    //// position list

    @Test
    public void hasPosition_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.hasPosition(null));
    }

    @Test
    public void hasPosition_positionNotInHrManager_returnsFalse() {
        assertFalse(hrManager.hasPosition(ADMIN_ASSISTANT));
    }

    @Test
    public void hasPosition_positionInHrManager_returnsTrue() {
        hrManager.addPosition(ADMIN_ASSISTANT);
        assertTrue(hrManager.hasPosition(ADMIN_ASSISTANT));
    }

    @Test
    public void hasPosition_positionWithSameIdentityFieldsInHrManager_returnsTrue() {
        hrManager.addPosition(ADMIN_ASSISTANT);
        Position editedAdminAssistant = new PositionBuilder(ADMIN_ASSISTANT).build();
        assertTrue(hrManager.hasPosition(editedAdminAssistant));
    }

    @Test
    public void getPositionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrManager.getPositionList().remove(0));
    }

    @Test
    public void deletePositionFromPerson() {
        HrManager testManager = new HrManager();
        Position hrManager = new Position(new Title("HR Manager"));
        List<Person> persons = testManager.getPersonList();
        testManager.addPerson(new PersonBuilder().withName("A").withEmail("A@yahoo.com").build());
        testManager.addPerson(new PersonBuilder().withName("B").withEmail("B@gmail.com").build());
        testManager.addPerson(new PersonBuilder().withName("C").withEmail("C@outlook.com").build());

        // All persons applied for HrManager
        for (Person p : persons) {
            assertTrue(p.appliedForPosition(hrManager));
        }

        testManager.deletePositionFromPerson(hrManager);

        // All persons did not apply for HrManager
        for (Person p : persons) {
            assertFalse(p.appliedForPosition(hrManager));
        }
    }

    @Test
    public void hasInterview_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.hasInterview(null));
    }

    @Test
    public void hasInterview_interviewNotInHrManager_returnsFalse() {
        assertFalse(hrManager.hasInterview(ASSISTANT_INTERVIEW));
    }

    @Test
    public void hasInterview_interviewInHrManager_returnsTrue() {
        hrManager.addInterview(ASSISTANT_INTERVIEW);
        assertTrue(hrManager.hasInterview(ASSISTANT_INTERVIEW));
    }

    @Test
    public void hasInterview_interviewWithSameIdentityFieldsInHrManager_returnsTrue() {
        hrManager.addInterview(ASSISTANT_INTERVIEW);
        Interview editedAssistantInterview = new InterviewBuilder(ASSISTANT_INTERVIEW).build();
        assertTrue(hrManager.hasInterview(editedAssistantInterview));
    }

    @Test
    public void getInterviewList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrManager.getInterviewList().remove(0));
    }

    /**
     * A stub ReadOnlyHrManager whose persons list can violate interface constraints.
     */
    private static class HrManagerStub implements ReadOnlyHrManager {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Position> positions = FXCollections.observableArrayList();
        private final ObservableList<Interview> interviews = FXCollections.observableArrayList();

        private interface CollectionPersonRef extends Supplier<Collection<Person>> {
        }

        HrManagerStub(CollectionPersonRef persons) {
            this.persons.setAll(persons.get());
        }

        private interface CollectionPositionRef extends Supplier<Collection<Position>> {
        }

        HrManagerStub(CollectionPositionRef positions) {
            this.positions.setAll(positions.get());
        }

        private interface CollectionInterviewRef extends Supplier<Collection<Interview>> {
        }

        HrManagerStub(CollectionInterviewRef interviews) {
            this.interviews.setAll(interviews.get());
        }

        public void setPositionsStub(Collection<Position> positions) {
            this.positions.setAll(positions);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Position> getPositionList() {
            return positions;
        }

        @Override
        public ObservableList<Interview> getInterviewList() {
            return interviews;
        }
    }

}
