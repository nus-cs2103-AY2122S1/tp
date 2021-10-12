package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.BOB_EMPLOYEE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateEmployeeException;
import seedu.address.model.person.exceptions.EmployeeNotFoundException;
import seedu.address.testutil.EmployeeBuilder;

public class UniqueEmployeeListTest {

    private final UniqueEmployeeList uniqueEmployeeList = new UniqueEmployeeList();

    @Test
    public void contains_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.contains(null));
    }

    @Test
    public void contains_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.contains(ALICE_EMPLOYEE));
    }

    @Test
    public void contains_employeeInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        assertTrue(uniqueEmployeeList.contains(ALICE_EMPLOYEE));
    }

    @Test
    public void contains_employeeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEmployeeList.contains(editedAlice));
    }

    @Test
    public void add_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.add(null));
    }

    @Test
    public void add_duplicateEmployee_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(ALICE_EMPLOYEE));
    }

    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(null, ALICE_EMPLOYEE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEmployeeList.setEmployee(ALICE_EMPLOYEE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () ->
                uniqueEmployeeList.setEmployee(ALICE_EMPLOYEE, ALICE_EMPLOYEE));
    }

    @Test
    public void setEmployee_editedEmployeeIsSameEmployee_success() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        uniqueEmployeeList.setEmployee(ALICE_EMPLOYEE, ALICE_EMPLOYEE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE_EMPLOYEE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasSameIdentity_success() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueEmployeeList.setEmployee(ALICE_EMPLOYEE, editedAlice);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(editedAlice);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasDifferentIdentity_success() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        uniqueEmployeeList.setEmployee(ALICE_EMPLOYEE, BOB_EMPLOYEE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB_EMPLOYEE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasNonUniqueIdentity_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        uniqueEmployeeList.add(BOB_EMPLOYEE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList
                .setEmployee(ALICE_EMPLOYEE, BOB_EMPLOYEE));
    }

    @Test
    public void remove_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.remove(null));
    }

    @Test
    public void remove_employeeDoesNotExist_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.remove(ALICE_EMPLOYEE));
    }

    @Test
    public void remove_existingEmployee_removesEmployee() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        uniqueEmployeeList.remove(ALICE_EMPLOYEE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nulluniqueEmployeeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((UniqueEmployeeList) null));
    }

    @Test
    public void setEmployees_uniqueEmployeeList_replacesOwnListWithProvidedUniqueEmployeeList() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB_EMPLOYEE);
        uniqueEmployeeList.setEmployees(expectedUniqueEmployeeList);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((List<Employee>) null));
    }

    @Test
    public void setEmployees_list_replacesOwnListWithProvidedList() {
        uniqueEmployeeList.add(ALICE_EMPLOYEE);
        List<Employee> employeeList = Collections.singletonList(BOB_EMPLOYEE);
        uniqueEmployeeList.setEmployees(employeeList);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB_EMPLOYEE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_listWithDuplicateEmployees_throwsDuplicateEmployeeException() {
        List<Employee> listWithDuplicateEmployees = Arrays.asList(ALICE_EMPLOYEE, ALICE_EMPLOYEE);
        assertThrows(DuplicateEmployeeException.class, () ->
                uniqueEmployeeList.setEmployees(listWithDuplicateEmployees));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueEmployeeList.asUnmodifiableObservableList().remove(0));
    }
}
