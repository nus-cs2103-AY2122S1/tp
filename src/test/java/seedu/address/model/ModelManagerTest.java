package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLIERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BOB;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.BOB_EMPLOYEE;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalReservation.BENSON_RESERVATION;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.customer.CustomerClassContainsKeywordsPredicate;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;
import seedu.address.testutil.RhrhBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Rhrh(), new Rhrh(modelManager.getRhrh()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRhrhFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setRhrhFilePath(Paths.get("new/address/book/file/path"));
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
    public void setRhrhFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRhrhFilePath(null));
    }

    @Test
    public void setRhrhFilePath_validPath_setsRhrhFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setRhrhFilePath(path);
        assertEquals(path, modelManager.getRhrhFilePath());
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
    public void hasReservation_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addReservation(null));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSupplier(null));
    }

    @Test
    public void hasCustomer_customerNotInRhrh_returnsFalse() {
        assertFalse(modelManager.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeNotInRhrh_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasReservation_reservationNotInRhrh_returnsFalse() {
        assertFalse(modelManager.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasSupplier_supplierNotInRhrh_returnsFalse() {
        assertFalse(modelManager.hasSupplier(AMY));
    }
    @Test
    public void hasCustomer_customerInRhrh_returnsTrue() {
        modelManager.addCustomer(CUSTOMER_ALICE);
        assertTrue(modelManager.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeInRhrh_returnsTrue() {
        modelManager.addEmployee(ALICE_EMPLOYEE);
        assertTrue(modelManager.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasReservation_reservationInRhrh_returnsTrue() {
        modelManager.addReservation(ALICE_RESERVATION);
        assertTrue(modelManager.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasSupplier_supplierInRhrh_returnsTrue() {
        modelManager.addSupplier(AMY);
        assertTrue(modelManager.hasSupplier(AMY));
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
    public void getFilteredReservationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredReservationList().remove(0));
    }

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredSupplierList().remove(0));
    }

    @Test
    public void equals() {
        Rhrh rhrh =
                new RhrhBuilder()
                        .withCustomer(CUSTOMER_ALICE).withCustomer(CUSTOMER_BOB)
                        .withEmployee(ALICE_EMPLOYEE).withEmployee(BOB_EMPLOYEE)
                        .withReservation(ALICE_RESERVATION).withReservation(BENSON_RESERVATION)
                        .withSupplier(AMY).withSupplier(BOB)
                        .build();

        Rhrh differentRhrh = new Rhrh();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(rhrh, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(rhrh, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different rhrh -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentRhrh, userPrefs)));

        // different filteredList -> returns false
        String[] custkeywords = CUSTOMER_ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCustomerList(
                new CustomerClassContainsKeywordsPredicate(Arrays.asList(custkeywords)));
        assertFalse(modelManager.equals(new ModelManager(rhrh, userPrefs)));

        // different filteredList for employees -> returns false
        String[] employeeKeywords = ALICE_EMPLOYEE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new EmployeeClassContainsKeywordsPredicate(
                Arrays.asList(employeeKeywords)));
        assertFalse(modelManager.equals(new ModelManager(rhrh, userPrefs)));

        // different filteredList for suppliers -> returns false
        String[] supplierKeywords = AMY.getName().fullName.split("\\s+");
        modelManager.updateFilteredSupplierList(
                new SupplierClassContainsKeywordsPredicate(Arrays.asList(supplierKeywords)));
        assertFalse(modelManager.equals(new ModelManager(rhrh, userPrefs)));

        // different filteredList for reservations -> returns false
        modelManager.updateFilteredReservationList(res -> res.equals(ALICE_RESERVATION));
        assertFalse(modelManager.equals(new ModelManager(rhrh, userPrefs)));

        modelManager.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        modelManager.updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setRhrhFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(rhrh, differentUserPrefs)));
    }
}
