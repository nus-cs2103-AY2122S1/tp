package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TutorialClassBuilder;
import seedu.address.testutil.TutorialGroupBuilder;

class AddGroupCommandTest {

    @Test
    public void constructor_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_tutorialGroupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialGroup modelStub = new ModelStubAcceptingTutorialGroup();
        TutorialGroup validGroup = new TutorialGroupBuilder().build();

        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.tutorialGroupsAdded);
    }

    @Test
    public void execute_duplicateTutorialGroup_throwsCommandException() {
        TutorialClass validClass = new TutorialClassBuilder().build();
        TutorialGroup validGroup = new TutorialGroupBuilder().build();
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);
        ModelStub modelStub = new ModelStubWithTutorialGroup(validClass, validGroup);

        assertThrows(CommandException.class,
                AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> addGroupCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TutorialGroup a = new TutorialGroupBuilder().withClassCode("G01").build();
        TutorialGroup b = new TutorialGroupBuilder().withClassCode("G02").build();

        AddGroupCommand addACommand = new AddGroupCommand(a);
        AddGroupCommand addBCommand = new AddGroupCommand(b);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddGroupCommand addACommandCopy = new AddGroupCommand(a);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different student -> returns false
        assertFalse(addACommand.equals(addBCommand));
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

    private class ModelStubAcceptingTutorialGroup extends ModelStub {
        final ArrayList<TutorialClass> tutorialClassesAdded = new ArrayList<>(Arrays.asList(
                new TutorialClassBuilder().build()));
        final ArrayList<TutorialGroup> tutorialGroupsAdded = new ArrayList<>();

        @Override
        public boolean hasTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            return tutorialClassesAdded.stream().anyMatch(tutorialClass::isSameTutorialClass);
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return tutorialGroupsAdded.stream().anyMatch(tutorialGroup::isSameTutorialGroup);
        }

        @Override
        public void addTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            tutorialClassesAdded.add(tutorialClass);
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            tutorialGroupsAdded.add(tutorialGroup);
        }

        @Override
        public void sortTutorialGroups() {
            // this does not test whether tutorial groups are sorted, and so it is empty.
        }

        @Override
        public ReadOnlyClassmate getClassmate() {
            return new Classmate();
        }
    }

}
