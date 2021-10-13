package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLIERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BOB;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.BOB_EMPLOYEE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.customer.CustomerNameContainsKeywordsPredicate;
import seedu.address.model.person.employee.EmployeeNameContainsKeywordsPredicate;
import seedu.address.model.person.supplier.SupplierNameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCustomer(null));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmployee(null));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSupplier(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_supplierNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasSupplier(AMY));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        modelManager.addCustomer(CUSTOMER_ALICE);
        assertTrue(modelManager.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeInAddressBook_returnsTrue() {
        modelManager.addEmployee(ALICE_EMPLOYEE);
        assertTrue(modelManager.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_supplierInAddressBook_returnsTrue() {
        modelManager.addSupplier(AMY);
        assertTrue(modelManager.hasSupplier(AMY));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredCustomerList().remove(0));
    }

    @Test
    public void getFilteredEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredEmployeeList().remove(0));
    }

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredSupplierList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook =
                new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON)
                        .withCustomer(CUSTOMER_ALICE).withCustomer(CUSTOMER_BOB)
                        .withEmployee(ALICE_EMPLOYEE).withEmployee(BOB_EMPLOYEE)
                        .withSupplier(AMY).withSupplier(BOB)
                        .build();

        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredList -> returns false
        String[] custkeywords = CUSTOMER_ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCustomerList(new CustomerNameContainsKeywordsPredicate(Arrays.asList(custkeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredList for employees -> returns false
        String[] employeeKeywords = ALICE_EMPLOYEE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new EmployeeNameContainsKeywordsPredicate(
                Arrays.asList(employeeKeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredList for suppliers -> returns false
        String[] supplierKeywords = AMY.getName().fullName.split("\\s+");
        modelManager.updateFilteredSupplierList(
                new SupplierNameContainsKeywordsPredicate(Arrays.asList(supplierKeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        modelManager.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        modelManager.updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
