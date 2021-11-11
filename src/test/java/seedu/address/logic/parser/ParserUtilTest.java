package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_BYTEDANCE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

public class ParserUtilTest {
    private static final String INVALID_COMPANY = "superlongcompanynamethatisover40characterslong";
    private static final String INVALID_POSITION = " *A&#4";
    private static final String INVALID_DEADLINE = "21421341";
    private static final String INVALID_DATE = "2012-02-30";
    private static final String INVALID_STATUS = "Maybe";
    private static final String INVALID_COMPLETION = "abc";
    private static final String INVALID_PRIORITY = "prioritised";
    private static final String INVALID_REQUIREMENT = "   ";
    private static final String INVALID_INTERVIEW_DATE_AND_TIME = "12-12-2012";
    private static final String INVALID_DATE_AND_TIME = "2021-02-30 1200";

    private static final String VALID_COMPANY = "Shopee";
    private static final String VALID_POSITION = "Software Engineer";
    private static final String VALID_DEADLINE = "2021-12-25";
    private static final String VALID_STATUS = "Pending";
    private static final String VALID_COMPLETION = "Uncompleted";
    private static final String VALID_PRIORITY = "High";

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
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsCompany() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedCompany() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(nameWithWhitespace));
    }

    @Test
    public void parsePosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePosition((String) null));
    }

    @Test
    public void parsePosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePosition(INVALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithoutWhitespace_returnsPosition() throws Exception {
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(VALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithWhitespace_returnsTrimmedPosition() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_POSITION + WHITESPACE;
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(phoneWithWhitespace));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline((String) null));
    }

    @Test
    public void parseDeadline_invalidDeadline_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DATE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus((String) null));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(nameWithWhitespace));
    }

    @Test
    public void parseCompletion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompletion((String) null));
    }

    @Test
    public void parseCompletion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompletion(INVALID_COMPLETION));
    }

    @Test
    public void parseCompletion_validValueWithoutWhitespace_returnsCompletion() throws Exception {
        Completion expectedCompletion = new Completion(VALID_COMPLETION);
        assertEquals(expectedCompletion, ParserUtil.parseCompletion(VALID_COMPLETION));
    }

    @Test
    public void parseCompletion_validValueWithWhitespace_returnsTrimmedCompletion() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPLETION + WHITESPACE;
        Completion expectedCompletion = new Completion(VALID_COMPLETION);
        assertEquals(expectedCompletion, ParserUtil.parseCompletion(nameWithWhitespace));
    }

    @Test
    public void parsePriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriority((String) null));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(VALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(nameWithWhitespace));
    }

    @Test
    public void parseRequirement_invalidRequirement_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRequirement(INVALID_REQUIREMENT));
    }

    @Test
    public void parseRequirements_returnsRequirementSet() throws ParseException {
        Collection<String> requirementSet = new HashSet<>();
        Set<Requirement> parsedRequirementSet = new HashSet<>();

        String[] requirements = new String[]{VALID_REQUIREMENTS_AMAZON, VALID_REQUIREMENTS_BYTEDANCE};
        Collections.addAll(requirementSet, requirements);
        for (String requirement: requirements) {
            parsedRequirementSet.add(new Requirement(requirement));
        }

        assertEquals(parsedRequirementSet, ParserUtil.parseRequirements(requirementSet));
    }

    @Test
    public void parseInterviewDateAndTime_invalidInterviewDateAndTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterviewDateAndTime(INVALID_INTERVIEW_DATE_AND_TIME));
    }

    @Test
    public void parseInterviewDateAndTime_invalidDateAndTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterviewDateAndTime(INVALID_DATE_AND_TIME));
    }

    @Test
    public void parseInterviewDateAndTimes_returnsInterviewDateAndTimeSet() throws ParseException {
        Collection<String> interviewTimeSet = new HashSet<>();
        Set<InterviewDateAndTime> parsedInterviewTimeSet = new HashSet<>();

        String[] interviewTimes =
                new String[]{VALID_INTERVIEW_DATE_AND_TIME_AMAZON, VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE};
        Collections.addAll(interviewTimeSet, interviewTimes);
        for (String interviewTime: interviewTimes) {
            parsedInterviewTimeSet.add(new InterviewDateAndTime(interviewTime));
        }

        assertEquals(parsedInterviewTimeSet, ParserUtil.parseInterviewDateAndTimes(interviewTimeSet));
    }

}
