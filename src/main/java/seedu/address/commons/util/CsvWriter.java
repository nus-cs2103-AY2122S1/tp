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

    private String[] headers;
    private Map<String, List<String>> data;

    /**
     * Writes data to Csv files.
     *
     * @param filePath path of file to be written to.
     * @param headers array of headers for the csv file.
     * @param data data to be written to the csv file.
     * @throws IOException when file cannot be created or written to.
     */
    public void write(String filePath, String[] headers, Map<String, List<String>> data) throws IOException {
        this.headers = headers;
        this.data = data;

        FileUtil.createFile(Paths.get(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        writeNewLine(bw, String.join(",", headers));

        for (int i = 0; i < data.get("name").size(); i++) {
            writeNewLine(bw, getRow(i));
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

    private void writeNewLine(BufferedWriter bw, String line) throws IOException {
        bw.write(line);
        bw.newLine();
        bw.flush();
    }

}
