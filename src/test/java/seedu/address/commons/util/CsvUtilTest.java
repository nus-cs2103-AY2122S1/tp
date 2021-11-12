package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.storage.CsvAdaptedPerson;
import seedu.address.testutil.TestUtil;

class CsvUtilTest {

    @Test
    public void createPerson_invalidCsvString_emptyOptional() {
        String invalidCsvString = "Name;;;";
        assertEquals(Optional.empty(), CsvUtil.createPerson(invalidCsvString, 0));
    }

    @Test
    public void createPerson_rowMissingDelimiter_dataConversionExceptionThrown() {
        String rowMissingDelimiter = "name1;123456789;email@email.comTrue";
        int rowNumber = 0;
        assertEquals(Optional.empty(), CsvUtil.createPerson(rowMissingDelimiter, rowNumber));
    }

    @Test
    public void fromCsvString_mixOfValidAndInvalidPeople_peopleList() throws DataConversionException {
        String[] validHeader = CsvAdaptedPerson.headerOrder();
        String header = String.join(";", validHeader) + "\n";
        String validPerson = "name 3;98765432;email@email.com;;;;;TRUE\n";
        String invalidPerson = ";123111222;;\n";
        String csvString = header + validPerson + invalidPerson;
        assertEquals(1, CsvUtil.fromCsvString(csvString).size());
    }

    @Test
    public void readCsvFile_invalidFilePath_emptyOptional() throws DataConversionException {
        assertEquals(Optional.empty(),
                CsvUtil.readCsvFile(TestUtil.getFilePathInSandboxFolder("doesNotExist.csv")));
    }

    @Test
    public void deserializeObjectFromCsvFile_wrongFile_ioException() {
        assertThrows(IOException.class, () ->
            CsvUtil.deserializeObjectFromCsvFile(TestUtil.getFilePathInSandboxFolder("doesNotExist.csv")));
    }

    @Test
    public void checkValidHeader_wrongDelimiter_dataConversionExceptionThrown() {
        String[] validHeader = CsvAdaptedPerson.headerOrder();
        String header = String.join(",", validHeader) + "\n";
        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeader(header));
    }

    @Test
    public void checkValidHeader_missingHeader_dataConversionExceptionThrown() {
        String header = "Name;Phone;Email";
        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeader(header));
    }

    @Test
    public void checkValidHeader_wrongHeader_dataConversionExceptionThrown() {
        String header = "Name;Phone;Email;Called";
        assertThrows(DataConversionException.class, () -> CsvUtil.checkValidHeader(header));
    }

}
