package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

public class CsvWriterTest {
    public static final String TESTED_FILE_PATH = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "CsvWriterTest"
            + File.separator
            + "testedCsv.csv";

    public static final String EXPECTED_FILE_PATH_ALL_FIELDS = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "CsvWriterTest"
            + File.separator
            + "expectedCsvAllFields.csv";

    private Map<String, List<String>> data;
    private final String[] fieldHeaders = new String[]{"name", "phone", "email", "address", "tags"};

    @Test
    public void write_allColumns_success() throws Exception {
        data = new HashMap<>();
        data.put("name", TypicalPersons.getTypicalNamesStringForm());
        data.put("phone", TypicalPersons.getTypicalPhonesStringForm());
        data.put("email", TypicalPersons.getTypicalEmailsStringForm());
        data.put("address", TypicalPersons.getTypicalAddressesStringFormWithDoubleQuoutes());
        data.put("tags", TypicalPersons.getTypicalTagsStringForm());

        CsvWriter writer = new CsvWriter();
        writer.write(TESTED_FILE_PATH, fieldHeaders, data);
        assertTrue(FileUtil.areFilesEqual(Paths.get(TESTED_FILE_PATH), Paths.get(EXPECTED_FILE_PATH_ALL_FIELDS)));
        Files.deleteIfExists(Paths.get(TESTED_FILE_PATH));
    }

}
