package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.BOB_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EmployeeBuilder;

public class EmployeeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee employee = new EmployeeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> employee.getTags().remove(0));
    }

    @Test
    public void isSameEmployee() {
        // same object -> returns true
        assertTrue(ALICE_EMPLOYEE.isSameEmployee(ALICE_EMPLOYEE));

        // null -> returns false
        assertFalse(ALICE_EMPLOYEE.isSameEmployee(null));

        // same name, all other attributes different -> returns true
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withLeaves(VALID_LEAVES_BOB).withSalary(VALID_SALARY_BOB).withJobTitle(VALID_JOBTITLE_BOB).build();
        assertTrue(ALICE_EMPLOYEE.isSameEmployee(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_EMPLOYEE.isSameEmployee(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Employee editedBob = new EmployeeBuilder(BOB_EMPLOYEE).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB_EMPLOYEE.isSameEmployee(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EmployeeBuilder(BOB_EMPLOYEE).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB_EMPLOYEE.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new EmployeeBuilder(ALICE_EMPLOYEE).build();
        assertTrue(ALICE_EMPLOYEE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_EMPLOYEE.equals(ALICE_EMPLOYEE));

        // null -> returns false
        assertFalse(ALICE_EMPLOYEE.equals(null));

        // different type -> returns false
        assertFalse(ALICE_EMPLOYEE.equals(5));

        // different person -> returns false
        assertFalse(ALICE_EMPLOYEE.equals(BOB_EMPLOYEE));

        // different name -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different leaves -> return false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withLeaves(VALID_LEAVES_BOB).build();
        System.out.println(ALICE_EMPLOYEE.getLeaves() + " bob : " + VALID_LEAVES_BOB);
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different salary -> return false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withSalary(VALID_SALARY_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));

        // different job title -> return false
        editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withJobTitle(VALID_JOBTITLE_BOB).build();
        assertFalse(ALICE_EMPLOYEE.equals(editedAlice));
    }
}
