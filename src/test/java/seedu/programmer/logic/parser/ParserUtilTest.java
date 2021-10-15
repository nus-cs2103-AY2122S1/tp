package seedu.programmer.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Grade;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.StudentId;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "+651234";
    private static final String INVALID_CLASS_ID = "+65";
    private static final String INVALID_GRADE = "56";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_STUDENT_ID = "A0214325H";
    private static final String VALID_CLASS_ID = "B01";
    private static final String VALID_GRADE = "A";


    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1L)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId(null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithWhitespace_returnsTrimmedStudentId() throws Exception {
        String studentIdWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(studentIdWithWhitespace));
    }

    @Test
    public void parseClassId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassId(null));
    }


    @Test
    public void parseClassId_validValueWithoutWhitespace_returnsClassId() throws Exception {
        ClassId expectedClassId = new ClassId(VALID_CLASS_ID);
        assertEquals(expectedClassId, ParserUtil.parseClassId(VALID_CLASS_ID));
    }

    @Test
    public void parseClassId_validValueWithWhitespace_returnsTrimmedClassId() throws Exception {
        String classIdWithWhitespace = WHITESPACE + VALID_CLASS_ID + WHITESPACE;
        ClassId expectedClassId = new ClassId(VALID_CLASS_ID);
        assertEquals(expectedClassId, ParserUtil.parseClassId(classIdWithWhitespace));
    }

    @Test
    public void parseGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGrade(null));
    }

    @Test
    public void parseGrade_validValueWithoutWhitespace_returnsGrade() throws Exception {
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(VALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithWhitespace_returnsTrimmedGrade() throws Exception {
        String gradeWithWhitespace = WHITESPACE + VALID_GRADE + WHITESPACE;
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(gradeWithWhitespace));
    }
}
