package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.position.Position;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskID;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_POSITION = "#friend";
    private static final String INVALID_TASK_NAME = "";
    private static final String INVALID_TASK_ID = "abc";
    private static final String INVALID_MEMBER_ID = "abc";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_POSITION_1 = "friend";
    private static final String VALID_POSITION_2 = "neighbour";
    private static final String VALID_TASK_NAME = "write a poem";

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
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("  1  "));
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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parsePosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePosition(null));
    }

    @Test
    public void parsePosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePosition(INVALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithoutWhitespace_returnsPosition() throws Exception {
        Position expectedPosition = new Position(VALID_POSITION_1);
        assertEquals(expectedPosition, ParserUtil.parsePosition(VALID_POSITION_1));
    }

    @Test
    public void parsePosition_validValueWithWhitespace_returnsTrimmedPosition() throws Exception {
        String positionWithWhitespace = WHITESPACE + VALID_POSITION_1 + WHITESPACE;
        Position expectedPosition = new Position(VALID_POSITION_1);
        assertEquals(expectedPosition, ParserUtil.parsePosition(positionWithWhitespace));
    }

    @Test
    public void parsePositions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePositions(null));
    }

    @Test
    public void parsePositions_collectionWithInvalidPositions_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePositions(
                Arrays.asList(VALID_POSITION_1, INVALID_POSITION)));
    }

    @Test
    public void parsePositions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePositions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePositions_collectionWithValidPositions_returnsPositionSet() throws Exception {
        Set<Position> actualPositionSet = ParserUtil.parsePositions(Arrays.asList(VALID_POSITION_1, VALID_POSITION_2));
        Set<Position> expectedPositionSet = new HashSet<Position>(
                Arrays.asList(new Position(VALID_POSITION_1), new Position(VALID_POSITION_2)));

        assertEquals(expectedPositionSet, actualPositionSet);
    }

    @Test
    public void parseMemberID_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMemberID(INVALID_MEMBER_ID));
    }

    @Test
    public void parseMemberID_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MemberID.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseMemberID(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseMemberID_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(new MemberID("1"), ParserUtil.parseMemberID("1"));

        // Leading and trailing whitespaces
        assertEquals(new MemberID("1"), ParserUtil.parseMemberID("  1  "));
    }

    @Test
    public void parseTaskID_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskID(INVALID_TASK_ID));
    }

    @Test
    public void parseTaskID_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, TaskID.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseTaskID(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseTaskID_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(new TaskID("1"), ParserUtil.parseTaskID("1"));

        // Leading and trailing whitespaces
        assertEquals(new TaskID("1"), ParserUtil.parseTaskID("  1  "));
    }

    @Test
    public void parseTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTask((String) null));
    }

    @Test
    public void parsTask_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTask(INVALID_TASK_NAME));
    }

    @Test
    public void parseTask_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_TASK_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_TASK_NAME));
    }

    @Test
    public void parseTask_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TASK_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_TASK_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }
}
