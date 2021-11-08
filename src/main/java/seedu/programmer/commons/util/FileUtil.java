package seedu.programmer.commons.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.CSVReader;

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * Writes and reads files.
 */
public class FileUtil {

    private static final Logger logger = LogsCenter.getLogger(FileUtil.class);
    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file File to create if it is missing.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file File to create.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory.
     *
     * @param file File to create parent directories of.
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists.
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     *
     * @param file File to write to.
     * @param content String content to write to file.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Gets a List of Students from CSV file of student data.
     *
     * @param chosenFile File chosen by user.
     * @return List of Students in the CSV file.
     * @throws IllegalArgumentException If CSV contains invalid input.
     * @throws IOException If error reading the file.
     */
    public static List<Student> getStudentsFromCsv(File chosenFile) throws IllegalArgumentException, IOException,
            IllegalValueException {
        List<Student> stuList = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(chosenFile));
        String[] headers = reader.readNext();
        String[] expectedHeaders = {"studentId", "classId", "name", "email"};
        if (!Arrays.equals(headers, expectedHeaders)) {
            throw new IllegalValueException("Sorry! Your CSV file header should be: `studentId,classId,name,email`");
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
     * @param stuList Student list to add to.
     * @param nextLine Line in a CSV file of student data.
     */
    private static void addStudentFromCsvLine(List<Student> stuList, String[] nextLine) {
        StudentId sid = new StudentId(nextLine[0].trim());
        ClassId cid = new ClassId(nextLine[1].trim());
        Name name = new Name(nextLine[2].trim());
        Email email = new Email(nextLine[3].trim());
        Student s = new Student(name, sid, cid, email);
        stuList.add(s);
    }
}
