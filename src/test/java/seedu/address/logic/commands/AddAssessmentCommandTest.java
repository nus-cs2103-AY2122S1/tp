package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Assessment;

public class AddAssessmentCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validAssessment_success() {
        Assessment validAssessment = new Assessment("pqijwe");

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAssessment(validAssessment);

        assertCommandSuccess(new AddAssessmentCommand(validAssessment), model,
                String.format(AddAssessmentCommand.MESSAGE_SUCCESS, validAssessment), expectedModel);
    }

    @Test
    public void execute_duplicateAssessment_failure() {
        Assessment duplicateAssessment = model.getAddressBook().getAssessmentList().get(0);
        assertCommandFailure(new AddAssessmentCommand(duplicateAssessment), model,
                AddAssessmentCommand.MESSAGE_DUPLICATE_ASSESSMENT);
    }

    @Test
    public void equal() {
        final AddAssessmentCommand standardCommand = new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_AMY));

        // same values -> returns true
        AddAssessmentCommand sameCommand = new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_AMY));
        assertEquals(standardCommand, sameCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_BOB)));
    }
}
