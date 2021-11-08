package seedu.smartnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_FALSE;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_TRUE;
import static seedu.smartnus.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_IMPORTANCE = "+2";
    private static final String INVALID_NAME = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_CHOICE = " ";
    private static final String INVALID_TRUE_FALSE_ANSWER = "5";
    private static final String INVALID_LIMIT_1 = " ";
    private static final String INVALID_LIMIT_2 = "-2";
    private static final String INVALID_LIMIT_3 = "a3";

    private static final String VALID_IMPORTANCE = "1";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_ANSWER = "2";
    private static final String VALID_CHOICE_1 = "3";
    private static final String VALID_CHOICE_2 = "4";
    private static final String VALID_CHOICE_3 = "1";
    private static final String VALID_LIMIT = "1";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_QUESTION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_QUESTION, ParserUtil.parseIndex("  1  "));
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
    public void parseImportance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseImportance(null));
    }

    @Test
    public void parseImportance_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance(INVALID_IMPORTANCE));
    }

    @Test
    public void parseImportance_validValueWithoutWhitespace_returnsImportance() throws Exception {
        Importance expectedImportance = new Importance(VALID_IMPORTANCE);
        assertEquals(expectedImportance, ParserUtil.parseImportance(VALID_IMPORTANCE));
    }

    @Test
    public void parseImportance_validValueWithWhitespace_returnsTrimmedImportance() throws Exception {
        String importanceWithWhitespace = WHITESPACE + VALID_IMPORTANCE + WHITESPACE;
        Importance expectedImportance = new Importance(VALID_IMPORTANCE);
        assertEquals(expectedImportance, ParserUtil.parseImportance(importanceWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseChoice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseChoice(null, true));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseChoice(null, false));
    }

    @Test
    public void parseChoice_validValueWithWhitespace_returnsOption() throws Exception {
        Choice exceptedChoice = new Choice(VALID_CHOICE_1, false);
        Choice actualChoice = ParserUtil.parseChoice(WHITESPACE + VALID_CHOICE_1 + WHITESPACE, false);
        assertEquals(actualChoice, exceptedChoice);
    }

    @Test
    public void parseChoice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseChoice(INVALID_CHOICE, false));
        assertThrows(ParseException.class, () -> ParserUtil.parseChoice(INVALID_CHOICE, true));
    }

    @Test
    public void parseChoices_nullChoices_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseChoices(null, VALID_CHOICE_1));
    }

    @Test
    public void parseChoices_nullAnswer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseChoices(
                Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2, VALID_CHOICE_3),
                null));
    }

    @Test
    public void parseChoices_validValues_returnsOptions() throws Exception {
        List<String> choices = Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2, VALID_CHOICE_3);
        Set<Choice> actualChoices = ParserUtil.parseChoices(choices, VALID_ANSWER);
        Set<Choice> expectedChoices = new HashSet<>();
        for (String choice: choices) {
            expectedChoices.add(new Choice(choice, false));
        }
        expectedChoices.add(new Choice(VALID_ANSWER, true));
        assertEquals(actualChoices, expectedChoices);
    }

    @Test
    public void parseChoices_invalidValues_throwsParseException() {

        // One invalid option
        assertThrows(ParseException.class, () ->
                ParserUtil.parseChoices(Arrays.asList(VALID_CHOICE_1, INVALID_CHOICE, VALID_CHOICE_3), VALID_ANSWER));

        // Not enough valid options
        assertThrows(ParseException.class, () ->
                ParserUtil.parseChoices(Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2), VALID_ANSWER));

        // No options
        assertThrows(ParseException.class, () -> ParserUtil.parseChoices(Collections.emptyList(), VALID_ANSWER));

        // Invalid Answer
        assertThrows(ParseException.class, () -> ParserUtil.parseChoices(
                Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2, VALID_CHOICE_3),
                INVALID_ANSWER));
    }

    @Test
    public void parseTrueFalseAnswer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTrueFalseAnswer(null));
    }

    @Test
    public void parseTrueFalseAnswer_validValues_returnsChoices() throws Exception {
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice(TRUE_CHOICE_TITLE, false));
        expectedChoices.add(new Choice(FALSE_CHOICE_TITLE, true));
        Set<Choice> actualChoices = ParserUtil.parseTrueFalseAnswer(ANSWER_FALSE);
        Set<Choice> actualChoicesLower = ParserUtil.parseTrueFalseAnswer("f");
        assertEquals(actualChoices, expectedChoices);
        assertEquals(actualChoicesLower, expectedChoices);
    }

    @Test
    public void parseTrueFalseAnswer_validValuesWithWhitespace_returnsChoice() throws Exception {
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice(TRUE_CHOICE_TITLE, true));
        expectedChoices.add(new Choice(FALSE_CHOICE_TITLE, false));
        Set<Choice> actualChoices = ParserUtil.parseTrueFalseAnswer(WHITESPACE + ANSWER_TRUE + WHITESPACE);
        Set<Choice> actualChoicesLower = ParserUtil.parseTrueFalseAnswer(WHITESPACE + "t" + WHITESPACE);
        assertEquals(actualChoices, expectedChoices);
        assertEquals(actualChoicesLower, expectedChoices);
    }

    @Test
    public void parseTrueFalseAnswer_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTrueFalseAnswer(INVALID_ANSWER));
        assertThrows(ParseException.class, () -> ParserUtil.parseTrueFalseAnswer(INVALID_TRUE_FALSE_ANSWER));
    }

    @Test
    public void parseQuizLimit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuizLimit(null));
    }

    @Test
    public void parseQuizLimit_validLimit_returnsInt() throws Exception {
        int expectedLimit = 1;
        int actualLimit = ParserUtil.parseQuizLimit(VALID_LIMIT);
        assertEquals(actualLimit, expectedLimit);
    }

    @Test
    public void parseQuizLimit_validLimitWithWhiteSpace_returnsInt() throws Exception {
        int expectedLimit = 1;
        int actualLimit = ParserUtil.parseQuizLimit(WHITESPACE + VALID_LIMIT + WHITESPACE);
        assertEquals(actualLimit, expectedLimit);
    }

    @Test
    public void parseQuizLimit_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuizLimit(INVALID_LIMIT_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuizLimit(INVALID_LIMIT_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuizLimit(INVALID_LIMIT_3));
    }
}
