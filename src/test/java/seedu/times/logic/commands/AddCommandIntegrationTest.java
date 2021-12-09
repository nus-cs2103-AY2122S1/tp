package seedu.times.logic.commands;

import static seedu.times.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;
import seedu.times.model.person.Student;
import seedu.times.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getTimesTable().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
