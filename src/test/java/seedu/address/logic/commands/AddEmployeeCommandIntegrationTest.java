package seedu.address.logic.commands;

import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalRhrhEmployees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddEmployeeCommand}.
 */
public class AddEmployeeCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRhrhEmployees(), new UserPrefs());
    }

    @Test
    public void execute_newEmployee_success() {
        Employee validEmployee = new EmployeeBuilder().build();

        Model expectedModel = new ModelManager(model.getRhrh(), new UserPrefs());
        expectedModel.addEmployee(validEmployee);

        assertCommandSuccess(new AddEmployeeCommand(validEmployee), model,
                String.format(AddEmployeeCommand.MESSAGE_SUCCESS, validEmployee), expectedModel);
    }

    @Test
    public void execute_duplicateEmployee_throwsCommandException() {
        ReadOnlyRhrh temp = model.getRhrh();
        Employee employeeInList = model.getRhrh().getEmployeeList().get(0);
        assertCommandFailure(new AddEmployeeCommand(employeeInList), model,
                AddEmployeeCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

}
