package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_FIRST_LAB;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.*;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.testutil.LabBuilder;
import seedu.programmer.testutil.StudentBuilder;

import static seedu.programmer.testutil.TypicalIndexes.NUMBER_FIRST_LAB;

public class AddLabCommandTest {
    private static Lab validLab;
    private static Lab sampleLabA;
    private static AddLabCommand sampleCommandA;
    private static AddLabCommand sampleCommandB;

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validLab = new LabBuilder().build();
        sampleLabA = new LabBuilder().withTitle("lab10").withTotal(20.0).build();
        Lab sampleLabB = new LabBuilder().withTitle("lab11").withTotal(30.0).build();
        sampleCommandA = new AddLabCommand(sampleLabA);
        sampleCommandB = new AddLabCommand(sampleLabB);
    }

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLabCommand(null));
    }

    @Test
    public void execute_labTitleAlreadyExists_throwsCommandException() throws Exception {
        Lab labToAdd = getTypicalLabList().get(NUMBER_FIRST_LAB);
        AddLabCommand addLabCommand = new AddLabCommand(labToAdd);

        String expectedMessage = String.format(AddLabCommand.MESSAGE_LAB_ALREADY_EXISTS, labToAdd);

        assertCommandFailure(addLabCommand, model, expectedMessage);
    }

    //TODO: It's failing because lab already exists when it actually does not.
    @Test
    public void execute_labAdded_success() {
        Lab labToAdd = sampleLabA;
        AddLabCommand addLabCommand = sampleCommandA;

        String expectedMessage = String.format(AddLabCommand.MESSAGE_ADD_LAB_SUCCESS, labToAdd);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());
        //expectedModel.addLab(labToAdd);

        assertCommandSuccess(addLabCommand, model, expectedMessage, expectedModel);
    }
}

