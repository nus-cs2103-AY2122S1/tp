package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Rhrh;
import seedu.address.testutil.TypicalCustomers;
import seedu.address.testutil.TypicalEmployees;
import seedu.address.testutil.TypicalSuppliers;

public class JsonSerializableRhrhTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableRhrhTest");

    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve(
            "typicalCustomersRhrh.json");
    private static final Path TYPICAL_EMPLOYEES_FILE = TEST_DATA_FOLDER.resolve("typicalEmployeesRhrh.json");
    private static final Path TYPICAL_SUPPLIERS_FILE = TEST_DATA_FOLDER.resolve("typicalSuppliersRhrh.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve(
            "invalidCustomerRhrh.json");
    private static final Path INVALID_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("invalidEmployeeRhrh.json");
    private static final Path INVALID_SUPPLIER_FILE = TEST_DATA_FOLDER.resolve("invalidSupplierRhrh.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateCustomerRhrh.json");
    private static final Path DUPLICATE_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("duplicateEmployeeRhrh.json");
    private static final Path DUPLICATE_SUPPLIER_FILE = TEST_DATA_FOLDER.resolve("duplicateSupplierRhrh.json");

    @Test
    public void toModelType_typicalCustomersFile_success() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableRhrh.class).get();
        Rhrh rhrhFromFile = dataFromFile.toModelType();
        Rhrh typicalCustomersRhrh = TypicalCustomers.getTypicalRhrhCustomers();
        assertEquals(rhrhFromFile, typicalCustomersRhrh);
    }

    @Test
    public void toModelType_typicalEmployeesFile_success() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(TYPICAL_EMPLOYEES_FILE,
                JsonSerializableRhrh.class).get();
        Rhrh rhrhFromFile = dataFromFile.toModelType();
        Rhrh typicalPersonsRhrh = TypicalEmployees.getTypicalRhrhEmployees();
        assertEquals(rhrhFromFile, typicalPersonsRhrh);
    }

    @Test
    public void toModelType_typicalSuppliersFile_success() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(TYPICAL_SUPPLIERS_FILE,
                JsonSerializableRhrh.class).get();
        Rhrh rhrhFromFile = dataFromFile.toModelType();
        Rhrh typicalSuppliersRhrh = TypicalSuppliers.getTypicalRhrhSuppliers();
        assertEquals(rhrhFromFile, typicalSuppliersRhrh);
    }

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidEmployeeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(INVALID_EMPLOYEE_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidSupplierFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(INVALID_SUPPLIER_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCustomers_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRhrh.MESSAGE_DUPLICATE_CUSTOMER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployees_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRhrh.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSuppliers_throwsIllegalValueException() throws Exception {
        JsonSerializableRhrh dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SUPPLIER_FILE,
                JsonSerializableRhrh.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRhrh.MESSAGE_DUPLICATE_SUPPLIER,
                dataFromFile::toModelType);
    }

}
