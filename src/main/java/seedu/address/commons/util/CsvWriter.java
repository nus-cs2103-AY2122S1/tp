package seedu.address.commons.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Writes data to Csv files.
 */
public class CsvWriter {

    private final String filePath;
    private final String[] headers;
    private final Map<String, List<String>> data;

    /**
     * Default constructor.
     *
     * @param filePath path of file to be written to.
     * @param headers array of headers for the csv file.
     * @param data data to be written to the csv file.
     */
    public CsvWriter(String filePath, String[] headers, Map<String, List<String>> data) {
        this.filePath = filePath;
        this.headers = headers;
        this.data = data;
    }

    /**
     * Writes data to Csv files.
     *
     * @throws IOException when file cannot be created or written to.
     */
    public void write() throws IOException {
        FileUtil.createFile(Paths.get(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        bw.write(String.join(",", headers));
        bw.newLine();
        bw.flush();

        for (int i = 0; i < data.get("name").size(); i++) {
            bw.write(getRow(i));
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    private String get(String columnName, int i) {
        return data.get(columnName).get(i);
    }

    private String getRow(int i) {
        String[] row = new String[headers.length];
        for (int j = 0; j < headers.length; j++) {
            row[j] = get(headers[j], i);
        }

        return String.join(",", row);
    }

}
