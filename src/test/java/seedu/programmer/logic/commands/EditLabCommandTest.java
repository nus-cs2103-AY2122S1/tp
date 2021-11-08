package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_FIRST_LAB;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_SECOND_LAB;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabTotal;
import seedu.programmer.testutil.LabBuilder;

public class EditLabCommandTest {
    private static Lab validLab;
    private static Lab sampleLabA;
    private static Lab sampleLabB;
    private static LabNum newLabNum = new LabNum(11);
    private static LabNum newLabNum1 = new LabNum(12);
    private static LabNum newLabNum2 = new LabNum(13);
    private static LabTotal newLabTotal = new LabTotal(40);
    private static EditLabCommand sampleCommandA;
    private static EditLabCommand sampleCommandB;
    private static EditLabCommand sampleCommandC;

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validLab = new LabBuilder().build();
        sampleLabA = new LabBuilder().withLabNum(10).withTotal(20).build();
        Lab sampleLabB = new LabBuilder().withLabNum(11).withTotal(30).build();
        sampleCommandA = new EditLabCommand(sampleLabA, newLabNum, newLabTotal);
        sampleCommandB = new EditLabCommand(sampleLabB, newLabNum);
        sampleCommandC = new EditLabCommand(sampleLabB, newLabTotal);
    }

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLabCommand(null));
    }

    @Test
    public void execute_labTitleDoesNotExist_throwsCommandException() {
        Lab labToEdit = new Lab(new LabNum(12));
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum);

        String expectedMessage = String.format(Lab.MESSAGE_LAB_NOT_EXISTS, labToEdit);

        assertCommandFailure(editLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_newlabTitleAlreadyExists_throwsCommandException() {
        Lab labToEdit = getTypicalLabList().get(NUMBER_FIRST_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, new LabNum(1));

        String expectedMessage = String.format(Lab.MESSAGE_LAB_ALREADY_EXISTS, labToEdit);

        assertCommandFailure(editLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_labEditedAllFields_success() {
        Lab labToEdit = getTypicalLabList().get(NUMBER_FIRST_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum1, newLabTotal);
        Lab newLab = new Lab(newLabNum1, newLabTotal);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_labEditedOnlyOneField_success() {
        // only change labNum
        Lab labToEdit = getTypicalLabList().get(NUMBER_SECOND_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum2);
        Lab newLab = new Lab(newLabNum2);

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

    @Test
    public void equals_sameLab_returnsTrue() {
        EditLabCommand sampleCommandACopy = new EditLabCommand(sampleLabA, newLabNum, newLabTotal);
        assertEquals(sampleCommandA, sampleCommandACopy);
    }
}
