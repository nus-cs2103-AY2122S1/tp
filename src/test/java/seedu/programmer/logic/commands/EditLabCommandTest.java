package seedu.programmer.logic.commands;


import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_EDIT_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EDIT_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EDIT_LAB_NO2;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EDIT_LAB_NO3;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NEW_LAB_TOTAL;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_FIRST_LAB;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_SECOND_LAB;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabTotal;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code EditLabCommand}.
 */
public class EditLabCommandTest {
    private static LabNum newLabNum = new LabNum(VALID_EDIT_LAB_NO);
    private static LabNum newLabNum2 = new LabNum(VALID_EDIT_LAB_NO2);
    private static LabNum newLabNum3 = new LabNum(VALID_EDIT_LAB_NO3);
    private static LabNum invalidLabNum = new LabNum(INVALID_EDIT_LAB_NO);
    private static LabTotal newLabTotal = new LabTotal(VALID_NEW_LAB_TOTAL);

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditLabCommand(null, newLabNum, newLabTotal));
    }

    @Test
    public void execute_labTitleDoesNotExist_throwsCommandException() {
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Lab labToEdit = new Lab(invalidLabNum);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum);

        String expectedMessage = String.format(Lab.MESSAGE_LAB_NOT_EXISTS, labToEdit);

        assertCommandFailure(editLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_newlabTitleAlreadyExists_throwsCommandException() {
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Lab labToEdit = getTypicalLabList().get(NUMBER_FIRST_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, new LabNum(VALID_LAB_NO));

        String expectedMessage = String.format(Lab.MESSAGE_LAB_ALREADY_EXISTS, labToEdit);

        assertCommandFailure(editLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_labEditedAllFields_success() {
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Lab labToEdit = getTypicalLabList().get(NUMBER_FIRST_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum2, newLabTotal);
        Lab newLab = new Lab(newLabNum2, newLabTotal);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_labEditedOnlyOneField_success() {
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        // only change labNum
        Lab labToEdit = getTypicalLabList().get(NUMBER_FIRST_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum3);
        Lab newLab = new Lab(newLabNum3);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand, model, expectedMessage, expectedModel);

        // only change totalScore
        Lab labToEdit2 = getTypicalLabList().get(NUMBER_SECOND_LAB);
        EditLabCommand editLabCommand2 = new EditLabCommand(labToEdit2, newLabTotal);
        Lab newLab2 = new Lab(labToEdit2.getLabNum(), newLabTotal);

        String expectedMessage2 = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab2);

        Model expectedModel2 = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand2, model, expectedMessage2, expectedModel2);
    }

}
