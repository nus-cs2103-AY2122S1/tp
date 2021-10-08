package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "+651234";
    private static final String INVALID_CLASS_ID = "+65";
    private static final String INVALID_GRADE = "56";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_STUDENT_ID = "a0213221h";
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
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithWhitespace_returnsTrimmedStudentId() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedPhone = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedPhone, ParserUtil.parseStudentId(phoneWithWhitespace));
    }

    @Test
    public void parseClassId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassId((String) null));
    }


    @Test
    public void parseClassId_validValueWithoutWhitespace_returnsClassId() throws Exception {
        ClassId expectedAddress = new ClassId(VALID_CLASS_ID);
        assertEquals(expectedAddress, ParserUtil.parseClassId(VALID_CLASS_ID));
    }

    @Test
    public void parseClassId_validValueWithWhitespace_returnsTrimmedClassId() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_CLASS_ID + WHITESPACE;
        ClassId expectedAddress = new ClassId(VALID_CLASS_ID);
        assertEquals(expectedAddress, ParserUtil.parseClassId(addressWithWhitespace));
    }

    @Test
    public void parseGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGrade((String) null));
    }

    @Test
    public void parseGrade_validValueWithoutWhitespace_returnsGrade() throws Exception {
        Grade expectedEmail = new Grade(VALID_GRADE);
        assertEquals(expectedEmail, ParserUtil.parseGrade(VALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithWhitespace_returnsTrimmedGrade() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_GRADE + WHITESPACE;
        Grade expectedEmail = new Grade(VALID_GRADE);
        assertEquals(expectedEmail, ParserUtil.parseGrade(emailWithWhitespace));
    }
}
