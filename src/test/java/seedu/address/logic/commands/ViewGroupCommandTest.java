package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TutorialClassBuilder;
import seedu.address.testutil.TutorialGroupBuilder;

class ViewGroupCommandTest {

    @Test
    public void constructor_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewGroupCommand(null));
    }

    @Test
    public void equals() {
        TutorialGroup a = new TutorialGroupBuilder().withClassCode("G01").build();
        TutorialGroup b = new TutorialGroupBuilder().withClassCode("G02").build();

        ViewGroupCommand viewACommand = new ViewGroupCommand(a);
        ViewGroupCommand viewBCommand = new ViewGroupCommand(b);

        // same object -> returns true
        assertTrue(viewACommand.equals(viewACommand));

        // same values -> returns true
        ViewGroupCommand viewACommandCopy = new ViewGroupCommand(a);
        assertTrue(viewACommand.equals(viewACommandCopy));

        // different types -> returns false
        assertFalse(viewACommand.equals(1));

        // null -> returns false
        assertFalse(viewACommand.equals(null));

        // different class -> returns false
        assertFalse(viewACommand.equals(viewBCommand));
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
        public ReadOnlyClassmate getClassmate() {
            return new Classmate();
        }
    }
}
