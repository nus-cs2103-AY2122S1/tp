package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static tutoraid.testutil.Assert.assertThrows;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Timing;
import tutoraid.model.student.Name;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.StudentName;

public class ParserUtilTest {
    private static final String INVALID_STUDENT_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_STUDENT_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";

    private static final String INVALID_LESSON_NAME = "Maths&";
    private static final String INVALID_CAPACITY = "911.1";
    private static final String INVALID_PRICE = "911.123";

    private static final String VALID_LESSON_NAME = "Maths 2";
    private static final String VALID_CAPACITY = "40";
    private static final String VALID_PRICE = "130";
    private static final String VALID_TIMING = "1000-1200";

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
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("  1  "));
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
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentName(INVALID_STUDENT_NAME));
    }

    @Test
    public void parseParentName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseParentName(INVALID_STUDENT_NAME));
    }

    @Test
    public void parseStudentName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new StudentName(VALID_STUDENT_NAME);
        assertEquals(expectedName, ParserUtil.parseStudentName(VALID_STUDENT_NAME));
    }

    @Test
    public void parseParentName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new ParentName(VALID_STUDENT_NAME);
        assertEquals(expectedName, ParserUtil.parseParentName(VALID_STUDENT_NAME));
    }

    @Test
    public void parseStudentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_STUDENT_NAME + WHITESPACE;
        Name expectedName = new StudentName(VALID_STUDENT_NAME);
        assertEquals(expectedName, ParserUtil.parseStudentName(nameWithWhitespace));
    }

    @Test
    public void parseParentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_STUDENT_NAME + WHITESPACE;
        Name expectedName = new ParentName(VALID_STUDENT_NAME);
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

    @Test
    public void parseLessonName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLessonName((String) null));
    }

    @Test
    public void parseLessonName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLessonName(INVALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithoutWhitespace_returnsLessonName() throws Exception {
        LessonName expectedLessonName = new LessonName(VALID_LESSON_NAME);
        assertEquals(expectedLessonName, ParserUtil.parseLessonName(VALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithWhitespace_returnsLessonName() throws Exception {
        String lessonNameWithWhitespace = WHITESPACE + VALID_LESSON_NAME + WHITESPACE;
        LessonName expectedLessonName = new LessonName(VALID_LESSON_NAME);
        assertEquals(expectedLessonName, ParserUtil.parseLessonName(lessonNameWithWhitespace));
    }

    @Test
    public void parseCapacity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCapacity((String) null));
    }

    @Test
    public void parseCapacity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCapacity(INVALID_CAPACITY));
    }

    @Test
    public void parseCapacity_validValueWithoutWhitespace_returnsCapacity() throws Exception {
        Capacity expectedCapacity = new Capacity(VALID_CAPACITY);
        assertEquals(expectedCapacity, ParserUtil.parseCapacity(VALID_CAPACITY));
    }

    @Test
    public void parseCapacity_validValueWithWhitespace_returnsCapacity() throws Exception {
        String capacityWithWhitespace = WHITESPACE + VALID_CAPACITY + WHITESPACE;
        Capacity expectedCapacity = new Capacity(VALID_CAPACITY);
        assertEquals(expectedCapacity, ParserUtil.parseCapacity(capacityWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice((String) null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseTiming_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTiming((String) null));
    }

    @Test
    public void parseTiming_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Timing expectedTiming = new Timing(VALID_TIMING);
        assertEquals(expectedTiming, ParserUtil.parseTiming(VALID_TIMING));
    }

    @Test
    public void parseTiming_validValueWithWhitespace_returnsTiming() throws Exception {
        String timingWithWhitespace = WHITESPACE + VALID_TIMING + WHITESPACE;
        Timing expectedTiming = new Timing(VALID_TIMING);
        assertEquals(expectedTiming, ParserUtil.parseTiming(timingWithWhitespace));
    }
}
