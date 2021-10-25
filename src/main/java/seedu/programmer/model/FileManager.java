package seedu.programmer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opencsv.CSVReader;

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

public class FileManager {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Gets a List of Students from CSV file of student data.
     *
     * @param chosenFile file chosen by user
     * @return List of Students in the CSV file
     * @throws IllegalArgumentException if CSV contains invalid input
     * @throws IOException if error reading the file
     */
    public List<Student> getStudentsFromCsv(File chosenFile) throws IllegalArgumentException, IOException {
        List<Student> stuList = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(chosenFile));
        String[] headers = reader.readNext();
        int numberOfFields = 4;
        if (headers.length != numberOfFields) {
            return null;
        }

        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            addStudentFromCsvLine(stuList, nextLine);
        }

        return stuList;
    }

    /**
     * Adds a Student to a List of Students.
     *
     * @param stuList Student list to add to
     * @param nextLine line in a CSV file of student data
     */
    private void addStudentFromCsvLine(List<Student> stuList, String[] nextLine) {
        StudentId sid = new StudentId(nextLine[0]);
        ClassId cid = new ClassId(nextLine[1]);
        Name name = new Name(nextLine[2]);
        Email email = new Email(nextLine[3]);
        Student s = new Student(name, sid, cid, email);
        stuList.add(s);
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param jsonData JSONArray of data
     * @param destinationFile File object to write to
     */
    public void writeJsonToCsv(JSONArray jsonData, File destinationFile) {
        // If there were no data, we should not even be trying to write anything
        assert (jsonData.length() > 0);
        try {
            String csv = CDL.toString(jsonData);
            FileUtils.writeStringToFile(destinationFile, csv, Charset.defaultCharset());
            logger.info("The following data was written:\n" + csv);
        } catch (IOException | JSONException e) {
            logger.severe("Unexpected error: " + e);
        }
    }

    /**
     * Retrieves students' JSON data stored in ProgrammerError.
     *
     * @return JSONArray of student's data
     */
    public JSONArray getJsonData() {
        String resourceName = "data/programmerError.json";
        try {
            InputStream is = new FileInputStream(resourceName);
            String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(jsonTxt);
            return json.getJSONArray("students");
        } catch (IOException | JSONException e) {
            logger.severe("Error with the file!");
            return null;
        }
    }
}
