package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ROLE = "Softw@re Eng1n33r";
    private static final String INVALID_EMPLOYMENT_TYPE = "intern";
    private static final String INVALID_EXPECTED_SALARY = "-1200";
    private static final String INVALID_LEVEL_OF_EDUCATION = "Kindergarten";
    private static final String INVALID_EXPERIENCE = "-1";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INTERVIEW = "monday";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ROLE = "Software Engineer";
    private static final String VALID_EMPLOYMENT_TYPE = "Internship";
    private static final String VALID_EXPECTED_SALARY = "1200";
    private static final String VALID_LEVEL_OF_EDUCATION = "PhD";
    private static final String VALID_EXPERIENCE = "1";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_Interview = "2021-10-29, 10:30";
    private static final String VALID_NOTES = "He is a very good candidate!";

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
    public void parseMultipleIndex_oneInvalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleIndex("10 a"));
    }

    @Test
    public void parseMultipleIndex_allInvalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleIndex("b a c"));
    }

    @Test
    public void parseMultipleIndex_duplicateInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleIndex("1 1 2"));
    }

    @Test
    public void parseMultipleIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseMultipleIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseMultipleIndex_validInput_success() throws Exception {
        // Only 1 space in between
        assertArrayEquals(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON},
                ParserUtil.parseMultipleIndex("1 2 3"));

        // Leading and trailing whitespaces
        assertArrayEquals(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON},
                ParserUtil.parseMultipleIndex("  1 3 2  "));

        // Varying whitespaces
        assertArrayEquals(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON},
                ParserUtil.parseMultipleIndex("  3     1               2  "));
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
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
    }

    @Test
    public void parseEmploymentType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmploymentType((String) null));
    }

    @Test
    public void parseEmploymentType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmploymentType(INVALID_EMPLOYMENT_TYPE));
    }

    @Test
    public void parseEmploymentType_validValueWithoutWhitespace_returnsEmploymentType() throws Exception {
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE);
        assertEquals(employmentType, ParserUtil.parseEmploymentType(VALID_EMPLOYMENT_TYPE));
    }

    @Test
    public void parseEmploymentType_validValueWithWhitespace_returnsTrimmedEmploymentType() throws Exception {
        String employmentTypeWithWhitespace = WHITESPACE + VALID_EMPLOYMENT_TYPE + WHITESPACE;
        EmploymentType expectedEmploymentType = new EmploymentType(VALID_EMPLOYMENT_TYPE);
        assertEquals(expectedEmploymentType, ParserUtil.parseEmploymentType(employmentTypeWithWhitespace));
    }

    @Test
    public void parseExpectedSalary_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpectedSalary((String) null));
    }

    @Test
    public void parseExpectedSalary_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpectedSalary(INVALID_EXPECTED_SALARY));
    }

    @Test
    public void parseExpectedSalary_validValueWithoutWhitespace_returnsExpectedSalary() throws Exception {
        ExpectedSalary expectedExpectedSalary = new ExpectedSalary(VALID_EXPECTED_SALARY);
        assertEquals(expectedExpectedSalary, ParserUtil.parseExpectedSalary(VALID_EXPECTED_SALARY));
    }

    @Test
    public void parseExpectedSalary_validValueWithWhitespace_returnsTrimmedExpectedSalary() throws Exception {
        String expectedSalaryWithWhitespace = WHITESPACE + VALID_EXPECTED_SALARY + WHITESPACE;
        ExpectedSalary expectedExpectedSalary = new ExpectedSalary(VALID_EXPECTED_SALARY);
        assertEquals(expectedExpectedSalary, ParserUtil.parseExpectedSalary(expectedSalaryWithWhitespace));
    }

    @Test
    public void parseLevelOfEducation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLevelOfEducation((String) null));
    }

    @Test
    public void parseLevelOfEducation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLevelOfEducation(INVALID_LEVEL_OF_EDUCATION));
    }

    @Test
    public void parseLevelOfEducation_validValueWithoutWhitespace_returnsLevelOfEducation() throws Exception {
        LevelOfEducation expectedLevelOfEducation = new LevelOfEducation(VALID_LEVEL_OF_EDUCATION);
        assertEquals(expectedLevelOfEducation, ParserUtil.parseLevelOfEducation(VALID_LEVEL_OF_EDUCATION));
    }

    @Test
    public void parseLevelOfEducation_validValueWithWhitespace_returnsTrimmedLevelOfEducation() throws Exception {
        String levelOfEducationWithWhitespace = WHITESPACE + VALID_LEVEL_OF_EDUCATION + WHITESPACE;
        LevelOfEducation expectedLevelOfEducation = new LevelOfEducation(VALID_LEVEL_OF_EDUCATION);
        assertEquals(expectedLevelOfEducation, ParserUtil.parseLevelOfEducation(levelOfEducationWithWhitespace));
    }

    @Test
    public void parseExperience_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExperience((String) null));
    }

    @Test
    public void parseExperience_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExperience(INVALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithoutWhitespace_returnsExperience() throws Exception {
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(VALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String experienceWithWhitespace = WHITESPACE + VALID_EXPERIENCE + WHITESPACE;
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(experienceWithWhitespace));
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
    public void parseInterview_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterview(null));
    }

    @Test
    public void parseInterview_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterview(INVALID_INTERVIEW));
    }

    @Test
    public void parseInterview_validValue_returnsInterview() throws Exception {
        Optional<Interview> expectedInterview = Optional.ofNullable(new Interview(VALID_Interview));
        assertEquals(expectedInterview, ParserUtil.parseInterview(VALID_Interview));
    }
    @Test
    public void parseNotes_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNotes((String) null));
    }

    @Test
    public void parseNotes_validValue_returnsNotes() throws Exception {
        Optional<Notes> expectedNotes = Optional.ofNullable(new Notes(VALID_NOTES));
        assertEquals(expectedNotes, ParserUtil.parseNotes(VALID_NOTES));
    }

}
