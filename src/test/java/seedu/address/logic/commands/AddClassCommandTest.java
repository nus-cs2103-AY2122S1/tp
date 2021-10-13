package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TutorialClassBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class AddClassCommandTest {
    @Test
    public void constructor_nullTutorialClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClassCommand(null));
    }

    @Test
    public void execute_tutorialClassAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialClass modelStub = new ModelStubAcceptingTutorialClass();
        TutorialClass validClass = new TutorialClassBuilder().build();

        CommandResult commandResult = new AddClassCommand(validClass).execute(modelStub);

        assertEquals(String.format(AddClassCommand.MESSAGE_SUCCESS, validClass), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClass), modelStub.tutorialClassesAdded);
    }

    @Test
    public void execute_duplicateTutorialClass_throwsCommandException() {
        TutorialClass validClass = new TutorialClassBuilder().build();
        AddClassCommand addClassCommand = new AddClassCommand(validClass);
        ModelStub modelStub = new ModelStubWithTutorialClass(validClass);

        assertThrows(CommandException.class,
                AddClassCommand.MESSAGE_DUPLICATE_CLASS, () -> addClassCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TutorialClass A = new TutorialClassBuilder().withClassCode("A").build();
        TutorialClass B = new TutorialClassBuilder().withClassCode("B").build();

        AddClassCommand addACommand = new AddClassCommand(A);
        AddClassCommand addBCommand = new AddClassCommand(B);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddClassCommand addACommandCopy = new AddClassCommand(A);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different student -> returns false
        assertFalse(addACommand.equals(addBCommand));
    }

    /**
     * A Model stub that contains a single TutorialClass.
     */
    private class ModelStubWithTutorialClass extends ModelStub {
        private final TutorialClass tutorialClass;

        ModelStubWithTutorialClass(TutorialClass tutorialClass) {
            this.tutorialClass = tutorialClass;
        }

        @Override
        public boolean hasTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            return this.tutorialClass.isSameTutorialClass(tutorialClass);
        }
    }

    private class ModelStubAcceptingTutorialClass extends ModelStub {
        final ArrayList<TutorialClass> tutorialClassesAdded = new ArrayList<>();

        @Override
        public boolean hasTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            return tutorialClassesAdded.stream().anyMatch(tutorialClass::isSameTutorialClass);
        }

        @Override
        public void addTutorialClass(TutorialClass tutorialClass) {
            requireNonNull(tutorialClass);
            tutorialClassesAdded.add(tutorialClass);
        }

        @Override
        public ReadOnlyClassmate getClassmate() {
            return new Classmate();
        }
    }
}
