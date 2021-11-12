package seedu.teachbook.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.teachbook.logic.commands.PrintCommand.generateColumn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.teachbook.model.student.Student;
import seedu.teachbook.testutil.TypicalClasses;


public class ExcelUtilTest {
    private static final List<Student> studentList = TypicalClasses.getAClass().getStudentsOfThisClass();


    @Test
    public void isPrinted() throws IOException {
        List<List<String>> toPrint = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();
        List<String> columnList = new ArrayList<>();

        studentNames.add("Name");
        for (Student s : studentList) {
            studentNames.add(s.getName().fullName);
        }

        toPrint.add(studentNames);
        for (String columnName : columnList) {
            toPrint.add(generateColumn(columnName, studentList));
        }

        String filePath = null;
        try {
            filePath = ExcelUtil.toExcel(toPrint);
            assertFalse(filePath == null);
        } catch (IOException e) {
            assertThrows(FileNotFoundException.class , () -> ExcelUtil.toExcel(toPrint));
        }

    }

    @Test
    public void isPrintedWithColumns() {
        List<List<String>> toPrint = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();
        List<String> columnList =
                new ArrayList<>(Arrays.asList("class", "phone", "email",
                        "address", "tags", "remark", "attendance", "grade"));

        studentNames.add("Name");
        for (Student s : studentList) {
            studentNames.add(s.getName().fullName);
        }

        toPrint.add(studentNames);
        for (String columnName : columnList) {
            toPrint.add(generateColumn(columnName, studentList));
        }

        String filePath = null;
        try {
            filePath = ExcelUtil.toExcel(toPrint);
            assertFalse(filePath == null);
        } catch (IOException e) {
            assertThrows(FileNotFoundException.class , () -> ExcelUtil.toExcel(toPrint));
        }
    }
}
