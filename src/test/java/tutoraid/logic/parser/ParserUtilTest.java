package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static tutoraid.testutil.Assert.assertThrows;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.Name;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.StudentName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";


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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseStudentName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentName((String) null));
    }

    @Test
    public void parseParentName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseParentName((String) null));
    }

    @Test
    public void parseStudentName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentName(INVALID_NAME));
    }

    @Test
    public void parseParentName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseParentName(INVALID_NAME));
    }

    @Test
    public void parseStudentName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new StudentName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseStudentName(VALID_NAME));
    }

    @Test
    public void parseParentName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new ParentName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseParentName(VALID_NAME));
    }

    @Test
    public void parseStudentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new StudentName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseStudentName(nameWithWhitespace));
    }

    @Test
    public void parseParentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new ParentName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseParentName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }



}
