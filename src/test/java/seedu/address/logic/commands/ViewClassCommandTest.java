package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialClassAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIALCLASS;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTORIALCLASS;
import static seedu.address.testutil.TypicalTutorialClasses.getTypicalClassmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.ClassMemberPredicate;
import seedu.address.model.tutorialclass.TutorialClass;

class ViewClassCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClassmate(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalClassmate(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        TutorialClass tutorialClassToView =
                model.getFilteredTutorialClassList().get(INDEX_FIRST_TUTORIALCLASS.getZeroBased());
        ViewClassCommand viewClassCommand = new ViewClassCommand(INDEX_FIRST_TUTORIALCLASS);

        String expectedMessage = String.format(ViewClassCommand.MESSAGE_VIEW_CLASS_SUCCESS, tutorialClassToView);
        expectedModel.updateFilteredTutorialClassList(tc -> tc.isSameTutorialClass(tutorialClassToView));

        ClassMemberPredicate predicate = prepareClassMemberPredicate(tutorialClassToView.getClassCode());
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(viewClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialClassList().size() + 1);
        ViewClassCommand viewCommand = new ViewClassCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorialClassAtIndex(model, INDEX_FIRST_TUTORIALCLASS);

        Index outOfBoundIndex = INDEX_SECOND_TUTORIALCLASS;
        // ensures that outOfBoundIndex is still in bounds of ClassMATE list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClassmate().getTutorialClassList().size());

        ViewClassCommand viewClassCommand = new ViewClassCommand(outOfBoundIndex);

        assertCommandFailure(viewClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewClassCommand viewFirstCommand = new ViewClassCommand(INDEX_FIRST_TUTORIALCLASS);
        ViewClassCommand viewSecondCommand = new ViewClassCommand(INDEX_SECOND_TUTORIALCLASS);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewClassCommand viewFirstCommandCopy = new ViewClassCommand(INDEX_FIRST_TUTORIALCLASS);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));

    }

    public ClassMemberPredicate prepareClassMemberPredicate(ClassCode classCode) {
        return new ClassMemberPredicate(classCode);
    }

}
