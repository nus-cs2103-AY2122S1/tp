package seedu.address.logic.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CsvFileSelector;
import seedu.address.ui.exceptions.FileSelectorException;

/**
 * Parses CSV files for import function.
 */
public class CsvParser {
    public static final String MESSAGE_CSV_FILE_IS_EMPTY = "Failed!"
            + System.lineSeparator()
            + "Csv file is empty";
    public static final String MESSAGE_CSV_FILE_MISSING_HEADERS = "Failed!"
            + System.lineSeparator()
            + "Csv file has no headers";
    public static final String MESSAGE_FILE_UNREADABLE = "File could not be read";

    private final Map<String, List<String>> data;
    private BufferedReader br;
    private String[] inputtedHeaders;

    /**
     * Constructs instance of CsvParser
     */
    public CsvParser() {
        data = new HashMap<>();
    }

    /**
     * Parses file selected with fileSelector.
     *
     * @param fileSelector CsvFileSelector to select file to parse.
     * @throws ParseException When file is not selected or empty.
     */
    public void parse(CsvFileSelector fileSelector) throws ParseException {
        try {
            br = new BufferedReader(new FileReader(fileSelector.selectFile()));
            parseHeader();
            parseColumns();
        } catch (IOException e) {
            throw new ParseException(MESSAGE_FILE_UNREADABLE);
        } catch (FileSelectorException e) {
            throw new ParseException(e.getMessage());
        }
    }

    private void parseHeader() throws IOException, ParseException {
        String headerRow = br.readLine();
        if (headerRow == null) {
            throw new ParseException(MESSAGE_CSV_FILE_IS_EMPTY);
        }

        inputtedHeaders = headerRow.split(",");
        if (inputtedHeaders.length == 0) {
            throw new ParseException(MESSAGE_CSV_FILE_MISSING_HEADERS);
        }

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

            // Fill up blank columns
            if (values.length < inputtedHeaders.length) {
                for (int i = values.length; i < inputtedHeaders.length; i++) {
                    data.get(inputtedHeaders[i]).add("");
                }
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
