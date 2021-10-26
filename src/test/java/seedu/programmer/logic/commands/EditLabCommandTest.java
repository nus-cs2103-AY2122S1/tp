package seedu.programmer.logic.commands;

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
import seedu.programmer.testutil.LabBuilder;

public class EditLabCommandTest {
    private static Lab validLab;
    private static Lab sampleLabA;
    private static Lab sampleLabB;
    private static int newLabNum = 11;
    private static Double newLabTotal = 40.0;
    private static EditLabCommand sampleCommandA;
    private static EditLabCommand sampleCommandB;
    private static EditLabCommand sampleCommandC;

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validLab = new LabBuilder().build();
        sampleLabA = new LabBuilder().withLabNum(10).withTotal(20.0).build();
        Lab sampleLabB = new LabBuilder().withLabNum(11).withTotal(30.0).build();
        sampleCommandA = new EditLabCommand(sampleLabA, newLabNum, newLabTotal);
        sampleCommandB = new EditLabCommand(sampleLabB, newLabNum);
        sampleCommandC = new EditLabCommand(sampleLabB, newLabTotal);
    }

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLabCommand(null));
    }

//    @Test
//    public void execute_labTitleAlreadyExists_throwsCommandException() throws Exception {
//        Lab labToAdd = getTypicalLabList().get(NUMBER_FIRST_LAB);
//        EditLabCommand editLabCommand = new EditLabCommand(labToAdd, );
//
//        String expectedMessage = String.format(AddLabCommand.MESSAGE_LAB_ALREADY_EXISTS, labToAdd);
//
//        assertCommandFailure(addLabCommand, model, expectedMessage);
//    }

    @Test
    public void execute_labEditedAllFields_success() {
        Lab labToEdit = getTypicalLabList().get(NUMBER_SECOND_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum, newLabTotal);
        Lab newLab = new Lab(newLabNum, newLabTotal);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_labEditedOnlyOneField_success() {
        // only change labNum
        Lab labToEdit = getTypicalLabList().get(NUMBER_SECOND_LAB);
        EditLabCommand editLabCommand = new EditLabCommand(labToEdit, newLabNum);
        Lab newLab = new Lab(newLabNum);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand, model, expectedMessage, expectedModel);

        // only change totalScore
        Lab labToEdit2 = getTypicalLabList().get(NUMBER_SECOND_LAB);
        EditLabCommand editLabCommand2 = new EditLabCommand(labToEdit2, newLabNum, newLabTotal);
        Lab newLab2 = new Lab(newLabNum, newLabTotal);

        String expectedMessage2 = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, newLab2);

        Model expectedModel2 = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());

        assertCommandSuccess(editLabCommand2, model, expectedMessage2, expectedModel2);
    }
}
