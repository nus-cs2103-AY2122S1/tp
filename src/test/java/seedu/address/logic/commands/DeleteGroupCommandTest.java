package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TutorialClassBuilder;
import seedu.address.testutil.TutorialGroupBuilder;

class DeleteGroupCommandTest {

    @Test
    public void constructor_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupCommand(null));
    }

    @Test
    public void execute_tutorialGroupDeletedByModel_deleteSuccessful() throws Exception {
        ModelStubDeletingTutorialGroup modelStub = new ModelStubDeletingTutorialGroup();
        TutorialGroup validGroup = new TutorialGroupBuilder().build();

        CommandResult commandResult = new DeleteGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, validGroup),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.tutorialGroupsResult);
    }

    @Test
    public void equals() {
        TutorialGroup a = new TutorialGroupBuilder().withClassCode("G01").build();
        TutorialGroup b = new TutorialGroupBuilder().withClassCode("G02").build();

        DeleteGroupCommand deleteACommand = new DeleteGroupCommand(a);
        DeleteGroupCommand deleteBCommand = new DeleteGroupCommand(b);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same values -> returns true
        DeleteGroupCommand deleteACommandCopy = new DeleteGroupCommand(a);
        assertTrue(deleteACommand.equals(deleteACommandCopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different student -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
    }

    /**
     * A Model stub that contains a single TutorialGroup.
     */
    private class ModelStubWithTutorialGroup extends ModelStub {
        private final TutorialClass tutorialClass;
        private final TutorialGroup tutorialGroup;

        ModelStubWithTutorialGroup(TutorialClass tutorialClass, TutorialGroup tutorialGroup) {
            this.tutorialClass = tutorialClass;
            this.tutorialGroup = tutorialGroup;
        }

        @Override
        public boolean hasTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            return this.tutorialClass.isSameTutorialClass(tutorialClass);
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return this.tutorialGroup.isSameTutorialGroup(tutorialGroup);
        }
    }

    private class ModelStubDeletingTutorialGroup extends ModelStub {
        final ArrayList<TutorialClass> tutorialClasses = new ArrayList<>(Arrays.asList(
                new TutorialClassBuilder().build()));
        final ArrayList<TutorialGroup> tutorialGroupsResult = new ArrayList<>();

        @Override
        public boolean hasTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            return tutorialClasses.stream().anyMatch(tutorialClass::isSameTutorialClass);
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            // get the only class in the list of classes(index zero)
            return tutorialClasses.get(0).getTutorialGroupsAsList()
                    .stream().anyMatch(tutorialGroup::isSameTutorialGroup);
        }

        @Override
        public void deleteTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            tutorialClasses.remove(tutorialGroup);
        }

        @Override
        public void sortTutorialGroups() {
            // this does not test whether tutorial groups are sorted, and so it is empty.
        }

        @Override
        public ObservableList<Student> getUnfilteredStudentList() {
            return FXCollections.observableArrayList();
        }

        @Override
        public ReadOnlyClassmate getClassmate() {
            return new Classmate();
        }
    }
}
