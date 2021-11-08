package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_HOON;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_IDA;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.HOON_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.IDA_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.getTypicalRhrhEmployees;
import static seedu.address.testutil.TypicalSuppliers.CHETWIN;
import static seedu.address.testutil.TypicalSuppliers.HOON_SUPPLIER;
import static seedu.address.testutil.TypicalSuppliers.IDA_SUPPLIER;
import static seedu.address.testutil.TypicalSuppliers.getTypicalRhrhSuppliers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.Rhrh;

public class JsonRhrhStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRhrhStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRhrh_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRhrh(null));
    }

    private java.util.Optional<ReadOnlyRhrh> readRhrh(String filePath) throws Exception {
        return new JsonRhrhStorage(Paths.get(filePath)).readRhrh(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRhrh("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRhrh("notJsonFormatRhrh.json"));
    }

    @Test
    public void readRhrh_invalidPersonRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidCustomerRhrh.json"));
    }

    @Test
    public void readRhrh_invalidEmployeeRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidEmployeeRhrh.json"));
    }

    @Test
    public void readRhrh_invalidSupplierRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidSupplierRhrh.json"));
    }

    @Test
    public void readRhrh_invalidAndValidPersonRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidAndValidCustomerRhrh.json"));
    }

    @Test
    public void readRhrh_invalidAndValidEmployeeRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidAndValidEmployeeRhrh.json"));
    }

    @Test
    public void readRhrh_invalidAndValidSupplierRhrh_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRhrh("invalidAndValidSupplierRhrh.json"));
    }

    @Test
    public void readAndSaveRhrh_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRhrh.json");
        Rhrh originalCustomer = getTypicalRhrhCustomers();
        Rhrh originalEmployee = getTypicalRhrhEmployees();
        Rhrh originalSuppliers = getTypicalRhrhSuppliers();
        JsonRhrhStorage jsonRhrhStorage = new JsonRhrhStorage(filePath);

        // Save in new file and read back
        jsonRhrhStorage.saveRhrh(originalCustomer, filePath);
        ReadOnlyRhrh readBack = jsonRhrhStorage.readRhrh(filePath).get();
        assertEquals(originalCustomer, new Rhrh(readBack));

        // Modify data, overwrite exiting file, and read back
        originalCustomer.addCustomer(CUSTOMER_HOON);
        originalCustomer.removeCustomer(CUSTOMER_ALICE);
        jsonRhrhStorage.saveRhrh(originalCustomer, filePath);
        readBack = jsonRhrhStorage.readRhrh(filePath).get();
        assertEquals(originalCustomer, new Rhrh(readBack));

        // Modify data, overwrite exiting file, and read back Employees test
        originalEmployee.addEmployee(HOON_EMPLOYEE);
        originalEmployee.removeEmployee(ALICE_EMPLOYEE);
        jsonRhrhStorage.saveRhrh(originalEmployee, filePath);
        readBack = jsonRhrhStorage.readRhrh(filePath).get();
        assertEquals(originalEmployee, new Rhrh(readBack));


        // Modify data, overwrite exiting file, and read back Suppliers test
        originalSuppliers.addSupplier(HOON_SUPPLIER);
        originalSuppliers.removeSupplier(CHETWIN);
        jsonRhrhStorage.saveRhrh(originalSuppliers, filePath);
        readBack = jsonRhrhStorage.readRhrh(filePath).get();
        assertEquals(originalSuppliers, new Rhrh(readBack));

        // Save and read without specifying file path
        originalCustomer.addCustomer(CUSTOMER_IDA);
        jsonRhrhStorage.saveRhrh(originalCustomer); // file path not specified
        readBack = jsonRhrhStorage.readRhrh().get(); // file path not specified
        assertEquals(originalCustomer, new Rhrh(readBack));

        // Save and read without specifying file path Employees test
        originalEmployee.addEmployee(IDA_EMPLOYEE);
        jsonRhrhStorage.saveRhrh(originalEmployee);
        readBack = jsonRhrhStorage.readRhrh().get();
        assertEquals(originalEmployee, new Rhrh(readBack));

        // Save and read without specifying file path Employees test
        originalSuppliers.addSupplier(IDA_SUPPLIER);
        jsonRhrhStorage.saveRhrh(originalSuppliers);
        readBack = jsonRhrhStorage.readRhrh().get();
        assertEquals(originalSuppliers, new Rhrh(readBack));
    }

    @Test
    public void saveRhrh_nullRhrh_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRhrh(null, "SomeFile.json"));
    }

    /**
     * Saves {@code rhrh} at the specified {@code filePath}.
     */
    private void saveRhrh(ReadOnlyRhrh rhrh, String filePath) {
        try {
            new JsonRhrhStorage(Paths.get(filePath))
                    .saveRhrh(rhrh, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRhrh_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRhrh(new Rhrh(), null));
    }
}
