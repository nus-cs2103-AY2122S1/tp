package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TypicalPersons;
import seedu.address.ui.CsvFileSelector;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class CsvParserTest {

    @Test
    public void execute_emptyCsvFile_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser();
        assertThrows(ParseException.class,
                CsvParser.MESSAGE_CSV_FILE_IS_EMPTY,
                () -> parser.parse(new CsvFileSelectorStubBypassesUi("emptyCsv.csv")));
    }

    @Test
    public void execute_sizeOfOnlyHeaderCsvFile_success() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("headerOnlyCsv.csv"));
        assertEquals(parser.size(), 0);
    }

    @Test
    public void execute_size_success() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("validCsv.csv"));
        assertEquals(parser.size(), 7);
    }

    @Test
    public void execute_getNonExistentHeader_returnsNull() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("validCsv.csv"));
        assertEquals(parser.get("test"), null);
    }

    @Test
    public void execute_getEmptyString_returnsNull() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("validCsv.csv"));
        assertEquals(parser.get(""), null);
    }

    @Test
    public void execute_getNull_returnsNull() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("validCsv.csv"));
        assertEquals(parser.get(null), null);
    }

    @Test
    public void execute_getValidColumn_success() throws Exception {
        CsvParser parser = new CsvParser();
        parser.parse(new CsvFileSelectorStubBypassesUi("validCsv.csv"));
        assertEquals(parser.get("name"), TypicalPersons.getTypicalNamesStringForm());
    }

    private class CsvFileSelectorStubBypassesUi extends CsvFileSelector {
        private String path = "src"
                + File.separator
                + "test"
                + File.separator
                + "data"
                + File.separator
                + "CSVParserTest"
                + File.separator;

        public CsvFileSelectorStubBypassesUi(String file) {
            path += file;
        }

        @Override
        public File selectFile() {
            return new File(path);
        }

    }
}


