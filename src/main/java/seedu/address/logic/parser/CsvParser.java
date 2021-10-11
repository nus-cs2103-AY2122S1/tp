package seedu.address.logic.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CsvFileChooser;

/**
 * Parses CSV files for import function.
 */
public class CsvParser {
    public static final String MESSAGE_CSV_FILE_IS_EMPTY = "Failed!\nCsv file is empty";
    public static final String MESSAGE_FILE_UNREADABLE = "File could not be read";

    private final BufferedReader br;
    private final Map<String, List<String>> data;
    private String[] inputtedHeaders;

    /**
     * Constructs instance of CsvParser and parses the provided file.
     *
     * @param csvFileChooser csvFileChooser to choose csv file to be parsed.
     * @throws ParseException if csv file is empty or cannot be read.
     */
    public CsvParser(CsvFileChooser csvFileChooser) throws ParseException {
        try {
            br = new BufferedReader(new FileReader(csvFileChooser.chooseFile("docs/assets/templates")));
            data = new HashMap<>();
            parse();
        } catch (IOException e) {
            throw new ParseException(MESSAGE_FILE_UNREADABLE);
        }
    }

    protected void parse() throws ParseException, IOException {
        parseHeader();
        parseColumns();
    }

    private void parseHeader() throws IOException, ParseException {
        String headerRow = br.readLine();

        if (headerRow == null) {
            throw new ParseException(MESSAGE_CSV_FILE_IS_EMPTY);
        }

        inputtedHeaders = headerRow.split(",");

        for (String header: inputtedHeaders) {
            data.put(header, new ArrayList<>());
        }

    }

    private void parseColumns() throws IOException {
        String line;

        // @@author Scott Robinson-reused
        // Reused regex from https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/
        String regex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        while ((line = br.readLine()) != null) {
            String[] values = line.split(regex);

            for (int i = 0; i < values.length; i++) {
                String entry = values[i];
                entry = entry.replaceAll("\"", "");

                data.get(inputtedHeaders[i]).add(entry);
            }

            // Case whereby final column was left blank
            if (values.length == inputtedHeaders.length - 1) {
                data.get(inputtedHeaders[inputtedHeaders.length - 1]).add("");
            }

        }
    }

    /**
     * Provides the number of rows of data in the file.
     *
     * @return Number of rows of data in file.
     */
    public int size() {
        List<String> column = data.get(inputtedHeaders[0]);

        if (column == null) {
            return 0;
        }

        return column.size();
    }

    /**
     * Gets the data stored under the specified column.
     *
     * @param columnName String corresponding to the header of the column.
     * @return Data stored in that column or null if column does not exist.
     */
    public List<String> get(String columnName) {
        return data.get(columnName);
    }

}
