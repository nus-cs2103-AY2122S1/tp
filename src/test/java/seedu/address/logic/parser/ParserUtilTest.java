package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.address.logic.parser.ParserUtil.parseLastVisit;
import static seedu.address.logic.parser.ParserUtil.parseVisit;
import static seedu.address.logic.parser.ParserUtil.parseVisitForAdd;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.DateTimeUtil;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_LANGUAGE = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATETIME = "2021-02-30 12:00";
    private static final String INVALID_DATETIME_FORMAT = "2020-111-11 12:00";
    private static final String INVALID_VISIT_DATETIME = DateTimeUtil.getInvalidVisitString();
    private static final String INVALID_LAST_VISIT_DATETIME = DateTimeUtil.getInvalidLastVisitString();

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_LANGUAGE = "English";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_VISIT_DATETIME = DateTimeUtil.getValidVisitString();
    private static final String VALID_LAST_VISIT_DATETIME = DateTimeUtil.getValidLastVisitString();

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
    public void parseLanguage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLanguage((String) null));
    }

    @Test
    public void parseLanguage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLanguage(INVALID_LANGUAGE));
    }

    @Test
    public void parseLanguage_validValueWithoutWhitespace_returnsLanguage() throws Exception {
        Language expectedLanguage = new Language(VALID_LANGUAGE);
        assertEquals(expectedLanguage, ParserUtil.parseLanguage(VALID_LANGUAGE));
    }

    @Test
    public void parseLanguage_validValueWithWhitespace_returnsTrimmedLanguage() throws Exception {
        String languageWithWhitespace = WHITESPACE + VALID_LANGUAGE + WHITESPACE;
        Language expectedLanguage = new Language(VALID_LANGUAGE);
        assertEquals(expectedLanguage, ParserUtil.parseLanguage(languageWithWhitespace));
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
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseVisit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVisit((String) null));
    }

    @Test
    public void parseVisit_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisit(INVALID_DATETIME_FORMAT));
    }

    @Test
    public void parseVisit_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisit(INVALID_DATETIME));
    }

    @Test
    public void parseVisit_invalidPastDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisit(INVALID_VISIT_DATETIME));
    }

    @Test
    public void parseVisit_validValueWithoutWhitespace_returnsVisit() throws Exception {
        Optional<Visit> expectedVisit = parseVisit(VALID_VISIT_DATETIME);
        assertEquals(expectedVisit, ParserUtil.parseVisit(VALID_VISIT_DATETIME));
    }

    @Test
    public void parseVisit_validValueWithWhitespace_returnsTrimmedVisit() throws Exception {
        String visitWithWhitespace = WHITESPACE + VALID_VISIT_DATETIME + WHITESPACE;
        Optional<Visit> expectedVisit = parseVisit(VALID_VISIT_DATETIME);
        assertEquals(expectedVisit, ParserUtil.parseVisit(visitWithWhitespace));
    }

    @Test
    public void parseVisitForAdd_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVisitForAdd((String) null));
    }

    @Test
    public void parseVisitForAdd_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisitForAdd(INVALID_DATETIME_FORMAT));
    }

    @Test
    public void parseVisitForAdd_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisitForAdd(INVALID_DATETIME));
    }

    @Test
    public void parseVisitForAdd_invalidPastDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisitForAdd(INVALID_VISIT_DATETIME));
    }

    @Test
    public void parseVisitForAdd_validValueWithoutWhitespace_returnsVisit() throws Exception {
        Optional<Visit> expectedVisit = parseVisitForAdd(VALID_VISIT_DATETIME);
        assertEquals(expectedVisit, ParserUtil.parseVisitForAdd(VALID_VISIT_DATETIME));
    }

    @Test
    public void parseVisitForAdd_validValueWithWhitespace_returnsTrimmedVisit() throws Exception {
        String visitWithWhitespace = WHITESPACE + VALID_VISIT_DATETIME + WHITESPACE;
        Optional<Visit> expectedVisit = parseVisitForAdd(VALID_VISIT_DATETIME);
        assertEquals(expectedVisit, ParserUtil.parseVisitForAdd(visitWithWhitespace));
    }

    @Test
    public void parseLastVisit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLastVisit((String) null));
    }

    @Test
    public void parseLastVisit_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLastVisit(INVALID_DATETIME_FORMAT));
    }

    @Test
    public void parseLastVisit_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLastVisit(INVALID_DATETIME));
    }

    @Test
    public void parseLastVisit_invalidPastDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLastVisit(INVALID_LAST_VISIT_DATETIME));
    }

    @Test
    public void parseLastVisit_validValueWithoutWhitespace_returnsVisit() throws Exception {
        Optional<LastVisit> expectedLastVisit = parseLastVisit(VALID_LAST_VISIT_DATETIME);
        assertEquals(expectedLastVisit, ParserUtil.parseLastVisit(VALID_LAST_VISIT_DATETIME));
    }

    @Test
    public void parseLastVisit_validValueWithWhitespace_returnsTrimmedVisit() throws Exception {
        String visitWithWhitespace = WHITESPACE + VALID_LAST_VISIT_DATETIME + WHITESPACE;
        Optional<LastVisit> expectedLastVisit = parseLastVisit(VALID_LAST_VISIT_DATETIME);
        assertEquals(expectedLastVisit, parseLastVisit(visitWithWhitespace));
    }


    @Test
    public void parseDisplayedDatetime() {
        // parse datetime valid
        String storedDate = "2021-02-01 23:59";
        String displayedDate = "01 Feb 2021 23:59";
        assertEquals(displayedDate, ParserUtil.parseDisplayedDatetime(storedDate));
    }

    @Test
    public void arePrefixesPresent_validPrefix_success() {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize("v/" + VALID_VISIT_DATETIME, PREFIX_VISIT);
        assertTrue(arePrefixesPresent(argMap));
    }

    @Test
    public void arePrefixesPresent_validPrefixes_success() {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize("v/ lv/" + VALID_VISIT_DATETIME,
                PREFIX_VISIT, PREFIX_LAST_VISIT);
        assertTrue(arePrefixesPresent(argMap));
    }
}
