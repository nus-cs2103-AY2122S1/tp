package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class CsvParserTest {
    private static final String TEST_DATA_FOLDER = "src/test/data/CSVParserTest/";
    private static final String ERROR_FORMAT = "Failed! Entries at following rows are wrongly formatted:"
            + System.lineSeparator()
            + "Row2 : ";

    @Test
    public void execute_emptyCsvFile_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "emptyCsv.csv"));

        assertThrows(ParseException.class,
                CsvParser.MESSAGE_CSV_FILE_IS_EMPTY, () -> parser.parse());
    }

    @Test
    public void execute_headerOnlyCsvFile_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "headerOnlyCsv.csv"));

        assertThrows(ParseException.class,
                CsvParser.MESSAGE_CSV_FILE_IS_EMPTY, () -> parser.parse());
    }

    @Test
    public void execute_tooManyColumns_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "tooManyColumnsCsv.csv"));

        assertThrows(ParseException.class,
                CsvParser.MESSAGE_TOO_MANY_COLUMNS, () -> parser.parse());
    }

    @Test
    public void execute_missingHeaders_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "missingColumnsCsv.csv"));

        assertThrows(ParseException.class,
                CsvParser.MESSAGE_MISSING_HEADERS, () -> parser.parse());
    }

    @Test
    public void execute_phoneNumberWronglyFormatted_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "phoneNumberWronglyFormattedCsv.csv"));


        assertThrows(ParseException.class,
                ERROR_FORMAT + Phone.MESSAGE_CONSTRAINTS, () -> parser.parse());
    }

    @Test
    public void execute_emailWronglyFormatted_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "emailWronglyFormattedCsv.csv"));

        assertThrows(ParseException.class,
                ERROR_FORMAT + Email.MESSAGE_CONSTRAINTS, () -> parser.parse());
    }

    @Test
    public void execute_addressWronglyFormatted_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "addressWronglyFormattedCsv.csv"));

        assertThrows(ParseException.class,
                ERROR_FORMAT + Address.MESSAGE_CONSTRAINTS, () -> parser.parse());
    }

    @Test
    public void execute_tagsWronglyFormatted_throwsParseException() throws Exception {
        CsvParser parser = new CsvParser(new File(TEST_DATA_FOLDER + "tagsWronglyFormattedCsv.csv"));

        assertThrows(ParseException.class,
                ERROR_FORMAT + Tag.MESSAGE_CONSTRAINTS, () -> parser.parse());
    }
}
